
# Financial transfer
API para fazer transferências bancarias de uma conta para outra
## Decisões Arquiteturais
A API segue o padrão REST sendo:
* **Controllers**: Controlam os pontos de entrada da aplicação, tratando as requisições e respostas HTTP
* **DTOs**: São usados para encapsular os dados e transferi-los entre as camadas, especialmente entre os controladores e os serviços, sem expor os modelos de domínio internos.
* **Models**: Representam o domínio ou entidades de negócio e são usados principalmente pela camada de persistência.
* **Repositories**: Abstraem a lógica de acesso aos dados, facilitando as operações CRUD.
* **Services**: Contêm a lógica de negócio e fazem a ponte entre os controladores e os repositórios.
## Versões de Linguagem e Ferramentas Utilizadas

* **Linguagem de Programação**: JAVA SDK 19.0.1
* **Framework**: Spring Boot versão 3.2.0
* **Sistema de Banco de Dados**: H2
* **Ferramentas de Construção**: Maven

## Pré-requisitos

* **Java**: 19.0.1
* **Maven**: 3.9.3

## Uso da API
A API será executada na porta 8080 http://localhost:8080
## Exemplo de chamada da API
* Listar transferências - GET /transfer/api/v1/list-transfer
* Fazer transferência - POST /transfer/api/v1
* Calcular transferência - POST /transfer/api/v1/calculete-transfer
* Listar taxas de transferência - GET /transfer/api/v1/list-transfer-fee-schedule
