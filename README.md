# Desafio DB - API Biblioteca
Este repositório contém uma API REST para gerenciar uma biblioteca, incluindo locatários, autores, livros e aluguéis. A aplicação foi desenvolvida utilizando Spring Boot e documentada com OpenAPI/Swagger.

## Pré-requisitos
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Como subir o Docker
Para iniciar a aplicação em um container Docker, siga os seguintes passos:
1. Clone o repositório
```bash
git clone https://github.com/anacagliari/desafiodb-java.git
```
2. Construa a imagem Docker
Entre via terminal Command Prompt na pasta apirest-biblioteca e execute o comando abaixo:
```bash
docker-compose build
```
3. Inicie o container:
```bash
docker-compose up
```
A partir daqui, o banco Postgre estará no container atendendo na porta 5432.
4. Iniciando aplicação Spring Boot:
Ao acessar o arquivo ApirestBibliotecaApplication.java e dar start na aplicação Spring Boot, caso seja a primeira vez, via as configurações do JPA, serão criadas as tabelas e sequences necessárias para aplicação.
Após, aplicação estará disponível em http://localhost:8080.
5. Banco de dados Postgre:
Caso queira acessar via cliente de banco de dados, os dados de conexão para testes estão no no arquivo docker-compose.yml:
POSTGRES_USER
POSTGRES_PASSWORD
POSTGRES_DB

## Documentação da API
A documentação da API está disponível via Swagger. Para acessá-la, siga os seguintes passos:
1. Com a aplicação em execução, abra seu navegador e acesse: http://localhost:8080/swagger-ui.html
2. Você verá a interface do Swagger onde poderá explorar e testar os endpoints da API.

## Testando a API
Aqui estão alguns exemplos de como testar a API utilizando curl caso esteja com o banco de dados vazio:
1. Adicionar um novo autor
```bash
curl -X POST "http://localhost:8080/autor" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"nome\": \"Miguel de Cervantes\", \"sexo\": \"Masculino\",  \"anoNascimento\": 1547, \"cpf\": \"66622244488\" }"
```
2. Adicionar um novo livro
```bash
curl -X POST "http://localhost:8080/livro" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"nome\": \"Dom Quixote\", \"isbn\": \"9783161484100\" , \"dataPublicacao\": 1605-01-16, \"autores\": [ { \"id:\" 5 } ] }"
```
3. Listar todos os livros
```bash
curl -X GET "http://localhost:8080/livro" -H "accept: application/json"
```
4. Buscar um livro por ID
```bash
curl -X GET "http://localhost:8080/livro/1" -H "accept: application/json"
```
5. Atualizar um livro
```bash
curl -X PUT "http://localhost:8080/livro" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": 1, \"nome\": \"Dom Quixote II\", \"isbn\": \"9783161484100\" , \"dataPublicacao\": 1605-01-16, \"autores\": [ { \"id:\" 5 } ] }"
```
6. Deletar um livro
```bash
curl -X DELETE "http://localhost:8080/livro/1" -H "accept: */*"
```

## Estrutura do Projeto
src/main/java: Contém o código fonte da aplicação.
Dividido da seguinte forma:
1. Controller: endpoints rest da aplicação.
2. Service: camada de regras de negócios e validações.
3. Repository: camada de interface de acesso ao banco de dados.
4. Model: representatividade das entidades do banco de dados.
5. Dto: padrão para melhorar a comunicação da API e não expor dados que não são necessários para o funcionamento.

src/test/java: Contém os testes unitários e de integração.
Dividio da seguinte forma:
1. Controller: testes de integração da API.
2. Service: testes unitários de regras de negócios.

## Testes automatizados
Inclui o pluggin Jacoco de cobertura para ser possível visualizar a porcentagem de cobertura de testes do projeto. Para visualizar, execute os comandos:
```bash
mvn clean test
mvn jacoco:report
```
Para visualizar o percentual de cobertura acessar /target/site/jacoco/index.html

## Maiores dificuldades no desenvolvimento do projeto
1. Montagem da arquitetura: como possuo conhecimentos apenas acadêmicos, criar a arquitetura do projeto foi um grande desafio. Adicionar tecnologias como Docker, testes automatizados, coberturas de testes, Swagger e utilização do JPA, onde todo meu conhecimento é de forma teórica, fazer com que todas essas tecnologias funcionassem juntas foi o mais complexo.
2. Aprendizado de tecnologias na prática: para fazer tudo funcionar de acordo com as melhores práticas, tive que buscar muito conhecimento durante o decorrer do desafio. Acredito que consegui uma boa solução.
3. Versão do Swagger X versão do Spring Boot: investi bastante tempo até encontrar uma versão da OpenAPI que funcionasse com o Spring Boot 3.
4. Equipamento: com a quantidade de ferramentas que utilizei para desenvolver este desafio, visualizei a necessidade de ter uma máquina mais robusta. Por vezes as ferramentas utilizadas ficaram lentas, o que comprometeu a produtividade.

## Conhecimentos de outras tecnologias não utilizadas
HTML, CSS, JavaScript, Python, TypeScript.

## Conhecimentos gerais
SCRUM, prototipação, histórias de usuários, relacionamento com cliente.

## Escolhas tomadas
Busquei aplicar os conhecimentos que tive em aula, sendo eles:
1. Arquitetura MVC entendo que esta arquitetura é a mais aderente para o desenvolvimento deste projeto/desafio.
2. Dto: utilizei este padrão buscando minimizar os dados expostos em cada endpoint, bem como a busca de melhor eficiência no tráfego de rede.
3. Handler de exceções: com esta estrutura busquei centralizar o tratamento de exceções, facilitando a manutenção e visando o padrão Restful.
4. Teste Coverage: em minhas pesquisas durante este desafio, percebi a ampla utilização de tecnologias que analisam a cobertura de testes de uma aplicação. Por este motivo, quis aplicar este conceito de cobertura de testes na API. Elegi o Jacoco por perceber que é um dos pluggins mais utilizados para este fim.
5. Docker: visto que um dos objetivos deste desafio era a persistência de dados, busquei conhecimento para facilitar a configuração e a reprodutibilidade do ambiente em que desenvolvi. Utilizei os volumes do Docker para preservar os dados. 

## Tarefa Bônus 3
Decidi fazer o versionamento da minha API pela prática de Versionamento na URI, pois achei a mais simples de entender e implementar.