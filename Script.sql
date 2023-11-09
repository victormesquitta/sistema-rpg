drop database if exists projeto;
create database if not exists projeto;

use projeto;

CREATE TABLE IF NOT EXISTS usuario (
  codusuario int(3) primary key auto_increment,
  usuario VARCHAR(16) NOT NULL,
  email VARCHAR(255) NULL,
  senha VARCHAR(32) NOT NULL,
  datacriacao TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  imagem BLOB NOT NULL,
  horasjogadas TIME NOT NULL);
  
  insert into usuario(codusuario,usuario,email,senha) values(null,"TiagoAnal", "tiagoanal@terra.com.br", "senha123");

  CREATE TABLE IF NOT EXISTS pericias (
    codpericia INT NOT NULL,
    atletismo VARCHAR(45) NULL,
    acrobacia VARCHAR(45) NULL,
    furtividade VARCHAR(45) NULL,
    prestidigitacao VARCHAR(45) NULL,
    arcanismo VARCHAR(45) NULL,
    historia VARCHAR(45) NULL,
    investigacao VARCHAR(45) NULL,
    natureza VARCHAR(45) NULL,
    religiao VARCHAR(45) NULL,
    intuicao VARCHAR(45) NULL,
    lidarcomanimais VARCHAR(45) NULL,
    medicina VARCHAR(45) NULL,
    percepcao VARCHAR(45) NULL,
    sobrevivencia VARCHAR(45) NULL,
    atuacao VARCHAR(45) NULL,
    enganacao VARCHAR(45) NULL,
    intimidacao VARCHAR(45) NULL,
    persuasao VARCHAR(45) NULL);

    INSERT INTO pericias (codpericia, atletismo, acrobacia, furtividade, prestidigitacao, arcanismo, historia, investigacao, natureza, religiao, intuicao, lidarcomanimais, medicina, percepcao, sobrevivencia, atuacao, enganacao, intimidacao, persuasao)
    VALUES (1, '2', '4', '3', '5', '1', '2', '3', '4', '1', '5', '3', '2', '4', '3', '1', '2', '4', '5');

CREATE TABLE IF NOT EXISTS participante (
  codparticipante INT primary key auto_increment,
  nome VARCHAR(45) NOT NULL,
  imagem BLOB NOT NULL,
  cargo VARCHAR(40) NOT NULL,
  adm int NOT NULL,
  user_codusuario int(3) NOT NULL,
  FOREIGN KEY (user_codusuario) REFERENCES usuario(codusuario)
);

INSERT INTO participante (nome, imagem, cargo, adm, user_codusuario)
VALUES ('Nome do Participante', 'dados_da_imagem_em_formato_blob', 'Cargo do Participante', 1, 1);
