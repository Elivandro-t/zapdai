#!/bin/sh

# Criar o diretório persistente, se não existir
mkdir -p "$DATA_DIR"

# Copiar o diretório Logos para o diretório persistente, se ele existir no contêiner
if [ -d "/dr7/imagens" ] && [ ! -d "$DATA_DIR/imagens" ]; then
    echo "Copying /dr7/imagens to $DATA_DIR/imagens"
    cp -r /dr7/imagens "$DATA_DIR/imagens"
else
    echo "Directory /dr7/imagens does not exist or $DATA_DIR/imagens already exists."
fi

# Listar conteúdo do diretório persistente para verificação
echo "Contents of $DATA_DIR:"
ls -l "$DATA_DIR"

# Iniciar a aplicação
exec java -jar /dr7/dr7.jar