# Sistema de Automação Kaspper

## 📋 Descrição
Sistema de gerenciamento para automação de serviços empresariais, desenvolvido com Spring Boot e Thymeleaf. O sistema permite o cadastro e gerenciamento de empresas, serviços e tarefas relacionadas.

## 🚀 Características Principais
- Gestão completa de empresas, serviços e tarefas
- Cálculo Automático de orçamentos
- Envio através do WhatsApp ou email
- Interface web responsiva
- API REST para integração com outros sistemas

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring MVC
- Thymeleaf
- MySQL
- Maven
- HTML5/CSS3

## 📦 Instalação

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8+
- MySQL 8.0+

### Configuração do Banco de Dados
1. Crie um banco de dados MySQL:
```sql
CREATE DATABASE bdautomacaokaspper;
```

2. Configure as credenciais no arquivo `application.properties`:
```properties
spring.application.name=AutomacaoKaspper
spring.datasource.url=jdbc:mysql://localhost/bdautomacaokaspper?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password= 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.mvc.hiddenmethod.filter.enabled=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


spring.thymeleaf.cache=false
```

### Executando o Projeto
1. Clone o repositório:
```bash
git clone https://github.com/VivianVidal/automacao-kaspper.git
```

2. Navegue até a pasta do projeto:
```bash
cd automacao-kaspper
```

3. Execute o projeto com Maven:
```bash
mvn spring-boot:run
```

4. Acesse a aplicação em: `http://localhost:8080`

## 🔍 Estrutura do Projeto
```
automacao-kaspper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sistema/AutomacaoKaspper/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── rest/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 📚 Documentação Técnica

### Arquitetura do Sistema
O sistema segue uma arquitetura em camadas:
- **Presentation Layer**: Controllers e templates Thymeleaf
- **Data Access Layer**: Repositories e Entities
- **REST API Layer**: REST Controllers para integração externa

### Entidades Principais

#### Empresa
- Representa uma empresa cliente do sistema
- Atributos principais: nome, CNPJ, email, telefone
- Relacionamento OneToMany com Servico

#### Servico
- Representa um serviço contratado por uma empresa
- Atributos principais: descrição, status
- Relacionamento ManyToOne com Empresa
- Relacionamento OneToMany com Tarefa

#### Tarefa
- Representa uma tarefa específica de um serviço
- Atributos principais: descrição, valor_hora, status
- Relacionamento ManyToOne com Servico

### API REST

#### Endpoints Disponíveis

##### Empresas
```
GET    /api/empresas        - Lista todas as empresas
POST   /api/empresas        - Cria uma nova empresa
GET    /api/empresas/{id}   - Busca empresa por ID
PUT    /api/empresas/{id}   - Atualiza uma empresa
DELETE /api/empresas/{id}   - Remove uma empresa
```

##### Serviços
```
GET    /api/servicos        - Lista todos os serviços
POST   /api/servicos        - Cria um novo serviço
GET    /api/servicos/{id}   - Busca serviço por ID
PUT    /api/servicos/{id}   - Atualiza um serviço
DELETE /api/servicos/{id}   - Remove um serviço
```

##### Tarefas
```
GET    /api/tarefas         - Lista todas as tarefas
POST   /api/tarefas         - Cria uma nova tarefa
GET    /api/tarefas/{id}    - Busca tarefa por ID
PUT    /api/tarefas/{id}    - Atualiza uma tarefa
DELETE /api/tarefas/{id}    - Remove uma tarefa
```

### Interface Web
- Templates Thymeleaf para renderização server-side
- Layout responsivo usando CSS puro

## 🤝 Contribuindo
1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## ✨ Atualizações Futuras
- [ ] Sistema de notificações
- [ ] Integração com serviços externos
- [ ] Geração de relatórios em PDF

## 🎯 Status do Projeto
Em desenvolvimento ativo. Versão atual: 1.0.0

