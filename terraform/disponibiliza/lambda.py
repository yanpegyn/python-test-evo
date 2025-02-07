import os
import subprocess
import boto3
import requests

def lambda_handler(event, context):
    # Caminho no EFS onde o PMD será instalado
    efs_path = "/mnt/efs/pmd"

    # URL do PMD (substitua pela versão desejada)
    pmd_url = "https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.55.0/pmd-bin-6.55.0.zip"

    # Cria o diretório no EFS (se não existir)
    os.makedirs(efs_path, exist_ok=True)

    # Baixa o PMD
    zip_path = os.path.join(efs_path, "pmd.zip")
    response = requests.get(pmd_url)
    with open(zip_path, "wb") as f:
        f.write(response.content)

    # Extrai o PMD
    subprocess.run(["unzip", "-o", zip_path, "-d", efs_path])

    # Remove o arquivo ZIP
    os.remove(zip_path)

    return {
        "statusCode": 200,
        "body": "PMD atualizado com sucesso no EFS."
    }