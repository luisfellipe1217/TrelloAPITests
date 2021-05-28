#language:pt

Funcionalidade: Manipulação de Cards
Eu como um usuário do Trello
Quero realizar o cadastro e edição de Cards 
Para administrar meu tempo e organização de trabalho


Esquema do Cenario: Adicionar novo Card
Dado que estou com acesso ao Trello
Quando solicito para cadastrar um novo card
E preencho as informações necessárias "<name>"  "<descricao>"  "<idList>"
Então é inserido um novo card ao meu quadro "<name>" "<descricao>" "<idList>"

Exemplos:
|   name													| descricao				          																			| 															idList																| 		
|  		TESTE APITrello			|	Teste de Api para desafio SenseDia				|  	60b129f0081ded8843d961de						|			


Cenario: Não deve adicionar card sem informação
Dado que estou com acesso ao Trello
Quando solicito para cadastrar um novo card
E não preencho as informações obrigatórias 
Então não deve ser inserido um novo card

Esquema do Cenario: Editar Cards
Dado que tenho um card cadastrado "<idCard>"
Quando solicito uma alteração "<idCard>" "<descricao> " "<name>"
E envio as informações a serem alteradas 
Então é realizada a alteração no card."<idCard>" "<descricao> " "<name>"

Exemplos: 
|idCard                                           |        descricao            |          name                                     | 
|60b1621e5a04093dc4b345c6|     Teste Sensedia     |       		Alteração PUT											|

