# Ekan Dev Jr - API para Gerenciamento de Beneficiários e Documentos

Esta é uma API REST desenvolvida para o gerenciamento de beneficiários e documentos. Ela foi criada com base no Spring Boot e utiliza o Spring Data JPA para interagir com o banco de dados. A API permite a criação, atualização, exclusão e recuperação de informações sobre beneficiários e seus documentos associados.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- Springdoc OpenAPI para documentação
- H2 Database (banco de dados em memória)
- Lombok para otimização de código
- Spring Validation para validar input de requisições

## Endpoints

### Beneficiários

- **Listar Beneficiários**: `GET http://localhost:8080/ekan/api/beneficiary`
- **Cadastrar Beneficiário**: `POST http://localhost:8080/ekan/api/beneficiary`
- **Atualizar Beneficiário**: `PUT http://localhost:8080/ekan/api/beneficiary/{id}`
- **Excluir Beneficiário**: `DELETE http://localhost:8080/ekan/api/beneficiary/{id}`
- **Listar Documentos de Beneficário**: `GET http://localhost:8080/ekan/api/beneficiary/{id}/documents`



## Como Utilizar a API

1. Certifique-se de ter o Java 17 instalado no seu sistema.
2. Clone este repositório do GitHub.
3. Abra o projeto em sua IDE favorita.
4. Configure as propriedades do banco de dados no arquivo `application.properties` (por padrão, a aplicação usa o H2 Database em memória. Que já está configurado no arquivo).
5. Execute a aplicação Spring Boot.
6. Acesse a documentação da API no seu navegador através de `http://localhost:8080/swagger-ui.html`.
7. Utilize algum testador de requisição (POSTMAN / INSOMNIA)
8. Cole sua URI no campo indicado, use o body abaixo de exemplo, para cadastrar o primeiro beneficiário. Depois acesse outros métodos para checar validaçóes e usuabilidade.

Exemplo de body para POST: 


```
{
  "name": "Leandro ",
  "telNumber": "11947165215",
  "birthDate": "2001-05-30",  // Formato de data (YYYY-MM-DD)
  "documentInputDTOS": [
    {
      "typeDocument": "CPF",
      "description": "99999999954"
    },
    {
      "typeDocument": "RG",
      "description": "111111114"
    }
  ]
}

```

Obs: Os campos não podem ser nulos ou vazios. Caso contrário, a ExceptionHandler lançara um exceção informando os dados inválidos.

