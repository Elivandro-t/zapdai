CREATE TABLE planos (
    id VARCHAR(100) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT, -- alterado de VARCHAR(255) para TEXT
    data_create TIMESTAMP,
    promocao VARCHAR(100),
    sub_descricao_permition TEXT, -- alterado de VARCHAR(255) para TEXT
    ativo BOOLEAN NOT NULL
);