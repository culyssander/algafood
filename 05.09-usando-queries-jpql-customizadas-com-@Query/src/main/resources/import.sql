INSERT INTO cozinha(id, nome) VALUES (1, "Angolana");
INSERT INTO cozinha(id, nome) VALUES (2, "Congolesa");

INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES("Bom de boa", 8.0, 1);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES("Tudo de graca", 7.5, 1);

INSERT INTO forma_pagamento(descricao) VALUES ("CARTAO VISA");
INSERT INTO forma_pagamento(descricao) VALUES ("BOLETO");

INSERT INTO permissao(nome, descricao) VALUES ("Adicionar-Cozinha", "Permite adicionar nova cozinha");

INSERT INTO estado(nome) VALUES ("Sao Paulo");
INSERT INTO estado(nome) VALUES ("Rio de Janeiro");
INSERT INTO estado(nome) VALUES ("Brasilia");

INSERT INTO cidade(nome, estado_id) VALUES ("Sao Paulo", 1);