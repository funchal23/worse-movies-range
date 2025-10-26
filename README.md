# Worse Movie API

API para ler os indicados e vencedores da categoria de pior filme e para mostrar intervalos maximo e minimo de produtores que ganharam o premio.

## Requisitos

- Java 17
- Gradle

## Como executar a aplicação

HTTPS
```bash
git clone https://github.com/funchal23/worse-movies-range.git
cd worse-movie
./gradlew bootRun
```

SSH
```bash
git clone git@github.com:funchal23/worse-movies-range.git
cd worse-movie
./gradlew bootRun
```

A aplicação será iniciada na porta padrão 8080.

## Banco de dados

A aplicação utiliza um banco de dados H2 em memória. O console do H2 está disponível em:

```
http://localhost:8080/h2-console
```

Credenciais de acesso:
- JDBC URL: `jdbc:h2:mem:moviesdb`
- Username: `sa`
- Password: ` `

## API Endpoints

### Obter intervalo de prêmios

Retorna os produtores com maior e menor intervalo entre dois prêmios consecutivos.

#### Requisição

```bash
curl -X GET "http://localhost:8080/ranges?disregardMoreThanOneWinnerPerYear=false" -H "accept: application/json"
```

#### Resposta

```json
{
  "min": [
    {
      "producer": "Nome do Produtor",
      "interval": 1,
      "previousWin": 2018,
      "followingWin": 2019
    }
  ],
  "max": [
    {
      "producer": "Nome do Produtor",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```

## Estrutura do Projeto

O projeto segue uma arquitetura limpa com as seguintes camadas:

- **Domain**: Contém as regras de negócio e entidades do domínio
- **Application**: Contém os casos de uso da aplicação
- **Infrastructure**: Contém adaptadores para frameworks externos, como REST e persistência

## Tecnologias Utilizadas

- Spring Boot 3.5.6
- Spring Data JPA
- H2 Database
- Lombok
- OpenCSV 5.9
- Map Struct

## Testes
A aplicação inclui testes de integração com base no csv original. 
Caso tenha alterado o csv, é recomendado atualizar os testes de integração para refletir as mudanças.
Para executar os testes, use o comando:

```bash
./gradlew test