# Tweteroo
API Twetroo, um clone do X (antigo Twitter).


# Como funciona?
Este projeto é uma API REST para atender a uma rede social similar ao Twitter onde temos usuários (que são identificados com avatares e usernames) e mensagens.

Para entidade, foram criadas cinco rotas:

- GET `/users`: Retorna todos os usuários cadastrados
- GET `/tweets`: Retorna todos as mensagens, independente do usuário
- GET `/tweets/user/:userId`: Retorna todas as mensagens relativas ao usuário (via userId)
- POST `/users`: Cria um novo usuário. Caso haja algum usuário com o mesmo "username", retorna erro 409 (Conflict)

```
{
    "username": "usuárioTeste",
		avatar: "https://meusiteteste.com.br/wp-content/uploads/2020/09/04-09_gato_SITE.jpg?quality=70&strip=info"
}

```

- POST `/tweet`: Cria uma nova mensagem.

```
{
	"userId": 1,
  "tweet": "essa é uma mensagem teste"
}

```

# Motivação 
Este projeto foi feito para praticar a construção de uma API REST utilizando Java.

# Tecnologias utilizadas
Para este projeto, foram utilizadas:

- Java
- SpringBoot;
- PostgreSQL para o banco de dados;

# Como rodar em desenvolvimento
Para executar este projeto em desenvolvimento, é necessário seguir os passos abaixo:

- Clonar o repositório;
- Compile o projeto `mvn clean install`
- Em seguida, criar o arquivo `application.properties` com base no `application.properties.example`. A estrutura do `application.properties` é a seguinte:

```
 
spring.datasource.url= jdbc:postgresql://localhost:5432/sua_database
spring.datasource.username=username
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update 


spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true 
```

- Esse arquivo é usado para fazer a conexão com o banco de dados. As três primeiras linhas se referem a sua database, username e password e devem ser alterados conforme a sua configuração do PostgreSQL. 

# Contato

Para mais informações, entre em contato através de [emailparacamille@gmail.com](mailto:emailparacamille@gmail.com).

