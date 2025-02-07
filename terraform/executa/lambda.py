import os
import subprocess
import json

def lambda_handler(event, context):
    # Caminho no EFS onde o PMD está instalado
    pmd_path = "/mnt/efs/pmd/pmd-bin-6.55.0/bin/pmd"

    # Caminho para o código e ruleset (substitua pelos caminhos reais)
    code_path = "/tmp/code.html"
    ruleset_path = "/tmp/ruleset.xml"
    report_path = "/tmp/pmd-report.json"

    # Executa o PMD
    command = [
        pmd_path, "check",
        "-d", code_path,
        "-f", "json",
        "-R", ruleset_path,
        "--report-file", report_path,
        "--cache", "/tmp/pmd_cache",
        "--debug"
    ]

    try:
        subprocess.run(command, check=True)
    except subprocess.CalledProcessError as e:
        return {
            "statusCode": 500,
            "body": json.dumps({"error": f"Erro ao executar PMD: {e}"})
        }

    # Lê o relatório gerado
    with open(report_path, "r") as f:
        report_data = f.read()

    return {
        "statusCode": 200,
        "body": report_data
    }