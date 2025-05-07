##!/bin/sh
#
## Criar o diretório persistente, se não existir
#mkdir -p "$DATA_DIR"
#
## Copiar o diretório Logos para o diretório persistente, se ele existir no contêiner
#if [ -d "/dr7/categorias" ] && [ ! -d "$DATA_DIR/categorias" ]; then
#    echo "Copying /dr7/categorias to $DATA_DIR/categorias"
#    cp -r /app/Logos "$DATA_DIR/Logos"
#else
#    echo "Directory /dr7/categorias does not exist or $DATA_DIR/categorias already exists."
#fi
#
## Listar conteúdo do diretório persistente para verificação
#echo "Contents of $DATA_DIR:"
#ls -l "$DATA_DIR"
#
## Iniciar a aplicação
#exec java -jar /dr7/dr7.jar