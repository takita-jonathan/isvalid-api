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

## Pensamento e Decisões de Design
No início, fiquei em dúvida sobre a melhor abordagem para implementar as validações: utilizar anotações customizadas ou delegar essa responsabilidade a um service específico. Considerei ainda dividir o service em classes separadas para organizar melhor as validações, mas concluí que isso poderia ser um exagero para o escopo atual.

Diante dessas opções, decidi implementar ambas as abordagens, pois cada uma tem suas vantagens em diferentes cenários. Além disso, considerei essencial garantir um tratamento de erros consistente entre as soluções, o que influenciou diretamente minhas escolhas de implementação.

## Classe de Validações – Evolução da Abordagem
Inicialmente, comecei com validações simples utilizando ifs, separando cada regra em métodos distintos para maior legibilidade. No entanto, percebi que isso poderia escalar mal conforme o número de validações aumentasse.

Para tornar a solução mais organizada, considerei o uso de um Map, onde cada tipo de validação seria associado a uma chave e poderia ser iterado dinamicamente. Durante essa pesquisa, me deparei com o Predicate, que parecia se encaixar bem na necessidade de avaliar condições de forma funcional.

Porém, ao aprofundar a análise, percebi que o Predicate não permitia um tratamento de erros alinhado ao que é esperado do Validator usado em anotações. Como alternativa, optei pelo uso de Function, que além de permitir a validação, também possibilita um melhor controle sobre os erros retornados.

Outro ponto que considerei foi o uso de métodos estáticos. Optei por evitá-los, pois caso futuramente a validação precise acessar o banco de dados ou interagir com outras dependências, métodos estáticos seriam uma limitação, já que não permitem injeção de dependências.

## Validações com Anotações e ConstraintValidator
Na segunda abordagem, implementei anotações personalizadas e criei classes separadas para cada tipo de validação. Entretanto, percebi que isso poderia gerar um acoplamento desnecessário ao tentar integrar essa solução com um service centralizado para validações.

Para evitar esse problema, decidi que as classes responsáveis pelas anotações deveriam operar de forma independente, mas ainda permitir integração com o service quando necessário. Isso garantiu que cada classe mantivesse sua responsabilidade única, ao mesmo tempo em que o código permaneceu modular e organizado.

## Implementação dos Testes
Após definir a estrutura das validações, avancei para os testes unitários e integrados. Como já tenho experiência com Cucumber, optei por utilizá-lo para cenários mais descritivos, enquanto JUnit 5 e Mockito foram utilizados para validar as regras de negócio de forma isolada.

Além disso, aproveitei as dependências do Spring Boot Test para facilitar a configuração do contexto de testes, garantindo um ambiente mais próximo do real sem precisar carregar toda a aplicação.

## CI/CD e Infraestrutura
Para automação da infraestrutura, escolhi Terraform, pois ele permite criar e gerenciar os recursos de forma declarativa e escalável.

Na pipeline de CI/CD, optei pelo GitHub Actions, pois além de já estar familiarizado com a ferramenta, ela atende bem às necessidades do projeto, que por enquanto não exige uma solução mais complexa. A configuração foi feita para garantir build, testes e deploy automatizados, garantindo mais confiabilidade no fluxo de entrega.