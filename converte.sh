#!/bin/bash

# Função para converter SARIF para SonarQube Issue
convert_sarif_to_sonarqube() {
  local sarif_file="$1"
  local sonarqube_file="$2"

  # Mapeamento de priority para severity em formato JSON
  severity_map='{
    "1": "BLOCKER",
    "2": "CRITICAL",
    "3": "MAJOR",
    "4": "MINOR",
    "5": "INFO"
  }'

  # Ler o arquivo SARIF e converter para o formato SonarQube
  jq --argjson severity_map "$severity_map" '{
    issues: [
      .runs[0].results[] as $result |
      .runs[0].tool.driver.rules[] | select(.id == $result.ruleId) as $rule |
      {
        engineId: .runs[0].tool.driver.name,
        ruleId: $result.ruleId,
        severity: ($severity_map[($rule.properties.priority | tostring)] // "MAJOR"),
        
        # Extrai o primeiro valor dentro dos colchetes como type
        type: ($rule.help.text | capture("\\[(?<type>[^\\]]+)\\]") | .type // "CODE_SMELL"),

        primaryLocation: {
          message: $result.message.text,
          filePath: ($result.locations[0].physicalLocation.artifactLocation.uri | sub("file:///"; "")),
          textRange: {
            startLine: ($result.locations[0].physicalLocation.region.startLine // 0),
            endLine: ($result.locations[0].physicalLocation.region.endLine // 0),
            startColumn: ($result.locations[0].physicalLocation.region.startColumn // 0),
            endColumn: ($result.locations[0].physicalLocation.region.endColumn // 0)
          }
        },

        # Extrai o segundo valor dentro dos colchetes como effortMinutes
        effortMinutes: ($rule.help.text | capture("\\[[^\\]]+\\]\\[(?<effort>[0-9]+)\\]") | .effort | tonumber // 30)
      }
    ]
  }' "$sarif_file" > "$sonarqube_file"
}

# Caminho para o arquivo SARIF de entrada
sarif_file="input.sarif"

# Caminho para o arquivo SonarQube de saída
sonarqube_file="output.sonarqube.json"

# Executar a conversão
convert_sarif_to_sonarqube "$sarif_file" "$sonarqube_file"

echo "Conversão concluída. Arquivo SonarQube gerado em $sonarqube_file"
