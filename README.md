# Password Validator API

API desenvolvida com o objetivo de validar senhas segundo critérios bem definidos de segurança.

---

## ✅ Regras para senha válida

Uma senha será considerada **válida** caso atenda a **TODOS** os seguintes critérios:

- Possuir **nove ou mais caracteres**
- Conter **ao menos uma letra minúscula**
- Conter **ao menos uma letra maiúscula**
- Conter **ao menos um dígito**
- Conter **ao menos um caractere especial** dentre: `!@#$%^&*()-+`
- **Não conter caracteres repetidos**
- **Não conter espaços em branco**

---

## 🚀 Como executar o projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/password-validator.git
cd password-validator
```

2. Compile e execute:
```bash
./mvnw spring-boot:run
```

3. Acesse a API via Postman, Insomnia ou cURL:

```http
POST http://localhost:8080/validate-password
Content-Type: application/json

{
  "passwordInput": "AbTp9!fok"
}
```

Resposta esperada:
```json
{
  "valid": true
}
```

---

## 🧠 Decisões e arquitetura da solução

A solução foi construída com base nos princípios **SOLID**, **Clean Code**, e boas práticas da arquitetura em camadas:

### Camadas do projeto:

- `controller` — expõe a API REST (`/validate-password`)
- `service` — camada de regra de negócio, responsável pela validação da senha
- `dto` — objetos de entrada e saída da API (PasswordRequest e PasswordResponse)
- `test` — testes unitários para regras de negócio + testes de integração da API com `MockMvc`

### Princípios aplicados:

- **SRP**: Cada classe possui uma única responsabilidade clara.
- **OCP**: A lógica de validação pode ser estendida com novos critérios sem modificar o código existente.
- **Injeção de Dependência**: O controller depende de uma interface, facilitando testes e manutenção.
- **Testabilidade**: Separação da lógica em serviços testáveis de forma isolada.
- **Clean Code**: Nomes autoexplicativos, métodos pequenos, e sem duplicações.

---

## 🧪 Testes

### Testes Unitários:
- Cobrem todos os critérios de validação da senha, testando a classe `PasswordValidatorServiceImpl`.

### Testes de Integração:
- Simulam requisições HTTP reais via `MockMvc`, testando o funcionamento completo da API.

Executar testes:
```bash
./mvnw test
```

---

## 📌 Premissas assumidas

- **Espaços em branco** foram tratados como inválidos, mesmo dentro da senha, conforme observado no exemplo `AbTp9 fok // false`.
- O nome do campo de entrada foi mantido como `passwordInput`, conforme especificado.
- A resposta da API foi modelada como `{ "valid": true|false }`, para permitir futura extensão com mensagens ou status.

---

## 🧱 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Maven
- JUnit 5
- MockMvc
- Lombok

---

## 📞 Contato

Caso queira discutir melhorias ou dúvidas sobre a implementação, fique à vontade para entrar em contato.

---