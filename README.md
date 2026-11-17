# Campeonato_Robos_pt2
(RODAR A PARTIR DO README) 


TESTE 1: 
OFENSIVO CONTRA DEFENSIVO
CADASTRANDO OFENSIVO
<img width="935" height="890" alt="image" src="https://github.com/user-attachments/assets/b098a5dc-b717-4897-82a1-533d290f68c9" />

CADASTRANDO DEFENSIVO e BATALHA


<img width="526" height="902" alt="image" src="https://github.com/user-attachments/assets/e0089f67-b891-4181-b30f-bab504739388" />
<img width="581" height="369" alt="image" src="https://github.com/user-attachments/assets/742f4eaa-57e1-414f-bc03-822af102eafb" />


Na primeira rodada, como Carlos é ofensivo, ele recebe +5 no ataque, passando de 20 para 25. Depois, subtraindo os 10 de defesa do Joao, o dano fica em 15.

Porém, como Joao é defensivo, ele reduz 3 do dano recebido.

Cálculo: 20 + 5 - 10 - 3 = 12 de dano.

Já na segunda rodada, o dano aumentou para 17, pois meu sistema acrescenta mais 5 nas rodadas pares, antes de aplicar a redução do defensivo.

Cálculo: 20 + 5 - 10 + 5 - 3 = 17 de dano.

No final das 5 rodadas, Carlos venceu, terminando com 40 de energia, enquanto Joao ficou com 30.

TESTE 2: 

OFENSIVO CONTRA EQUILIBRADO

CADASTRANDO OFENSIVO e VALIDAÇÕES: 


<img width="477" height="904" alt="image" src="https://github.com/user-attachments/assets/f33a55d8-83a1-4f34-8266-a48867dc19f9" />


CADASTRANDO EQUILIBRADO E BATALHA: 

<img width="479" height="902" alt="image" src="https://github.com/user-attachments/assets/cd9dd806-70e5-4a50-a836-3fecfe5ab5de" />



<img width="331" height="347" alt="image" src="https://github.com/user-attachments/assets/c31358c1-3e78-445d-b0c9-e454747a5d82" />



Nesse segundo teste, coloquei o Felipe (Ofensivo) contra o Pedro (Equilibrado), ambos com 20 de ataque e 10 de defesa.

Na primeira rodada, como Felipe é ofensivo, ele recebe +5 no ataque, passando de 20 para 25. Depois, subtraindo os 10 de defesa do Pedro, o dano fica em 15.

Como Pedro é equilibrado, ele não possui nenhuma redução adicional de dano.

Cálculo: 20 + 5 - 10 = 15 de dano.

Já na segunda rodada, o dano aumentou para 20, pois meu sistema acrescenta mais 5 de dano nas rodadas pares.

Cálculo: 20 + 5 - 10 + 5 = 20 de dano.

No final das 5 rodadas, Felipe venceu, terminando com 40 de energia, enquanto Pedro ficou com 15.

Com esse teste, consegui verificar que o bônus do ofensivo está funcionando e que o equilibrado continua utilizando as regras normais de combate, sem modificadores adicionais.





TESTE 3: 


DEFENSIVO CONTRA EQUILIBRADO: 

CADASTRANDO DEFENSIVO: 


<img width="482" height="885" alt="image" src="https://github.com/user-attachments/assets/140c5ede-b65e-4b86-a126-58e438ac03bd" />


CADASTRANDO EQUILIBRADO E BATALHA: 

<img width="483" height="911" alt="image" src="https://github.com/user-attachments/assets/7cf449ab-cc0f-46ac-959a-cb18502e98a4" />



<img width="442" height="763" alt="image" src="https://github.com/user-attachments/assets/4aae3da2-4cfb-4461-a99e-2dccc36bac89" />






Nesse terceiro teste, coloquei o CARLOTRON3000 (Defensivo) contra o DANIELTRON (Equilibrado), ambos com 20 de ataque e 10 de defesa.

Na primeira rodada, CARLOTRON3000 atacou primeiro e causou 10 de dano. Porém, para demonstrar o comportamento do defensivo, vou utilizar o golpe do DANIELTRON contra ele.

Como DANIELTRON é equilibrado, seu ataque continua sendo 20. Subtraindo os 10 de defesa do CARLOTRON3000, o dano fica em 10.

Porém, como CARLOTRON3000 é defensivo, ele reduz 3 do dano recebido.

**Cálculo: 20 - 10 - 3 = 7 de dano.**

Já na segunda rodada, DANIELTRON causou 12 de dano, pois meu sistema acrescenta mais 5 nas rodadas pares antes de aplicar a redução do defensivo.

**Cálculo: 20 - 10 + 5 - 3 = 12 de dano.**

No final das 5 rodadas, CARLOTRON3000 venceu, terminando com 55 de energia, enquanto DANIELTRON ficou com 40.

Com esse teste, consegui verificar que o robô defensivo reduz o dano recebido, enquanto o equilibrado continua atacando normalmente. Também confirmei que a redução defensiva acontece depois do bônus das rodadas pares.









-------------------------------------------------------------------------------

*APLICAÇÃO DE HERANÇA, POLIMORFISMO E ENCAPSULAMENTO NO MEU TRABALHO:*

1- HERANÇA


Para aplicar herança no meu trabalho, comecei realizando alterações na classe Robo, transformando ela em uma classe abstrata, como no enunciado.. depois criei 3 classes novas: RoboOfensivo, RoboDefensivo e 
RoboEquilibrado.

Nessas três classes utilizei o extends Robo, porque dessa forma elas conseguem herdar os atributos e métodos que já existem na classe principal, por exemplo: nome, código, ataque, defesa etc..

Também utilizei o super nos construtores das três classes, para chamar o construtor da classe Robo e inicializar as formas que são comuns entre os participantes..

Dessa forma, não fiquei repetindo os mesmos atributos e metódos em cada classe, pois essas infos já estão na classe mãe. 



2- Polimorfismo: 


O polimorfismo foi aplicado principalmente no meu método atacar(), que eu já tinha criado na primeira versão do campeonato. 
Antes, todos os robô utilizavam exatamente o mesmo cálculo de dano, agora precisei adaptar esse método para considerar o comportamento dos três tipos de robôs..
Pra isso, criei métodos: ataqueNoGolpe() e ajustarDanoRecebido() na classe robo. 

Na classe RoboOfensivo utilizei o @Override para sobrescrever o método atacarnogolpe, acrescentando 5 ao ataque durante o cálculo, sem alterar o valor original do atributo. 


Já na classe robo defensivo sobrescrevi  o metodo ajustardanorecebido fazendo com que esse tipo de robô reduza 3 do dano recebido respeitando o minimo de 2. 

O  RoboEquilibrado continua utilizando o comportamento normal da classe Robo, sem modificações adicionais no ataque ou na defesa.

O que eu achei interessante nessa parte é que não precisei criar um método de combate separado para cada tipo. Continuei utilizando meu método atacar(), mas agora ele chama os métodos correspondentes ao objeto que está participando do combate.

Dessa forma, também não precisei utilizar instanceof ou ficar fazendo if para descobrir o tipo de robô durante o cálculo do dano.

3- Encapsulamento: 

Para aplicar o encapsulamento, comecei alterando os atributos da classe Robo, que antes eram public e agora estão todos como private.

Como os atributos ficaram privados, não posso mais acessar diretamente essas informações pela Main, como fazia anteriormente.

Por isso, utilizei os métodos get, como getCodigo(), getNome(), getAtaque(), getEnergia() e getPontos(), para conseguir consultar essas informações.

Também mantive os métodos que já tinha desenvolvido, como vencer(), perder(), empatar(), receberDano() e recuperarEnergia(), pois são eles que fazem as alterações de energia, pontos e resultados, respeitando as regras do campeonato.

Outra alteração que fiz foi no construtor da classe Robo. Antes ele recebia também energia, vitórias, derrotas, pontos e combates. Agora ele recebe somente código, nome, ataque e defesa.

A própria classe define que cada robô começa com 100 de energia e as estatísticas zeradas.

Fiz essa alteração para impedir que esses valores iniciais sejam escolhidos livremente durante a criação dos robôs.


