CREATE DATABASE linketinder;


CREATE TABLE candidato (
    id_candidato SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    pais VARCHAR(50) NOT NULL,
    cep VARCHAR(9),
    descricao_pessoal TEXT,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE empresa (
    id_empresa SERIAL PRIMARY KEY,
    nome_empresa VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    email_corporativo VARCHAR(255) UNIQUE NOT NULL,
    descricao_empresa TEXT,
    pais VARCHAR(50) NOT NULL,
    cep VARCHAR(9),
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE competencia (
    id_competencia SERIAL PRIMARY KEY,
    nome_competencia VARCHAR(100) NOT NULL
);

CREATE TABLE vaga (
    id_vaga SERIAL PRIMARY KEY,
    nome_vaga VARCHAR(100) NOT NULL,
    descricao_vaga TEXT NOT NULL,
    local_vaga VARCHAR(100) NOT NULL,
    id_empresa INT REFERENCES empresa(id_empresa)
);

CREATE TABLE candidato_competencia (
    id_candidato INT REFERENCES candidato(id_candidato),
    id_competencia INT REFERENCES competencia(id_competencia),
    PRIMARY KEY (id_candidato, id_competencia)
);

CREATE TABLE vaga_competencia (
    id_vaga INT REFERENCES vaga(id_vaga),
    id_competencia INT REFERENCES competencia(id_competencia),
    PRIMARY KEY (id_vaga, id_competencia)
);

CREATE TABLE curtida_candidato (
    id_candidato INT REFERENCES candidato(id_candidato),
    id_vaga INT REFERENCES vaga(id_vaga),
    PRIMARY KEY (id_candidato, id_vaga)
);

CREATE TABLE curtida_empresa (
    id_empresa INT REFERENCES empresa(id_empresa),
    id_candidato INT REFERENCES candidato(id_candidato),
    PRIMARY KEY (id_empresa, id_candidato)
);


INSERT INTO candidato (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao_pessoal, senha)
VALUES
    ('Harry', 'Potter', '31/07/1980', 'harry.potter@example.com', '12345678900', 'Inglaterra', '12345-678', 'Desenvolvedor Python e Django', 'senha@candidato1'),
    ('Frodo', 'Baggins', '22/09/2000','frodo.baggins@example.com', '22233344455', 'Condado', '56789-012', 'Especialista em Java e Spring', 'senha@candidato2'),
    ('Katniss', 'Everdeen', '31/05/2001', 'katniss.everdeen@example.com', '33344455566', 'Panem', '67890-123', 'Desenvolvedora Front-end Angular', 'senha@candidato3'),
    ('Daenerys', 'Targaryen', '13/04/1999', 'daenerys.targaryen@example.com', '44455566677', 'Dragonstone', '78901-234', 'Analista de Banco de Dados SQL', 'senha@candidato4'),    
    ('Jon', 'Snow', '06/01/1999', 'jon.snow@example.com', '55566677788', 'Winterfell', '89012-345', 'Desenvolvedor Full Stack JavaScript', 'senha@candidato5');


INSERT INTO empresa (nome_empresa, cnpj, email_corporativo, descricao_empresa, pais, cep, senha)
VALUES
    ('Gringotts Bank', '01234567000100', 'contact@gringotts.com', 'Banco de Magia e Finanças', 'Reino Unido', '12345-678', 'senha@empresa1'),
    ('Umbrella Corporation', '02345678000111', 'contact@umbrella.com', 'Corporation de Tecnologia e Bioengenharia', 'EUA', '23456-789', 'senha@empresa2'),
    ('Stark Industries', '03456789000122', 'contact@starkindustries.com', 'Tecnologia Avançada e Inovação', 'EUA', '34567-890', 'senha@empresa3'),
    ('WICKED', '04567890000133', 'contact@wicked.com', 'Experimentos e Pesquisa', 'EUA', '45678-901', 'senha@empresa4'),
    ('Vought International', '05678901000144', 'contact@vought.com', 'Gestão de Super-heróis e Tecnologia', 'EUA', '56789-012', 'senha@empresa5');



INSERT INTO competencia (nome_competencia)
VALUES
    ('Python'), ('Java'), ('SQL'), ('JavaScript'), ('Machine Learning'), ('Angular'), ('Pandas'), ('Django');

INSERT INTO vaga (nome_vaga, descricao_vaga, local_vaga, id_vaga)
VALUES
    ('Analista de Dados', 'Extração, processamento e análise dos dados', 'Remoto', '1');

INSERT INTO curtida_candidato (id_candidato, id_vaga)
VALUES
    (1,1);

INSERT INTO curtida_empresa (id_empresa, id_candidato)
VALUES
    (1,1);