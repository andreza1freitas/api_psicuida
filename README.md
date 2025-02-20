# PROJETO INTEGRADOR: ANÁLISE DE SOLUÇÕES INTEGRADAS PARA ORGANIZAÇÕES

## Descrição

A **API PsiCuida** é um backend desenvolvido em Spring Boot para fornecer suporte psicológico acessível e confidencial. A plataforma permite o agendamento de sessões, diários do paciente e integração com autenticação via login tradicional e social.

## Integrantes do Grupo

- Andreza Azevedo Gomes de Freitas
- Lucas Vieira Rocha
- Luiz Otávio Silva Henriques
- Mégui Silva Machado
- Thayna Englidy da Silva
  
<br>

## Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.4.2
- **Banco de Dados**: PostgreSQL
- **Gerenciador de Dependências**: Maven
- **Bibliotecas**: Lombok (para redução de código boilerplate)

## Configuração do Projeto

### Requisitos

- **JDK 21**
- **Maven**
- **PostgreSQL** (Rodando na porta 5432, banco `PSICUIDA`)

### Instalação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/andreza1freitas/api_psicuida.git

2. Acesse o diretório do projeto:

   ```bash
   cd api_psicuida

3. Configure o banco de dados no arquivo `application.yml.`

4. Compile e execute o projeto:

   ```bash
   mvn spring-boot:run

### Configuração do Banco de Dados

Certifique-se de ter o PostgreSQL instalado e configurado. <br> O arquivo `application.yml` contém a seguinte configuração padrão:

```sh
spring:
  datasource:
    url: 'jdbc:postgresql://localhost:5432/PSICUIDA'
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

## Endpoints Principais

### Autenticação

- **POST** /auth/login - Login com credenciais usuário e senha.
- **POST** /auth/social-login - Login via provedores sociais.

### Agendamentos

- **GET** /agendamentos/{id} - Obter detalhes de um agendamento.
- **GET** /agendamentos/por-paciente - Listar agendamentos de um paciente.

### Diários

- **GET** /diarios/{id} - Obter um diário específico
- **GET** /diarios/data/{data}/paciente/{pacienteId} - Buscar diários por data e paciente**

  
## CORS

O backend permite requisições apenas da origem:

```sh
psicuida:
  security:
    cors:
      allowed-origins: >
        http://localhost:3000
```

## Licença

Este projeto é de uso acadêmico e não possui licença comercial definida.

## Contato

Para dúvidas ou sugestões, entre em contato com os integrantes do grupo.






