# Memory Game
Memory Game é uma aplicação de jogo de memória que possui 3 níveis de dificuldade e guarda os dados de jogo de cada usuário cadastrado.
## Sobre o projeto
Foi escolhido o modelo de padrão de projeto MVC (Model-View-Controller) para elaborar a aplicação.
Model representa os dados do aplicativo e a lógica de negócios, o View controla a camada de apresentação (interfaces do usuário)
e o Controller gerencia o fluxo de dados entre o Model e o View.

Para mais informações desse padrão de projeto : https://www.devmedia.com.br/padrao-mvc-java-magazine/21995

## Como executar o projeto ?
### Pré-requisitos:
* Java 17 : https://www.oracle.com/br/java/technologies/downloads/#java17

* SQLite : https://www.sqlite.org/download.html

* DB Browser for SQLite : https://sqlitebrowser.org/dl/ (para visualização do banco de dados)

* Para compilar pode ser utilizado o editor de sua preferência, a aplicação foi feita através do Intellij

### Dentro da aplicação:
**Se não criar um usuário no jogo pode entrar com o meu usuário**

* Use o username: arthurccamargo 

* Use a password: 123456

## Tecnologias utilizadas
* Java
* CSS
* Maven
* SQLite

## Imagens
**Se não criar um usuário pode entrar com o meu usuário**

* Use o username: arthurccamargo 

* Use a password: 123456

![Login](https://github.com/arthurccamargo/Memory-Game/assets/118135851/1056da81-1a0f-4819-8496-764748903d71)

Criar um Usuário
* Deve criar um jogador com um nome diferente dos já criados, cada jogador possui um nome único

![Create_user](https://github.com/arthurccamargo/Memory-Game/assets/118135851/cd67991d-992e-466d-aab7-d6bd4e6a5fa5)

Dados do Jogador

![Home](https://github.com/arthurccamargo/Memory-Game/assets/118135851/71341599-a5b9-4567-b66b-21d52aa06123)

Jogo no Modo Fácil

![Game](https://github.com/arthurccamargo/Memory-Game/assets/118135851/77d4a3a5-e760-4f67-a725-78b77ba603e4)

Aviso ao Jogador: 
* Se o jogador mudar de janela ao ir para Home ou mudar o modo de jogo em Game, o jogador perderá a partida

![Warning](https://github.com/arthurccamargo/Memory-Game/assets/118135851/8200d700-5278-417f-8708-da76ce50c09e)




## Próximos Passos
* Criar os outros dois níveis de dificuldade: médio e difícil
* Criar um ranking de todos jogadores da aplicação 

