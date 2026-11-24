# MedClinic — Sistema de Gestão de Consultas Médicas

Projeto desenvolvido para a disciplina de **Desenvolvimento de Sistemas Corporativos** (Tecnologia em Sistemas para Internet — UERN/UAB), aplicando a arquitetura em três camadas **MVC** com Spring Boot, PostgreSQL e front-end em HTML5, CSS3 e JavaScript.

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