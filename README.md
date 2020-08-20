# Relatório de Vendas

Criar um sistema de análise de dados de venda que irá importar lotes de arquivos e produzir um relatório baseado em informações presentes no mesmo.

Existem 3 tipos de dados dentro dos arquivos e eles podem ser distinguidos pelo seu identificador que estará presente na primeira coluna de cada linha,
onde o separador de colunas é o caractere "ç".

● **Dados do vendedor**: Os dados do vendedor possuem o identificador 001 e seguem o seguinte formato:

	001çCPFçNameçSalary
	
● **Dados do cliente**: Os dados do cliente possuem o identificador 002 e seguem o seguinte formato: 

	002çCNPJçNameçBusiness Area
	
● **Dados de venda**: Os dados de venda possuem o identificador 003 e seguem o seguinte formato: 

	003çSale IDç[Item ID-Item Quantity-ItemPrice]çSalesman name
	
Exemplo de conteúdo total do arquivo:

	001ç1234567891234çPedroç50000
	001ç3245678865434çPauloç40000.99
	002ç2345675434544345çJose da SilvaçRural
	002ç2345675433444345çEduardo PereiraçRural
	003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
	003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo	
	
O sistema deverá ler continuamente todos os arquivos dentro do diretório padrão HOMEPATH/data/in e colocar o arquivo de saída em HOMEPATH/data/out.

No arquivo de saída o sistema deverá possuir os seguintes dados:

● Quantidade de clientes no arquivo de entrada

● Quantidade de vendedores no arquivo de entrada

● ID da venda mais cara

● O pior vendedor

## Requisitos Técnicos:

● O sistema deve rodar continuamente e capturar novos arquivos assim que eles sejam inseridos no diretório padrão.

● Você tem total liberdade para escolher qualquer biblioteca externa se achar necessário.

## Desenvolvimento

● [Java 11](https://www.oracle.com/java/)

● [Spring Boot](https://spring.io/projects/spring-boot)

● [Spring Batch](https://spring.io/projects/spring-batch)

● [Spring Data](https://spring.io/projects/spring-data)

● [Maven](http://maven.apache.org/)

● [Lombok](https://projectlombok.org)

● [IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/)


## Instalação
    
	$ git clone git@github.com:marciafc/sales-report.git
		
	$ cd sales-report
	
	$ mvn spring-boot:run
	
## Testando a aplicação

Copiar o arquivo HOMEPATH/data/temp/data.dat para HOMEPATH/data/in

Verificar geração do arquivo HOMEPATH/data/out/report.data
		
Acessar banco de dados: http://localhost:8080/h2
	
## Melhorias

● Criar databases diferentes para separar os dados do negócio das tabelas de controle do batch

● Incluir serviço de Health Check (/health) para verificar a "saúde" da aplicação

● Incluir o Spring Boot Actuator para monitoramento e métricas da aplicação

● Ajuste na configuração para instalar via docker-compose up

● Melhorar cobertura de testes	