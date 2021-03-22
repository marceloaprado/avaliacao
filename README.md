## Soluções de desafios em Java 

Este repositório contém a resolução de 5 desafios propostos para serem resolvidos através da linguagem Java.

Para todos as soluções desenvolvidas, foi utilizado como versão o Java 8. Os projetos e suas dependências foram organizadas através do [**Maven**](https://maven.apache.org/).

**Desafio 1 - Votos em relação ao total de eleitores**

Considerando a tabela abaixo, faça uma classe com 3 métodos que calculam:

| Total de eleitores | Válidos | Brancos | Nulos
|--|--|--|--|
| 1000 | 800 | 150 | 50 |


-   o percentual de votos válidos em relação ao total de eleitores
-   o percentual de brancos em relação ao total de eleitores
-   o percentual de nulos em relação ao total de eleitores

A resolução deste desafio encontra-se no projeto [**exercicio1**](https://github.com/marceloaprado/avaliacao/tree/main/exercicio1)



**Desafio 2 - Implementar o algoritmo de ordenação Bubble Sort**
Imagine o seguinte vetor:
`v = {5, 3, 2, 4, 7, 1, 0, 6}`
Faça um algoritmo que ordene o vetor acima de forma crescente utilizando o **Bubble Sort**.

A resolução deste desafio encontra-se no projeto [**exercicio2**](https://github.com/marceloaprado/avaliacao/tree/main/exercicio2)


**Desafio 3 - Calcular o fatorial de um número**
Faça um programa que calcule o fatorial de um número qualquer.
Por exemplo:
`5! = 1 x 2 x 3 x 4 x 5 = 120`

A resolução deste desafio encontra-se no projeto [**exercicio3**](https://github.com/marceloaprado/avaliacao/tree/main/exercicio3)


**Desafio 4 - Soma dos múltiplos de 3 ou 5**
Fazer uma implementação que calcule a soma de todos os números que sejam múltiplos de 3 ou 5, abaixo de um número X informado como entrada.
Por exemplo: 
O número 10 possui os seguintes múltiplos de 3 ou 5: `3, 5, 6, 9`. A soma desses múltiplos é `23`.

A resolução deste desafio encontra-se no projeto [**exercicio4**](https://github.com/marceloaprado/avaliacao/tree/main/exercicio4)

**Desafio final - Cadastro de veículos**
Criar uma aplicação back-end baseada em web services usando JSON e um front-end em modo Single Page Application que se comunique com os serviços criados no back-end.

Esta aplicação representa o gerenciamento de um cadastro de veículos e tem os seguintes requisitos:

- Permitir o cadastro de veículos
- Permitir a atualização de dados de um veículo
- Permitir a exclusão de um veículo
- Exibir a informação de quantos veículos estão como não vendidos na base
- Exibir a informação da distribuição de veículos por década de fabricação
		- Exemplo: Década 1990 -> 15 veículos
- Exibir a informação da distribuição de veículos por fabricante
		- Exemplo: Ford -> 14 veículos
- Exibir os carros registrados durante a última semana.
- Deverá haver consistência das marcas fornecidas. Não poderá haver marcas escritas de forma errada (Exemplo: Volksvagen, Forde, etc), pois não serão aceitos no cadastro.


**Missão**

Desenvolver uma API JSON RESTful, que utilize todos os métodos (GET, POST, PUT, PATCH, DELETE), bem como os testes unitários da API.

Expor os seguintes endpoints:

- `GET /veiculos` - Retorna todos os veículos
- `GET /veiculos/find` - Retorna os veículos de acordo com o termo passado como parâmetro
- `GET /veiculos/{id}` - Retorna os detalhes do veículos
- `POST /veiculos` - Adiciona um novo veículo
-  `PUT /veiculos/{id}` - Atualiza os dados de um veículo 
-  `PATCH /veiculos/{id}` - Atualiza apenas alguns dados do veículo 
-  `DELETE /veiculos/{id}` - Apaga um veículo

**Analisando o desafio**

Com base no desafio proposto, podemos fazer algumas observações:

1.  Precisamos criar uma API RESTful, e nada mais adequado do que o Spring para atender essa necessidade de forma simples e rápida.

2.  Precisamos criar uma aplicação web no modo Single Page Application, e o faremos utilizando o Angular 11, juntamente com os componentes do Angular Material. 

3.  Precisamos realizar o cadastro de veículos, manter seu histórico e consultá-los posteriormente. Para o armazenamento, utilizaremos o PostgreSQL como banco de dados, e iremos utilizar o Liquibase para criar as tabelas necessárias. Aqui o Hibernate vai facilitar ainda mais a nossa vida durante o desenvolvimento e mapeamento das entidades.

**As tecnologias para o back-end**

Antes de colocar a mão na massa, vamos entender todas as tecnológicas
mencionadas acima.

O [**Spring**](https://spring.io/) é um framework open source criado para Java, ele se baseia principalmente nos padrões de projeto Inversão de Controle e Injeção de Dependências. O Spring fornece suporte a microsserviços, cloud, web, persistência, eventos, batch, segurança, dentre outros; é um projeto enorme, e também com muitas possibilidades de personalização, por isso, ele foi dividido em várias partes, então cada projeto apenas traz aquilo que necessita.

Utilizando Spring o desenvolvimento é mais rápido e produtivo, temos em mãos inúmeras ferramentas que precisaríamos fazer manualmente. 

No Spring também temos o Spring Data JPA, que nos fornece recursos para trabalhar com persistência de dados SQL de forma simples e padronizada. O Spring utiliza o [**Hibernate**](https://hibernate.org/), um ORM que implementa todas as especificações do JPA, assim, não temos necessidade de escrever queries, controlar transações e conexões manualmente.

O principal papel do Hibernate é facilitar o mapeamento das nossas
entidades com o respectivo modelo de dados, diminuindo a complexidade de desenvolver uma aplicação Java que precisa persistir dados em um banco SQL. 

Ainda falando sobre persistência, utilizaremos o framework [**Liquibase**](https://www.liquibase.org/), uma ferramenta simples e leve para versionar nosso banco de dados.

Saindo da persistência e indo para uma camada de mais alto nível, com o
Spring utilizaremos um de seus componentes chamado **Spring Web**, com ele já teremos um servidor Tomcat para nossa aplicação REST, do
contrário teríamos que implementar tudo na mão e, ainda, configurar o
servidor. Também teremos facilidade para criar nossos endpoints apenas
com algumas anotações, e o Spring cuidará de toda parte de rotas,
serialização e desserialização dos dados nas chamadas HTTP.

Aproveitaremos para usar o [**Lombok**](https://projectlombok.org/), um framework para facilitar o dia a dia do desenvolvedor. Ele nos fornece a implementação de getters, setters, construtores, equals, hashcode, toString, etc., estes códigos básicos e repetitivos serão criados automaticamente, só precisamos indicar isto.

Entretanto, tudo isso poderia ser complicado de juntar e configurar, mas
para facilitar ainda mais temos o **Spring Boot**, que já vai deixar
tudo pronto e configurado no nosso projeto, e precisaremos fazer o
mínimo de alterações antes de rodar a aplicação. Na verdade, não
precisaríamos alterar absolutamente nada, mas como estamos usando um banco de dados externo, precisamos configurar o acesso a este banco.

Comentando com mais detalhes sobre o acesso externo ao banco de dados Postgres, foi incluído na API um arquivo chamado `docker-compose.yaml`. Esse arquivo contém instruções para que o [**Docker**](https://www.docker.com/) faça a inicialização de uma imagem do [**PostgreSQL**](https://www.postgresql.org/), já com a versão e variáveis como usuário, senha e banco de dados padrão previamente configurados.

E para finalizar esse combo poderoso de tecnologias, temos a biblioteca [**Swagger**](https://swagger.io/), que nos permite documentar a API com o mínimo de configurações necessárias. No final, com a aplicação rodando, é possível acessar a URL http://localhost:8080/swagger-ui.html e ver a mágica acontecer!

**As tecnologias para front-end**

Conforme o enunciado do desafio especifica, temos o requisito de utilizar um Framework que seja possível criar uma aplicação no modo Single Page Application. E, para isso, utilizaremos o [**Angular**](https://angular.io/)!

O Angular é uma plataforma de desenvolvimento web muito poderosa, que conta com uma comunidade muito ativa e é uma das mais utilizadas no mundo. Faremos uso dos conceitos de web components para criar nossa aplicação que irá consumir os serviços expostos na API descrita na seção anterior.


**Compilando e rodando a aplicação**

Para rodar o back-end, precisamos realizar as seguintes etapas:
- Instalar a JDK 8 do Java
- Utilizar alguma IDE de desenvolvimento, como Eclipse, STS ou IntelliJ, para importar o projeto pelo Maven
- Instalar o Docker e o Docker Compose

Para rodar o front-web, temos alguns pré-requisitos:

-  Instalar o [**Node.js**](https://nodejs.org/)
-  Instalar o gerenciador de pacotes [**npm**](https://docs.npmjs.com/getting-started) 

Felizmente, as duas aplicações, tanto back-end quanto front-end já estão configuradas em módulos do Maven, e esta última já possui as configurações de instalação do Node.js e do npm durante o build do projeto.

Após instalar o Docker e a JDK do Java 8, precisamos inicializar o container do PostgreSQL. Faremos da seguinte maneira:

- Abra o terminal ou o prompt de comando no diretório do arquivo `exercicio5-api/src/main/resources/environment`
- Execute o seguinte comando: `docker-compose up`.
No console, serão exibidos os logs do Postgres sendo iniciado. Caso não queira manter o terminal aberto, é possível usar o comando `docker-compose up -d`.
- Agora, basta compilar o projeto utilizando o seguinte comando do Maven: `mvn clean install` .  Assim, o *jar* da aplicação será criado na pasta `target` do projeto `exemplo5-api` e o mesmo pode ser executado através do comando:  `java -jar VeiculosAPI.jar`.
- O passo anterior pode ser replicado na sua IDE de preferência também.

Feito isso, é possível acessar aplicação localmente no endereço http://localhost:8080/.

A resolução deste desafio encontra-se no projeto [**exercicio5**](https://github.com/marceloaprado/avaliacao/tree/main/exercicio5)


**Extra - Deploy no Heroku**
Para facilitar a visualização e o uso da aplicação, ela foi implantada no Heroku e pode ser acessada através do link: https://avaliacao-desafios.herokuapp.com/
