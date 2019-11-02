# PetClinic com SparkJava

## Instruções para o uso

### Pré-requisitos
- Mysql

### Execução
Para executar a aplicação entre na pasta do projeto (o mesmo diretorio do arquivo *pom.xml*) e rode o comando

```Terminal
    mvn compile exec:java
```
Depois basta consumir seus recursos. Por padrão toda vez que você rodar a aplicação ela irá apagar as tabelas do banco e cria-lás de novo. Isso pode ser mudado indo no arquivo src/main/resources/persistence.xml e mudando o **value="drop-and-create"** para **value="create"**.

## Endpoints disponiveis

- Hello: http://localhost:8081/
- Listar de veterinários: http://localhost:8081/listVets/
- Adicionar um dono: http://localhost:8081/owner/saveOwner/
body:
```JSON
    {
        "address": "Rua XXXXX XXXXX, 1234",
        "city": "XXXX",
        "telephone": "1234567890",
        "firstName": "Fulano",
        "lastName": "Silva"
    }
```
- Buscar um dono pelo sobrenome: http://localhost:8081/owner/searchOwner/Silva/
- Listar todos os donos: http://localhost:8081/owner/listOwners/
- Atualizar um dono: http://localhost:8081/owner/updateOwner/
body:
```JSON
    {
        "address": "Rua XXXXX XXXXX, 1234",
        "city": "XXXX",
        "telephone": "1234567890",
        "firstName": "Fulano",
        "lastName": "Sousa"
    }
```
- Adicionar um pet: http://localhost:8081/pet/addPet/1/
o 1 é o *id* do dono
body:
```JSON
{
	"name":"Max",
	"birthDate": {
		"year": 2019,
		"month": 10,
		"day": 20
	},
	"type": {
		"id": 1
	}
}
```
- Listar os pets: http://localhost:8081/pet/listPets/
- Listar os tipos de pet: http://localhost:8081/pet/listPetTypes/
- Editar as informações um pet: http://localhost:8081/pet/updatePet/1/
o 1 é o *id* do dono
body:
```JSON
{
	"name":"Max",
	"birthDate": {
		"year": 2019,
		"month": 10,
		"day": 20
	},
	"type": {
		"id": 2
	}
}
```
- Adicionar uma visita do pet a clinica: http://localhost:8081/visit/addVisit/
body:
```JSON
{
  "date": {
    "year": 2019,
    "month": 10,
    "day": 20
  },
	"description":"Banho",
	"petId": 1
}
```
- Listar as visitar de um pet: http://localhost:8081/visit/listVisit/1/
o 1 é o *id* do pet
