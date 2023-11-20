use projeto;
select * from tb_usuario;
select * from tb_participante;
select * from tb_campanha;
select * from tb_regras;
select * from tb_personagem;

use projeto;
INSERT INTO tb_usuario(datacriacao, email, horasjogadas, senha, usuario) 
VALUES('2023-11-02','mvictormesquita@gmail.com','08:30','1234','mesquitta');
INSERT INTO tb_campanha (nome, qtdplayers, datacriacao, senha, qtdplayersonline, qtdplayersoffline)
VALUES ('Nome Campanha', 5, '2023-11-01', 'senha123', 3, 2);
INSERT INTO tb_participante (codusuario, codcampanha, nome, cargo, adm, admMaster)
VALUES (1, 1, 'Nome Participante', 'Mestre', true, false);
INSERT INTO tb_regras (data, nome, descricao, codparticipante)
VALUES ('2023-11-01', 'Regras Nome', 'Descrição das regras', 1);
INSERT INTO tb_personagem (codparticipante, nome, foto, classe, raca, antecedente, nivel, xp, bonusproficiencia, inspiracao, sabedoria_passiva, ca, iniciativa, deslocamento, hp, hptemp, tracospersonalidade, ideais, vinculos, fraquezas, corolhos, altura, peso, pele, cabelos, aparencia, aliadosorg, outrascarac, historia, tesouro, habilidadeconjuracao, cdresistmagia, bonusataquemagia)
VALUES (1, 'Nome Personagem', NULL, 'Classe', 'Raça', 'Antecedente', 1, 0, 2, 'Inspiração', 'Sabedoria Passiva', 12, 3, 30.0, 10, 0, 'Traços Personalidade', 'Ideais', 'Vínculos', 'Fraquezas', 'Cor dos Olhos', 'Altura', 'Peso', 'Cor da Pele', 'Cor dos Cabelos', 'Aparência', 'Aliados e Organizações', 'Outras Características', 'História', 'Tesouro', 'Inteligência', 15, 5);


/*delete from tb_participante where codparticipante=4;*/