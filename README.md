# BusIQ powered by SPTrans
Uma API moderna (Semi READ-ONLY) para consulta e gerenciamento de informações sobre transporte público em São Paulo, 
utilizando dados da SP Trans. Dados via GTFS Estático (não acompanham informações em tempo real), tenha acesso as 
principais informações sobre linhas de ônibus, itinerários, paradas e horários. Todos os dias às 12h os arquivos são atualizados.

## ☁️ Inspiração:
O projeto é foi inspirado pelo desafio de entender e manipular dados do sistema público de transporte coletivo de São 
Paulo. Uma paixão minha, interligada a outras paixões: Java, dados e ônibus.

## 🚀 Tecnologias e Características do Projeto:
- Java 21
- Spring Boot 3.5.5
- JWT
- PostgreSQL
- TestContainers
- Clean Architecture
- Docker
- Swagger
- JUnit 5

## 📦 Funcionalidades Principais:
- Autenticação com JWT
- Segurança personalizada com Spring Security
- Recursos de consulta de dados via REST
- Conteinerização com Docker

## 📋 Pré-requisitos
- Java 21+
- Maven 3.9.x+
- Docker (opcional, para conteinerização e testes de integração)
- PostgreSQL 17+
- Git para comandos git BTW

## ⚙️ Configuração
Todas as configurações necessárias passam pelo arquivo application.properties, referenciado no repositório como 
[application-example.properties](https://github.com/Minessp/BusIQ-powered-by-SP-Trans/blob/main/src/main/resources/application-example.properties). Todas as propriedades desse arquivo devem ser substituídas pelos valores reais, 
correspondentes ao ambiente de execução da aplicação. 

## 🔧 Instalação e Setup
```bash
    git clone https://github.com/Minessp/BusIQ-powered-by-SP-Trans.git
    cd BusIQ-powered-by-SP-Trans
    mvn clean install
    docker compose build 
    docker compose up
```
### ⚠️ Não se esqueça da etapa de configuração!

## 🤝 Contribua
O maior presente para um desenvolvedor é o conhecimento de outro, achou algum problema, enxergou uma solução mais prática,
não deixe de contribuir, seja com um pull request ou abrir uma issue. Cada feedback é importantíssimo para o desenvolvimento
da aplicação, do meu e seu desenvolvimento. Cada linha de código escrita e refatorada é um aprendizado novo. 

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 📧 Contato
[![LinkedIn](https://skillicons.dev/icons?i=linkedin&theme=dark)](https://www.linkedin.com/in/paulosilvawork/)
[![LinkedIn](https://skillicons.dev/icons?i=gmail&theme=dark)](mailto:paulosilva.jobs@gmail.com)
[![Instagram](https://skillicons.dev/icons?i=instagram&theme=dark)](https://www.instagram.com/minesssp/)

