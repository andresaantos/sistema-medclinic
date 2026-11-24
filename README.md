# MedClinic — Sistema de Gestão de Consultas Médicas

Projeto desenvolvido para a disciplina de **Desenvolvimento de Sistemas Corporativos** (Sistemas para Internet - UERN), aplicando a arquitetura em três camadas **MVC** com Spring Boot, PostgreSQL e front-end em HTML5, CSS3 e JavaScript.

---

## 1. Cenário da Empresa

A **MedClinic** é uma clínica médica ambulatorial de pequeno porte que atende diversas especialidades. Antes do sistema, os agendamentos e o controle financeiro eram feitos em planilhas separadas, o que dificultava a busca por histórico de pacientes e o controle de acesso às informações.

O sistema foi construído em Java na arquitetura **MVC em três camadas**:
* **Modelo (Persistência):** Entidades JPA (`Usuario` e `Consulta`) e repositórios Spring Data JPA conectados ao PostgreSQL.
* **Controle (Negócios):** Endpoints REST e filtro de segurança responsáveis pelas regras de negócio, autenticação por sessão e permissões por perfil.
* **Visão (Aplicação):** Páginas web em HTML5, CSS3 e JavaScript que consomem a API REST via `fetch`.

---

## 2. Estrutura do Projeto

```text
medclinic/
├── pom.xml                                # Dependências (Spring Web, JPA, PostgreSQL, BCrypt)
└── src/main/
    ├── java/medclinic/
    │   ├── MedClinicApplication.java      # Inicialização e criação dos usuários padrão
    │   ├── negocios/                      # Camada de Negócios (Controle e Segurança)
    │   │   ├── AuthController.java
    │   │   ├── AuthFilter.java
    │   │   ├── ConsultaController.java
    │   │   └── UsuarioController.java
    │   └── persistencia/                  # Camada de Persistência (Modelo e Banco)
    │       ├── Consulta.java
    │       ├── ConsultaRepository.java
    │       ├── Usuario.java
    │       └── UsuarioRepository.java
    └── resources/
        ├── application.properties         # Configuração do banco e porta HTTP
        └── static/                        # Camada de Aplicação (Visão / Front-end)
            ├── login.html
            ├── index.html
            ├── cadastrarConsulta.html
            ├── listarConsultas.html
            ├── cadastrarUsuario.html
            ├── listarUsuarios.html
            ├── relatorio.html
            └── style.css
```


## 4. Roteiro de Instalação, Execução e Testes

### Pré-requisitos
* **Java JDK 17** ou superior
* **Apache Maven 3.8+** (ou IDE Eclipse com suporte Maven)
* **PostgreSQL** instalado e em execução

### Passo 1: Baixar o Projeto
Clone o repositório ou baixe o ZIP pelo GitHub:
```bash
git clone https://github.com/andresaantos/sistema-medclinic.git
```

### Passo 2: Criar o Banco de Dados
No PostgreSQL (via `psql` ou **pgAdmin 4**), crie o banco de dados vazio:
```sql
CREATE DATABASE medclinic;
```

### Passo 3: Configurar a Senha do Banco
Abra o arquivo `src/main/resources/application.properties` e informe a senha do seu PostgreSQL local:
```properties
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/medclinic
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.jpa.hibernate.ddl-auto=update
```
*(As tabelas e os usuários iniciais são criados automaticamente na primeira execução.)*

### Passo 4: Executar o Sistema
Na pasta raiz do projeto, execute pelo terminal (ou importe como projeto Maven no Eclipse e rode `MedClinicApplication.java`):
```bash
mvn spring-boot:run
```

### Passo 5: Acessar e Testar
Abra **`http://localhost:8081/login.html`** no navegador e utilize as contas padrão:
* **Administrador:** `admin` / `admin123` (acesso completo: agendamentos, listagem, usuários e relatórios)
* **Usuário Comum:** `user` / `user123` (apenas visualização da lista de consultas)