# Projeto de Consulta de Endereço - Processo Seletivo Wipro

Este projeto foi desenvolvido como parte do processo seletivo para a Wipro e oferece uma API RESTful para consultar informações de endereço e calcular o frete com base no CEP fornecido. O projeto é baseado no Spring Boot e utiliza o serviço ViaCEP (https://viacep.com.br/) para obter informações de endereço.


## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior

## Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/yourusername/consulta-endereco.git
```


2. Entre no diretório do projeto:

```bash
cd consulta-endereco
```

3. Execute o projeto usando Maven:

```bash
mvn clean install
mvn spring-boot:run
```


4. A aplicação estará disponível em: http://localhost:8080/v1

## Endpoints

### Consultar Endereço

- Método: `POST`
- Endpoint: `/v1/consulta-endereco`
- Body: `{ "cep": "12345678" }`

## Exemplo de chamada:

```bash
curl -X POST \
  http://localhost:8080/v1/consulta-endereco \
  -H 'Content-Type: application/json' \
  -d '{ "cep": "12345678" }'
```

**Acessando a documentação da API**

Foi utilizado o Swagger para documentar e testar nossa API, oferecendo uma interface interativa e visual para facilitar o entendimento e uso dos endpoints.
1. Inicie a aplicação.
2. Acesse http://localhost:8080/swagger-ui/index.html no navegador.
3. A interface do Swagger exibe os endpoints disponíveis, permitindo testes diretamente na plataforma e garantindo clareza e colaboração entre os desenvolvedores.


**Resposta:**

```json
{
  "cep": "12345-678",
  "rua": "Rua Exemplo",
  "complemento": "",
  "bairro": "Bairro Exemplo",
  "cidade": "Cidade Exemplo",
  "estado": "SP",
  "frete": 7.85
}
```

## Testes automatizados com Cucumber
Implementado o Cucumber para realizar testes automatizados de comportamento (BDD) em nossa aplicação, facilitando a descrição e entendimento dos cenários de teste.

## Testes unitários
```bash
mvn test
```

## Tecnologias utilizadas
- Spring Boot
- Spring Web
- Lombok
- Swagger
- JUnit 5
- Mockito


**Desenvolvedor**: [Jocimar Neres](https://www.linkedin.com/in/jocimar-neres/)