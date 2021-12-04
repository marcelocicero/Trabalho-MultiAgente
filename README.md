# Trabalho Prático de Sistemas Multiagentes
<br/><br/>

<center> PONTÍFICIA UNIVERSIDADE CÁTOLICA DE MINAS GERAIS</center>
<br/><br/>
<p style='text-align: right;'>¹Eduardo Felipe Lopes
<p style='text-align: right;'>²Marcelo Cícero Do Nascimento Maia
<p style='text-align: right;'>³Hugo Basto De Paula

###### _____________________________________
<p style="font: times ; font-size:6pt">
   1 Graduando em Engenharia de Computação -eflopes@sga.pucminas.br Brasil. 
</p>
<p style="font: times ; font-size:6pt">
   2	Graduando em Engenharia de Computação - marcelo.cicero@sga.pucminas.br Brasil. 
<p style="font: times ; font-size:6pt">
   3 Doutor em Engenharia Elétrica -hugo@sga.pucminas.br Brasil. 
</p>


<br/><br/>
<br/><br/>

### **1  Introdução**

 <p align="justify">Diante disso, este trabalho tem como objetivo aliar os conceitos teóricos de sistemas multi-agentes, atrelado a utilização de um ambiente simulado, open source, em linguagem JAVA, onde é possível codificar um robô para competir contra outros robôs numa arena de batalhas. 
Segundo Russel e Norvig, 2003 agente é definido como qualquer entidade que pode perceber um ambiente através de sensores e agir através de atuadores. Um sistema de multi-agente é definido como conjunto de agentes que interagem em um ambiente comum, onde consiste em um conglomerado de agentes ativos formando um grupo, ou seja, onde dois ou mais interagem e desempenham funções capazes de realizar o objetivo pelo sistema que estão inseridos. Estes sistemas incluem vários agentes que trabalham em conjunto, onde cada um é capaz de resolver de forma autônoma a sua função, e consequentemente operam de forma sincronizada com os outros membros do grupo, tornando-se assim parte de um sistema maior, sendo desejável que os agentes participantes do sistema tenham algumas características específicas.
<p align="justify">
Segundo Jones, Bartlett, 2004 aponta que os agentes inteligentes são sistemas computacionais que têm como principais características a percepção do ambiente onde estão inseridos, adaptação às mudanças, como também a capacidade de trabalhar em função dos objetivos, podendo ser atribuídas aos agentes, e diante disso, podendo dispor a combinar sistemas diferentes, com intuito de desenvolver um sistema onde combinados podem juntos combinar mecanismo de dispor a obter um resultado com maior produtividade, combinados  para resolução de problemas. 
 <p>
 
 <p align=justify>Diante disso, este trabalho tem como objetivo aliar os conceitos teóricos de sistemas multiagentes, atrelado a utilização de um ambiente simulado, open source, em linguagem JAVA, onde o objetivo é codificar um robô para competir contra outros robôs numa arena de batalhas. 
Segundo Russel e Norvig, 2003 agente é definido como qualquer entidade que pode perceber um ambiente através de sensores e agir através de atuadores.
 </p>
<br/><br/>
 
 
 
 
 
### **2   Objetivos**
<br/><br/>
<p align="justify">
O trabalho prático de sistemas multi-agentes tem o objetivo de desenvolver um tanque de batalha. Onde o robô que irá guerrear com outros tanques, onde será elaborado um campeonato e consistirá na construção de um time robôs. Dentre as equipes participantes com a estratégia de ataque e defesa, com o intuito a fim de se manterem vivos e ganharem as batalhas realizadas.</p>
<br/><br/>
 
### **3   Modelo de Agentes**
<br/><br/>
<p align="justify"> Para elaboração do modelo de agentes foi promovido a priori em consonância das aulas teóricas de inteligência artificial onde foi disposto a elaboração dos agentes contendo a base para promover um modelo. Diante disso, cada agente é representado por um robô tanque de guerra cujo objetivo é derrotar seu oponente durante uma batalha. É definida a composição do time onde possui 3 robôs, onde temos o líder e seus aliados. Os robôs aliados possuem estratégias de ataque e defesa, a fim de se manterem vivos e ganharem as batalhas realizadas. O líder tem um papel fundamental onde a partir do seu comando definido é repassado para o seus aliados com a promoção de cooperação, é definido que objetivo do líder seja guiá-los e transpor a direção. Com intuito de definir as estratégias para derrotar o time adversário, onde a partir da comunicação do líder com seus aliados decidem suas ações, e deve ser escalonado essa estratégia.
 
 <p align="justify">Segundo Russel e Norvig (2004), aborda que podem possuir vários aspectos diferentes, dentre elas podemos ter: autonomia de comportamento, que irá definir como o agente irá reagir em momentos decisórios, flexibilidade para que com dinamismo consiga escolher a ação e a forma de execução da tarefa, inteligência que irá proporcionar ao agente como executar a ação, uma vez que seu desenvolvimento ocorre através das regras de evolução em que está submetido.A possibilidade de movimentação que um agente terá em um determinado ambiente pode defini-lo como sendo estático ou móvel. Agentes que atuam localmente sem a possibilidade de mobilidade são denominados agentes estáticos, um exemplo pode ser observado em agentes instalados localmente em uma rede que esperam determinado evento ocorrer para dispararem algum tipo de ação. Por sua vez, os agentes móveis podem executar a mesma operação com o diferencial de possuírem a mobilidade para trafegarem na rede à procura de eventos estabelecidos.
 
<p align="justify">Diante desses aspectos existem vários tipos de agentes, desde os mais simples ou mais elaborados. Onde o agente de reflexo simples ou agente reativo simples, escolhe a melhor ação com base no que está sendo transmitido naquele momento, ou seja, não leva em conta os demais acontecimentos. O agente fica aguardando um evento específico acontecer, e, quando acontece, ele é ativado, ou seja, possui determinado comportamento quando estimulado. E a partir disso é possível estender um pouco mais e outras  variações que incluem a qual necessita obter as informações de acontecimentos passados e analisar alguns aspectos que ainda não tenham acontecido no estado atual. Chegando até agentes que têm um ou uma série de objetivos que são utilizados  para executar e sua percepção do ambiente é superior, pois, ele é capaz de considerar ações com a meta de alcançar seus objetivos. Como foi definido neste trabalho são utilizados um conjunto de tipos de agentes para que pudéssemos criar as estratégias.
 
<p align="justify">Contudo, é utilizado neste trabalho como foi abordado anteriormente o conjunto de tipos, onde  assim são formando os multi-agentes que promovem a eficácia dos mesmos. É promovido a utilização de agente baseado em utilidade  onde é semelhante em objetivo, mas, além de tentar alcançar um conjunto de objetivos, também tenta maximizar algum valor de utilidade. Portanto podemos definir o valor de utilidade pode ser pensado como a felicidade do agente ou quão bem-sucedido ele está sendo. Também pode ser levado em consideração quanto trabalho o agente precisa realizar a fim de alcançar seus objetivos, para fins de esclarecimento agente baseado em utilidade, podemos citar um carro que necessita chegar a um destino, o qual existe várias sequências de ações para alcançá-los, sendo algumas mais seguras, rápidas, confiáveis e econômicas que outras. 
</p>
<p align="justify">
Podemos definir onde os agentes serão aplicados. Estabelecendo onde serão estabelecidas as estratégias no capítulo 3 definido ataque, defesa, e a comunicação dos agentes.  Gilliard e Lopes (2010), exemplificam que uma das formas de representação de comportamento mais comuns é através de regras de inferência, onde o comportamento do agente consiste em uma coleção de regras condicionais, cujo antecedente (ou condição) da regra representa um conjunto de condições que denotam a situação atual da partida, e o consequente (ou conclusão) representa uma ação a ser tomada pelo agente.
</p>
<br/><br/>
 
### **4  Descrições dos robôs e estratégias**
<br/><br/>
<br/><br/>
<p align="justify">
As batalhas são divididas em rodadas e os tanques recebem pontos de acordo com um sistema de pontuação. Vence a batalha aquele que acumular mais pontos. Os robôs são controlados por programas em Java. 
 
              Estratégias para o desenvolvimento e classificação
<br>
<p align="justify">Os tipos de modelos dos robôs são classificados como multiagentes onde o robô líder é descrito como o personagem principal. O objetivo consiste na comunicação direta com seus aliados permitindo que caso o robô detectado pelo radar esteja mais perto do nosso time, fazendo-o identificar qual é o alvo a ser atingido. Diante disso, é analisada também uma margem segura capaz de identificar que um dos robôs do time inimigo, está mais perto do que o inimigo anterior por exemplo ou não temos nenhuma instância inimiga. Assim é realizado a comunicação com dos robôs denominado seguidor transmitindo as coordenadas de qual inimigo precisa atirar, isso permite também que não atire se o robô que está na nossa frente pertence ao mesmo time ou caso contrário ataque o robô inimigo. Para o desenvolvimento é utilizado os conhecimentos prévios de agente, baseado em objetivo. Deste modo, existem outras propriedades elencadas sobre esse robô onde é descrito a propriedade do ambiente do tipo determinístico que consiste em sensores que fornece acesso ao estado completo do ambiente, ou seja, o próximo estado do ambiente é completamente determinado pelo estado atual e pela ação executada pelo agente.  
</p>
 
<p align="justify">
   No processo de desenvolvimento do robô algumas estratégias foram adotadas  como o uso do radar que é acoplado ao canhão, onde objetivo das estratégias de controle  é de detectar o mais frequentemente possível os robôs presentes na arena. O robocode fornece uma implementação bem simples de robô como ponto do de partida de desenvolvimento, o MyfirstRobot. Outros robôs foram disponibilizados em pacotes de exemplo e serviram de inspiração na elaboração da estratégia. Com isso foram adicionadas estratégias como o escaneamento preso, que consiste em que como o radar demora 8 turnos para escanear toda a área. Caso o robô esteja em uma batalha contra apenas um outro robô, o radar terá, em geral, eficiência de 12,5%, adotando o escaneamento básico. Alternar o sentido de rotação do radar toda vez que um robô é detectado, fará com que o radar fique preso detectado a mesma seção angular enquanto o inimigo estiver nessa área, para aprimorar essa estratégia foi adicionado a estratégia de escaneamento seguro, onde apesar do ganho significativo de eficiência, não é raro o inimigo sair da área varrida com o escaneamento preso. Para aprimorar é preciso calcular a distância do radar até o robô inimigo e adicionar uma distância para além do robô.
   </p>
   <br>
 
 
 
                  Estratégias de controle do canhão
<br>
   <p align="justify">
 Uma vez que a manutenção da aproximação do radar não é mais feita com valores infinitos, é adicionado uma condição no loop principal, para que o radar rotacione infinitamente sempre que não existir um movimento agendado para o próximo turno. A distância segura a ser adicionada ao ângulo entre o radar e o inimigo é inversamente proporcional à distância que ele se encontra do robô, ou seja, quanto mais próximo o inimigo estiver do robô, mais aberto o ângulo do radar deve ser mantido. De modo que seja coberto as dimensões de um robô, 36 pixels, a mais de cada lado. As estratégias de controle do canhão têm por objetivo acertar os inimigos com os tiros. Dado um alvo escolhido, a tarefa deste controlador é apontar o canhão numa direção tal que ao realizar um disparo, o alvo seja atingido. A estratégia mais simples de controlar o canhão já é implementada quando se cria o MyFirstRobot. 
 </p>
 
<p align="justify">
O canhão é mantido acoplado ao radar, que estará sempre girando. Assim que um inimigo é detectado, o canhão é disparado. Portanto, foi adicionado um conceito importante que é usado como estratégias tanto de movimentação, quanto de mira é o do máximo ângulo de escape: No momento em que um tiro é disparado por um robô A, existe um setor tal que independente da movimentação, um inimigo B não conseguirá sair dele antes de uma bala, com velocidade v, percorrer a distância inicial. Tomando como eixo central o raio que parte do robô A até o robô B, o arco do eixo até o fim do setor, projetado pelo máximo ângulo de escape, é a maior distância que o robô B pode percorrer no sentido horário ou anti-horário, conseguida quando este movimenta-se perpendicular ao robô A. Uma vez que a velocidade máxima do robô é de 8 pixels/turno, o máximo ângulo de escape será arcsin(8/v). Este método de controle do canhão consiste em dividir o máximo ângulo de escape em uma lista de setores, tratados como guess factors, e disparar um tiro na direção cujo setor tenha sido mais visitado com base nas experiências anteriores.
</p>
 
<p align="justify">
As visitas são contadas no momento tf em que a distância viajada pela bala ultrapassa a distância entre o canhão e o inimigo no momento t0 do disparo. Neste momento, a posição no vetor referente ao setor em que o inimigo se encontra é incrementado. Desse modo, cada vez que o inimigo é escaneado, o setor mais visitado até então é selecionado e convertido em ângulo normalizado pelo máximo ângulo de escape. As informações do disparo são armazenadas para que no momento tf seja possível resgatar as informações relevantes de t0. São elas: as coordenadas do canhão; o sentido do inimigo; a força do tiro; o bearing do inimigo e o momento do tiro.
</p>
 
                   Estratégia do controle do robo
<p align="justify">
 Para o controle do robô foi adicionado a estratégia onde o objetivo é desviar dos projéteis inimigos, denominado Wave Surfing. O radar é incapaz de detectar balas no campo de batalha, tampouco consegue detectar caso um robô inimigo tenha realizado um disparo, mas isso pode ser inferido a partir da leitura contínua de sua energia. Sabendo que um disparo gasta entre 0.1 e 3.0 pontos de energia do robô, quando uma baixa de tal magnitude for detectada em um inimigo, é provável que este tenha realizado um disparo. Algumas alternativas geram falsos-positivos: colisão com parede ou outros robôs ou algum dano ocasionado por projétil com f ≤ 0.75.Uma vez disparado, não sabemos a direção do tiro, mas sabemos o momento t0 do disparo e sabemos a velocidade v da bala (calculada com base em sua força), ou seja, a bala pode estar em qualquer ponto de uma circunferência com centro na posição a qual o robô estava quando disparou o tiro e raio (t − t0) × v.
</p>




<br/><br/>
<br/><br/>


### **4  Referências**
RUSSEL, S., NORVIG, P. “Inteligência Artificial”. Rio de Janeiro: Elsevier. 2004.

RUSSEL, S., NORVIG, P. “Inteligência Artificial”. Rio de Janeiro: Elsevier. 2003.

JONES& BARTLETT, Bem,Cppin.“Inteligência Artificial”.GrupoEditoraNacional, 
2004.
