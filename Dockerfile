FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /dr7

# Copie o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Copie o restante dos arquivos da aplicação, incluindo app/Logos
COPY . .

# Execute o comando Maven para construir o projeto
RUN mvn clean package

# Estágio de produção
FROM openjdk:21-jdk-slim

# Diretório de trabalho para a aplicação
WORKDIR /dr7

# Copie o arquivo .jar do estágio de construção
COPY --from=build /dr7/target/dr7-0.0.1-SNAPSHOT.jar dr7.jar

# Copie o diretório Logos do estágio de construção (se existir)
COPY --from=build /dr7/imagens /dr7/imagens

# Expor a porta 8080 para acesso externo
EXPOSE 8080

# Diretório onde os arquivos de dados serão armazenados no disco persistente
ENV DATA_DIR=/var/lib/data

# Script de inicialização para copiar os dados se o diretório Logos existir
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Comando de inicialização da aplicação
ENTRYPOINT ["/entrypoint.sh"]