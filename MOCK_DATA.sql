/*FATEC SÃO JOSÉ DOS CAMPOS

Otimização de Sistemas de Banco de Dados

Laboratório 04


Reproduzir em laboratório a teoria apresentada em sala de aula quanto ao parse de comandos e custo gerado no servidor.

O laboratório consiste na implementação de um código-fonte acessando o banco de dados via um driver capaz de processar 
"prepared statements". Ex: Java e JDBC. O aluno deve elaborar duas versões do mesmo código alternando o modo de acesso
aos dados conforme explicado em aula (hardcoded e softcoded).

O processamento consiste em executar 100.000 vezes ou mais um mesmo select numa tabela criada pelo aluno.

Para cada modo de acesso o aluno deve computar o tempo gasto para processar o total dos comandos.

Ao final deve-se montar um gráfico comparativo entre as duas versões implementadas e demonstrar a diferença entre elas.
*/


create table lab04_OTB (
	id NUMBER PRIMARY KEY ,
	data_hora VARCHAR(50)
);

create sequence sq_lab04 INCREMENT BY 1 NOCACHE;

CREATE OR REPLACE PROCEDURE insere_lab04_otb IS
    cont int :=0;
    BEGIN
        LOOP
    
            INSERT INTO lab04_OTB (id, data_hora) values (sq_lab04.NEXTVAL,TO_CHAR(current_timestamp, 'DD/MM/YYYY HH24:MI:SS') );
            cont := cont+1;
            COMMIT;
            
            EXIT WHEN cont = 100001;
        END LOOP;
END insere_lab04_otb;

--user: System

SET TIMING ON
EXEC insere_lab04_otb;


select count(*) from lab04_OTB ; 