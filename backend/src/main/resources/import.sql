INSERT INTO TB_TIPO_DOMINIO (DESCRICAO, CODIGO) VALUES ('Gênero', 'GENERO');
INSERT INTO TB_TIPO_DOMINIO (DESCRICAO, CODIGO) VALUES ('Tipo de telefone', 'TIPO_TELEFONE');
INSERT INTO TB_TIPO_DOMINIO (DESCRICAO, CODIGO) VALUES ('Tipo de anotação', 'TIPO_ANOTACAO');
INSERT INTO TB_TIPO_DOMINIO (DESCRICAO, CODIGO) VALUES ('Situação do tipo de anotação', 'SITUACAO_TIPO_ANOTACAO');

INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Masculino', 'MASCULINO', 1);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Feminino', 'FEMININO', 1);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Não binário', 'NAO_BINARIO', 1);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Residencial', 'RESIDENCIAL', 2);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Celular', 'CELULAR', 2);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Rascunho', 'RASCUNHO', 3);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Estudo', 'ESTUDO', 3);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Lembrete', 'LEMBRETE', 3);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Estudo em andamento', 'ESTUDO_ANDAMENTO', 4);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Estudo finalizado', 'ESTUDO_FINALIZADO', 4);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Rascunho em aberto', 'RASCUNHO_ABERTO', 4);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Rascunho finalizado', 'RASCUNHO_FINALIZADO', 4);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Lembrete em aberto', 'LEMBRETE_ABERTO', 4);
INSERT INTO TB_DOMINIO (DESCRICAO, CODIGO, ID_TIPO_DOMINIO)  VALUES ('Lembrete finalizado', 'LEMBRETE_FINALIZADO', 4);

INSERT INTO TB_TELEFONE (NUMERO, DDD, DESCRICAO, ID_TIPO_TELEFONE, CREATED_AT) VALUES ('20927386', '65', 'Telefone do lucas', 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_TELEFONE (NUMERO, DDD, DESCRICAO, ID_TIPO_TELEFONE, CREATED_AT) VALUES ('24357416', '81', 'Telefone do sebastiao', 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_TELEFONE (NUMERO, DDD, DESCRICAO, ID_TIPO_TELEFONE, CREATED_AT) VALUES ('986421134', '82', 'Celular da mariah', 2, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_TELEFONE (NUMERO, DDD, DESCRICAO, ID_TIPO_TELEFONE, CREATED_AT) VALUES ('980815185', '96', 'Celular da juliana', 2, '2023-10-06 23:00:00.000000+00');

INSERT INTO TB_USUARIO (NOME, EMAIL, CPF, RENDA, DATA_NASCIMENTO, QUANTIDADE_FILHOS, ID_TELEFONE, ID_GENERO, CREATED_AT) VALUES ('Lucas Anthony Carlos Eduardo Assunção', 'lucas-assuncao91@arteche.com.br', '19827537733', 10000.00, '1964-02-13', 2, 1, 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_USUARIO (NOME, EMAIL, CPF, RENDA, DATA_NASCIMENTO, QUANTIDADE_FILHOS, ID_TELEFONE, ID_GENERO, CREATED_AT) VALUES ('Sebastião Gabriel Monteiro', 'sebastiao_monteiro@grupoannaprado.com.br', '48678626810', 2500.00, '1973-03-12', 1, 2, 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_USUARIO (NOME, EMAIL, CPF, RENDA, DATA_NASCIMENTO, QUANTIDADE_FILHOS, ID_TELEFONE, ID_GENERO, CREATED_AT) VALUES ('Mariah Luna Cardoso', 'mariah.luna.cardoso@asconnet.com.br', '28104715763', 5000.00, '1997-01-14', 0, 3, 2, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_USUARIO (NOME, EMAIL, CPF, RENDA, DATA_NASCIMENTO, QUANTIDADE_FILHOS, ID_TELEFONE, ID_GENERO, CREATED_AT) VALUES ('Osvaldo André Thiago Rezende', 'osvaldo.andre.rezende@yahoo.com', '99763694507', 13000.00, '1990-01-23', 1, 4, 3, '2023-10-06 23:00:00.000000+00');

INSERT INTO TB_ANOTACAO (TITULO, DESCRICAO, ID_USUARIO, CREATED_AT) VALUES ('Titulo 1','Lorem ipsum vivamus eu quis eros platea sem et, aenean class nunc dictumst auctor mattis volutpat velit, torquent platea congue velit fusce lacus feugiat. volutpat ornare sodales aenean odio nisi, platea fusce vivamus consequat magna risus, orci scelerisque porttitor fusce. non per taciti molestie est nulla magna, lectus curabitur fames vestibulum scelerisque hac congue, arcu diam aenean laoreet accumsan. non quisque taciti suspendisse tincidunt ut nostra aenean adipiscing condimentum, malesuada fames hendrerit torquent quisque nullam aenean conubia, venenatis pellentesque commodo interdum est etiam quisque per. posuere eleifend cubilia tellus dictum ullamcorper arcu fames, vehicula nec ullamcorper habitasse nibh iaculis et, ac pharetra aenean gravida orci aptent.', 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_ANOTACAO (TITULO, DESCRICAO, ID_USUARIO, CREATED_AT) VALUES ('Titulo 2','Suspendisse a dictum libero litora neque lectus purus aptent, tempor dui mollis enim aliquam tortor nullam pulvinar ut, duis nostra purus aptent cubilia netus quisque. venenatis interdum sollicitudin aliquam potenti vulputate semper convallis pharetra commodo ante, senectus aliquet in sed nunc mattis nec nulla velit consequat, vulputate tristique maecenas amet et enim mattis aptent curabitur. torquent nunc ut pharetra neque pretium netus nam laoreet sagittis, praesent quam iaculis nec et potenti torquent gravida curae diam, a dolor purus vivamus enim malesuada venenatis placerat. molestie posuere class hendrerit cursus aliquet, conubia torquent purus et erat, quam ad torquent libero. ', 1, '2023-10-06 23:00:00.000000+00');
INSERT INTO TB_ANOTACAO (TITULO, DESCRICAO, ID_USUARIO, CREATED_AT) VALUES ('Titulo 3','Lorem ipsum suscipit quisque etiam tempus torquent commodo condimentum sapien quisque habitasse nibh, arcu libero amet condimentum purus mi libero fames hac fermentum tincidunt dictum, feugiat fusce lorem mollis tellus proin aenean fames nullam volutpat rutrum. pulvinar elit lobortis consequat ut habitant ut conubia imperdiet aliquam, euismod phasellus sit venenatis metus pharetra molestie porta nunc, turpis lacinia pellentesque hac curabitur pretium elit cras. eget eros urna eleifend nisi posuere suscipit vulputate, purus mattis hendrerit aliquam viverra rutrum venenatis augue, non tempus lorem hendrerit ipsum semper. netus cras quis aliquam dolor elementum massa orci pellentesque primis fames curabitur facilisis ante mattis, nostra dapibus id imperdiet lorem rhoncus tempor senectus venenatis ut lectus non felis. ', 2, '2023-10-06 23:00:00.000000+00');