# 📘 Documentação do Projeto: IsValid

## 📌 Tecnologias Utilizadas
O projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Java 21** - Última versão do Java para melhor desempenho e suporte.
- **Spring Boot 3** - Framework para criação de aplicações Java modernas e escaláveis.
- **JUnit 5** - Framework de testes unitários.
- **Cucumber** - Ferramenta para testes de aceitação baseados em BDD.
- **RestAssured** - Biblioteca para testes de APIs REST.
- **Lombok** - Reduz a verbosidade do código eliminando a necessidade de getters, setters e construtores manuais.
- **SpringDoc OpenAPI** - Integração do Swagger para documentação da API.
- **Maven** - Gerenciador de dependências.

---

## ⚙️ Configuração do Ambiente
Antes de rodar o projeto, certifique-se de ter os seguintes requisitos instalados:

### 🔹 Requisitos
- **JDK 21** → Pode ser instalado via [Adoptium](https://adoptium.net/), [asdf](https://asdf-vm.com/) ou [SDKMAN](https://sdkman.io/).
- **Maven 3.9+** → Para gerenciar dependências e compilar o projeto.
- **Docker (Opcional)** → Caso queira rodar a aplicação em um container.
- **Postman/Insomnia (Opcional)** → Para testar manualmente os endpoints.
- **Swagger UI** → Documentação interativa para testar os endpoints.

## 🚀 Rodando a Aplicação
A aplicação pode ser iniciada com o seguinte comando:

```shell
mvn spring-boot:run
```

Isso iniciará o servidor na porta **8080**

## 🧪 Executando os Testes
O projeto possui testes unitários, de integração 

Para rodar todos os testes:

### ✅ Testes Unitários e de Integração

```shell
mvn test
```

### ✅ Testes Unitários

```shell
mvn test -Dtest=unit.**
```

### ✅ Testes Integração

```shell
mvn test -Dtest=integration.**
```


## 🌍 Testando os Endpoints com Swagger
A API pode ser acessada via Swagger UI para facilitar os testes:

- 📄 Swagger UI: http://localhost:8080/swagger-ui.html
- 📘 OpenAPI JSON: http://localhost:8080/v3/api-docs