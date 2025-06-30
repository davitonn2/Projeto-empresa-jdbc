CREATE DATABASE IF NOT EXISTS corp_data;
USE corp_data;

DROP TABLE IF EXISTS projetos;
DROP TABLE IF EXISTS funcionarios;
DROP TABLE IF EXISTS pessoas;

CREATE TABLE pessoas (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE funcionarios (
    id INT NOT NULL,
    matricula VARCHAR(15) NOT NULL UNIQUE,
    setor VARCHAR(120) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (id) REFERENCES pessoas(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE projetos (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    detalhes VARCHAR(255),
    id_funcionario INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_projeto_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

INSERT INTO pessoas (nome, email) VALUES
('Mariana Silva', 'mariana.silva@corpmail.com'),
('Thiago Oliveira', 'thiago.oliveira@corpmail.com'),
('Leticia Santos', 'leticia.santos@corpmail.com'),
('Rafael Almeida', 'rafael.almeida@corpmail.com'),
('Camila Ferreira', 'camila.ferreira@corpmail.com'),
('Lucas Ribeiro', 'lucas.ribeiro@corpmail.com'),
('Beatriz Gomes', 'beatriz.gomes@corpmail.com'),
('Felipe Souza', 'felipe.souza@corpmail.com'),
('Vanessa Rocha', 'vanessa.rocha@corpmail.com'),
('Rodrigo Castro', 'rodrigo.castro@corpmail.com');

INSERT INTO funcionarios (id, matricula, setor) VALUES
(1, 'EMP1001', 'Desenvolvimento'),
(2, 'EMP1002', 'Recursos Humanos'),
(3, 'EMP1003', 'Comunicação'),
(4, 'EMP1004', 'Financeiro'),
(5, 'EMP1005', 'Desenvolvimento'),
(6, 'EMP1006', 'Recursos Humanos'),
(7, 'EMP1007', 'Desenvolvimento'),
(8, 'EMP1008', 'Design Gráfico'),
(9, 'EMP1009', 'Infraestrutura'),
(10, 'EMP1010', 'Marketing Digital');

INSERT INTO projetos (titulo, detalhes, id_funcionario) VALUES
('Plataforma E-Commerce', 'Desenvolvimento da plataforma para vendas online', 1),
('Programa de Integração', 'Planejamento do onboarding para novos colaboradores', 2),
('Campanha Primavera', 'Estratégia de comunicação para campanha de primavera', 3),
('Auditoria Financeira', 'Análise e auditoria dos processos financeiros', 4),
('Aplicativo Mobile', 'Desenvolvimento do app para clientes', 5),
('Treinamentos Corporativos', 'Capacitação de equipes internas', 6),
('API Gateway', 'Implementação do gateway para APIs internas', 7),
('Identidade Visual', 'Atualização do manual de identidade visual', 8),
('Infraestrutura Cloud', 'Migração para infraestrutura em nuvem', 9),
('SEO e Mídias', 'Otimização para motores de busca e gestão de mídias digitais', 10);
