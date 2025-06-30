# 💼 Sistema de Gestão Empresarial — Java + JDBC + MySQL

Este é um projeto de console em Java, que permite realizar operações de cadastro e gerenciamento de **Pessoas**, **Funcionários** e **Projetos**, com persistência de dados em um banco MySQL via JDBC.

---

## 📌 Funcionalidades

- 👤 CRUD completo de **Pessoas**
- 🧑‍💼 Gerenciamento de **Funcionários**, vinculados às pessoas existentes
- 📁 Controle de **Projetos**, com associação a funcionários
- 🔒 Validações de regras de negócio e mensagens de feedback amigáveis

---

## 🗃️ Estrutura do Banco de Dados

O banco utilizado é `corp_data`, e contém três tabelas:

- `pessoas (id, nome, email)`
- `funcionarios (id, matricula, setor)` — `id` é chave estrangeira de `pessoas`
- `projetos (id, titulo, detalhes, id_funcionario)` — ligado a `funcionarios`

> ✅ O script de criação e população do banco está disponível na pasta `scripts/Empresa.sql`.

---

## 📂 Estrutura do Projeto

```
src/
├── main/
│ └── java/
│ ├── Main.java
│ ├── DAO/
│ │ ├── PessoaDao.java
│ │ ├── FuncionarioDao.java
│ │ └── ProjetoDao.java
│ ├── models/
│ │ ├── Pessoa.java
│ │ ├── Funcionario.java
│ │ └── Projeto.java
│ └── utils/
│ └── Conexao.java
```

---

## ⚙️ Configuração do Banco de Dados

O arquivo `Conexao.java` é responsável pela conexão via JDBC:

```java
// utils/Conexao.java

String url = "jdbc:mysql://localhost:3306/corp_data";
String usuario = "root";        // Altere se necessário
String senha = "SUA_SENHA_AQUI"; // Insira sua senha do MySQL
```

## ✅ Requisitos

- Java 11 ou superior  
- MySQL Server instalado e em execução  
- Driver JDBC do MySQL incluído no classpath  
- IDE Java (Eclipse, IntelliJ, VS Code, etc.)

---
