# CodeNation AceleraDev Java

**Douglas Morato**

**dmoratos@gmail.com**

#### Projeto de conclusao do aceleraDev Java Online Da CodeNation

# Apoiadores

[CI&T](https://br.ciandt.com/), [Conta Azul](https://contaazul.com.br), [GuiaBolso](https://guiabolso.com.br), [PagueVeloz](https://www.pagueveloz.com.br/), [Rocket.Chat](https://rocket.chat), [Sanar](https://www.sanarmed.com/), [Nexfar](https://nexfar.com.br/#/), [Senior](https://www.senior.com.br/), [Voyager](https://www.voyagerportal.com/company/) e [Itaú](https://itau.com.br)

## Central de Erros(Event Log)

### Objetivo do Projeto

Implementar uma API Rest para centralizar registros de erros de aplicações.

### API

### Tecnologia Utilizadas

- Java
- Spring boot
- Maven
- H2
- GitHub
- SpringDoc
- QueryDsl
- Heroku

_Premissas_&nbsp;

- A API deve ser pensada para atender diretamente um front-end
- Deve ser capaz de gravar os logs de erro em um banco de dados relacional
- O acesso a ela deve ser permitido apenas por requisições que utilizem um token de acesso válido &nbsp;

_Funcionalidades_&nbsp;

- Deve permitir a autenticação do sistema que deseja utilizar a API gerando o Token de Acesso
- Pode ser acessado por multiplos sistemas
- Deve permitir gravar registros de eventos de log salvando informações de Level(error, warning, info), Descrição do Evento, LOG do Evento, ORIGEM(Sistema ou Serviço que originou o evento), DATA(Data do evento), QUANTIDADE(Quantidade de Eventos de mesmo tipo)
- Deve permitir a listagem dos eventos juntamente com a filtragem de eventos por qualquer parâmetro especificado acima
- Deve suportar Paginação
- Deve suportar Ordenação por diferentes tipos de atributos
- A consulta de listagem não deve retornar os LOGs dos Eventos
- Deve permitir a busca de um evento por um ID, dessa maneira exibindo o LOG desse evento em específico
