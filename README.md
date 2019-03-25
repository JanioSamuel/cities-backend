# Cities Backend


Projeto com o propósito de criar uma API Rest para o controle de cidades cadastradas.

O que essa API fará:

1. Ler o arquivo CSV das cidades para a base de dados;
2. Retornar somente as cidades que são capitais ordenadas por nome;
3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
quantidade de cidades;
4. Retornar a quantidade de cidades por estado;
5. Obter os dados da cidade informando o id do IBGE;
6. Retornar o nome das cidades baseado em um estado selecionado;
7. Permitir adicionar uma nova Cidade;
8. Permitir deletar uma cidade;
9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para
filtrar. retornar assim todos os objetos que contenham tal string;
10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens
iguais;
11. Retornar a quantidade de registros total;
12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base
na localização (distância em KM em linha reta);

# Requerimentos:

- Java 8
- Mysql 5
- Maven 3.2+

# Cadastrando dados do CSV no banco de dados:

Primeiramente, para criação da base e tabela no seu mysql execute o *.sql* que está no repositório.
Após isso, executar o projeto pelo *Eclipse* ou por meio do *.jar* que você pode gerar.
OBS: Existe no projeto um arquivo chamado *application.properties* o qual você informa os dados de conexão com a base de dados. Atualmente está como *usuario* e *senha* igual a *root* que é o utilizado para testes em minha máquina, troque de acordo com o seu.
OBS 2: Existe uma pequena Faq no final com possíveis erros que podem acontecer (que ocorreram no meu) no momento da execução do sistema em relação ao banco de dados.

 - Ler o arquivo CSV das cidades para a base de dados;

```sh
java -jar cities-backend.jar -DloadDatabase="C:\CaminhoDoCSV\arquivo.csv"
```
Ou pelo Eclipse rodando com o parâmetro:
```sh
-DloadDatabase="C:\CaminhoDoCSV\arquivo.csv"
```
Esse parâmetro é apenas utilizado quando você quer popular a base pela primeira vez, após isso pode retirar o parâmetro para não haver o carregamento novamente.

OBS: São mais de 5 mil registros, leva um tempo, cerca de 5 minutos em um PC mais ou menos.

# Endpoints

Após seu projeto estar rodando, vamos ver os endpoins disponíveis:

Para os passos de 2 a 12 foram criados endpoints e com exemplos para ajudar.

 - Retornar somente as cidades que são capitais ordenadas por nome:

```sh
localhost:8080/index/findOnlyCapital
```

 - Retornar o nome do estado com a maior e menor quantidade de cidades e a
quantidade de cidades:

```sh
localhost:8080/index/findStateWithMoreAndFewerCities
```

 - Retornar a quantidade de cidades por estado:

```sh
localhost:8080/index/getTotalCitiesByState
``` 
 
 - Obter os dados da cidade informando o id do IBGE:

```sh
localhost:8080/index/getCityByIbgeId?ibgeId=1100080
``` 

- Retornar o nome das cidades baseado em um estado selecionado:

```sh
localhost:8080/index/getAllCitiesByState?uf=GO
``` 

- Permitir adicionar uma nova Cidade:

POST
```sh
localhost:8080/index/add
``` 
Body:
```sh
{
	"ibge_id": 4,
	"uf": "PA",
	"name": "Teste Cidade",
	"capital": "true",
	"lon": -123456,
	"lat": -7890,
	"no_accents": "Teste Cidade",
	"alternative_names": "",
	"microregion": "AAA",
	"mesoregion": "AAAA"
}
``` 

- Permitir deletar uma cidade:

DELETE
```sh
localhost:8080/index/delete
``` 
Body
```sh
{
	"ibge_id": 1
}
``` 

- Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para
filtrar. retornar assim todos os objetos que contenham tal string:

```sh
localhost:8080/index/search?name=te
``` 

- Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens
iguais:

```sh
localhost:8080/index/countUniqueRecordsPerColumn?column=uf
``` 

- Retornar a quantidade de registros total:

```sh
localhost:8080/index/countAllRecords
``` 

- Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base
na localização (distância em KM em linha reta):

OBS: Não foi totalmente testado (meu pc travou pelo tanto de registros)
```sh
localhost:8080/index/searchMoreDistantCities
``` 

- Buscar todas as cidades:

```sh
localhost:8080/index/findAll
``` 
# Problemas encontrados e resoluções:

 - Mysql
```sh
Access denied for user 'root'@'localhost'
``` 

Ao tentar executar a primeira vez ocorreu esse erro. 
Para a solução dele foi executado o comando:
```sh
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
``` 

Erro 2:
```sh
The server time zone value 'Horario brasileiro de verão' is unrecognized or represents more than one time zone
```

Solução:
Execute esse comando no mysql:
```sh
SET GLOBAL time_zone = '+4:00';
```

Qualquer dúvida estou à disposição, se quiser contribuir mande um request. :)
