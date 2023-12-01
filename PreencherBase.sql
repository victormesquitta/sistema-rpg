use projeto;

/*Usuario*/
INSERT INTO tb_usuario(datacriacao, email, senha, usuario) 
VALUES('2023-11-02','mvictormesquita@gmail.com','1234','mesquitta');
INSERT INTO tb_usuario(datacriacao, email, senha, usuario) 
VALUES('2023-11-02','vii.mesquita@gmail.com','1234','mesquitta2');
INSERT INTO tb_usuario(datacriacao, email, senha, usuario) 
VALUES('2023-11-02','email3@gmail.com','1234','mesquitta3');

/*Campanha*/
INSERT INTO tb_campanha (nome, datacriacao, senha, descricao)
VALUES ('Nome Campanha', '2023-11-01', 'senha123', 'O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um espécime de livro. Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um espécime de livro. Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.O Lorem Ipsum é um texto modelo da indústria tipográfica e de impressão. O Lorem Ipsum tem vindo a ser o texto padrão usado por estas indústrias desde o ano de 1500, quando uma misturou os caracteres de um texto para criar um espécime de livro. Este texto não só sobreviveu 5 séculos, mas também o salto para a tipografia electrónica, mantendo-se essencialmente inalterada. Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.');
INSERT INTO tb_campanha (nome, datacriacao, senha, descricao)
VALUES ('Nome Campanha', '2023-11-01', 'senha123', 'Foi popularizada nos anos 60 com a disponibilização das folhas de Letraset, que continham passagens com Lorem Ipsum, e mais recentemente com os programas de publicação como o Aldus PageMaker que incluem versões do Lorem Ipsum.');


/*Participante*/
INSERT INTO tb_participante (codusuario, codcampanha, nome, cargo, adm, admMaster)
VALUES (1, 1, 'Nome Participante', 'Mestre', true, true);
INSERT INTO tb_participante (codusuario, codcampanha, nome, cargo, adm, admMaster)
VALUES (2, 1, 'Victor Mesquita', 'Mestre', true, true);
INSERT INTO tb_participante (codusuario, codcampanha, nome, cargo, adm, admMaster)
VALUES (3, 1, 'Vitor Ripper', 'Mestre', true, true);

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

/*Atributos*/
INSERT INTO tb_atributos (codpersonagem, forca, inteligencia, destreza, constituicao, carisma, sabedoria)
VALUES (1, 16, 14, 12, 15, 13, 10);

/*Talento Traço*/
INSERT INTO tb_talentotraco(codtalentotraco, nome, fonte, tipofonte, descricao, codpersonagem)
VALUES(1, 'Talento 1', 'Fonte 1', 'Tipo Fonte 1', 'Descrição do Talento 1', 1);

/*Moedas*/
INSERT INTO tb_moedas(po, pp, pc, pl, da, codpersonagem)
VALUES (10, 5, 20, 2, 1, 1);

/*Equipamento*/
INSERT INTO tb_equipamento (codpersonagem, nome, quantidade, peso) VALUES
(1, 'Espada', 1, 5.0);

/*Magia*/
INSERT INTO tb_magia (codpersonagem, material, tempoconjuracao, alcance, componente, duracao, descricao, nvlmagia, tipomagia)
VALUES (1, 'Material 1', 'Tempo 1', 'Alcance 1', 'Componente 1', 'Duracao 1', 'Descricao 1', 1, 'Tipo 1');

/*Ataques Conjuração*/
INSERT INTO tb_ataquesconjuracao (codpersonagem, nome, bonusataque, dano, tipodano, origem)
VALUES(1, 'Ataque Fogo Mágico', 5, '2d8', 'Fogo', 'Ataque');
