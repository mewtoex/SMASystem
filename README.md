# ☕ SMA System - Sistema de Gestão para ONGs

![Java](https://img.shields.io/badge/Java-Legacy-red?style=for-the-badge&logo=java)
![UML](https://img.shields.io/badge/Engenharia-UML%20%26%20Docs-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Concluído%20(TCC)-success?style=for-the-badge)

> *Projeto acadêmico completo com ênfase em Engenharia de Software, Padrões de Projeto (MVC) e Documentação Técnica.*

O **SMA (Sistema Mão Amiga)** foi desenvolvido como Trabalho de Conclusão de Curso (TCC) para informatizar os processos de uma ONG real. O foco do projeto não foi apenas o código, mas todo o ciclo de vida de software: Levantamento de Requisitos, Modelagem UML, Implementação e Testes.

---

## 📚 Documentação de Engenharia

Diferente de projetos casuais, este sistema possui documentação formal de nível corporativo.

📄 **[Ler TCC Completo (PDF)](docs/TCC_Sistema_Mao_Amiga.pdf)**

### Destaques da Documentação:
* **Diagramas de Caso de Uso:** Mapeamento completo das interações dos atores (Voluntários, Admins).
* **Diagramas de Classe:** Estrutura MVC detalhada.
* **Modelo ER (Banco de Dados):** Estrutura relacional normalizada no PostgreSQL.
* **Estimativas:** Contagem de Pontos de Função e cronograma (PMBOK).

---

## 🏗️ Arquitetura Técnica (MVC Puro)

O sistema foi construído "do zero", sem frameworks mágicos, para demonstrar domínio dos fundamentos de Orientação a Objetos.

```java
// Exemplo da Camada Controller (Orquestração)
public class LoginController {
    public void validarLogin(String user, String pass) {
        LoginDao dao = new LoginDao(); // Acesso direto ao DAO
        if(dao.existe(user, pass)) {
            new PrincipalView().setVisible(true); // Chamada da View
        }
    }
}
```

Estrutura de Pacotes
view: Telas em Java Swing (JFrames).

controller: Regras de negócio e validações.

model: POJOs representando as entidades do banco.

dao: Data Access Object - SQL JDBC puro.

util: Conexão Singleton com Postgres.

🛠️ Tecnologias Utilizadas
Linguagem: Java 8

Interface: Swing (Desktop)

Banco: PostgreSQL

Relatórios: iReport / JasperReports

Engenharia: UML, Astah, PMBOK

Desenvolvido por Whanderson Andrade.
