# Configuração do PostgreSQL no Spring Boot

Para configurar o PostgreSQL no Spring Boot, você precisa primeiro instalar o banco de dados na sua máquina e criar um banco específico para a aplicação chamado `catalogo`. Em seguida, no PGAdmin você irá criar o banco e um usuário com senha, concedendo permissões para esse usuário acessar o banco. Depois disso, no projeto, é necessário ajustar o arquivo `application.properties` (ou `application.yml`) em `src/main/resources` para incluir a URL de conexão, usuário, senha e o driver do PostgreSQL. Nesse projeto seria: `spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo`, `spring.datasource.username=postgres`, `spring.datasource.password=123456`, além de configurar o Hibernate para gerar ou validar as tabelas com `spring.jpa.hibernate.ddl-auto=update` e definir o dialeto como `org.hibernate.dialect.PostgreSQLDialect`. Com essa configuração, ao rodar a aplicação, o Spring Boot se conecta ao banco e o Hibernate cria ou atualiza as tabelas de acordo com as entidades do projeto.

---

## Configurações

```properties
spring.application.name=catalogo
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
