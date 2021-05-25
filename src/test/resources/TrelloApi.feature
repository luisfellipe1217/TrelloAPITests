#language:pt

Funcionalidade: Manipulação de Cards
Eu como um usuário do Trello
Quero realizar o cadastro e edição de Cards 
Para administrar meu tempo e organização de trabalho

Contexto:
Dado que estou acessando a integração com o Trello
Quando informo as informações para autenticação
Então devo receber o acesso com a validação do Token para meu usuário


Cenario: Adicionar novo Card
Dado que estou com acesso ao Trello
Quando solicito para cadastrar um novo card
E preencho as informações necessárias
Então é inserido um novo card ao meu Quadro

Cenario: Editar Cards
Dado que tenho um card cadastrado
Quando solicito uma alteração 
E envio as informações a serem alteradas
Então é realizada a alteração no card.



