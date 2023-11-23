use projeto;

/*Usuario*/
INSERT INTO tb_usuario(datacriacao, email, horasjogadas, senha, usuario) 
VALUES('2023-11-02','mvictormesquita@gmail.com','08:30','1234','mesquitta');

/*Campanha*/
INSERT INTO tb_campanha (nome, qtdplayers, datacriacao, senha, qtdplayersonline, qtdplayersoffline)
VALUES ('Nome Campanha', 5, '2023-11-01', 'senha123', 3, 2);

/*Participante*/
INSERT INTO tb_participante (codusuario, codcampanha, nome, cargo, adm, admMaster)
VALUES (1, 1, 'Nome Participante', 'Mestre', true, true);

/*Regras*/
INSERT INTO tb_regras (data, nome, descricao, codparticipante)
VALUES ('2023-11-01', 'Regras Nome', 'Descrição das regras', 1);

/*Personagem*/
INSERT INTO tb_personagem (codparticipante, nome, classe, raca, antecedente, nivel, xp, bonusproficiencia, inspiracao, sabedoria_passiva, ca, iniciativa, deslocamento, hp, hptemp, tracospersonalidade, ideais, vinculos, fraquezas, corolhos, altura, peso, pele, cabelos, aparencia, aliadosorg, outrascarac, historia, tesouro, habilidadeconjuracao, cdresistmagia, bonusataquemagia)
VALUES (1, 'Nome Personagem', 'Classe', 'Raça', 'Antecedente', 1, 0, 2, 'Inspiração', 'Sabedoria Passiva', 12, 3, 30.0, 10, 0, 'Traços Personalidade', 'Ideais', 'Vínculos', 'Fraquezas', 'Cor dos Olhos', 'Altura', 'Peso', 'Cor da Pele', 'Cor dos Cabelos', 'Aparência', 'Aliados e Organizações', 'Outras Características', 'História', 'Tesouro', 'Inteligência', 15, 5);

/*Rolagem*/
INSERT INTO tb_rolagem (codpersonagem, resultrolagem, tipodado, data, tiporolagem, qtdrolagens)
VALUES (1, 15, 'D20', '2023-11-20 10:30:00', 'Ataque', 1);

/*Proficiencia*/
INSERT INTO tb_proficiencia (ferramenta, tipoProficiencia, atributoRelacionado, modificador, codpersonagem, valortotproficiencia)
VALUES ('Martelo', 'Ferramenta', 'Força', 2, 1, 5);

/*Outra Proficiencia*/
INSERT INTO tb_outraproficiencia (tipo, proficiencia, codpersonagem)
VALUES ('Magia', 'Conjuração', 1);

/*Pericias*/
INSERT INTO tb_pericias (codpersonagem, atletismo, acrobacia, furtividade, prestidigitacao, arcanismo, historia, investigacao, natureza, religiao, intuicao, lidarComAnimais, medicina, percepcao, sobrevivencia, atuacao, enganacao, intimidacao, persuasao)
VALUES (1, 1, 2, 0, -1, 3, -2, 0, 1, 2, -1, 0, -2, 3, 1, 2, 0, -1, 3);
