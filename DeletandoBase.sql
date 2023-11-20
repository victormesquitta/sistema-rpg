use projeto;
SET foreign_key_checks = 0;

-- Deletando dados de tabelas filhas
DELETE FROM tb_personagem;
DELETE FROM tb_regras;

-- Deletando dados da tabela principal
DELETE FROM tb_participante;
DELETE FROM tb_campanha;
DELETE FROM tb_usuario;

SET foreign_key_checks = 1;