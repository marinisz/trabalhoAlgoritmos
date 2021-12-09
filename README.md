# Descrição

O presente trabalho está tem como objetivo o de construir uma ferramenta computacional capaz de auxiliar alguém na construção de um portfólio de investimento. Especificamente, trabalharemos com uma pequena base de dados real do mercado imobiliário.

# Como funciona?

1. Leva os dados do csv para o programa;
2. Percorre o csv armazenando ações de mesmo papel em lista de listas;
3. Percorre cada lista gerando os indicadores.
4. Com os indicadores registrados, gera uma nota para cada ativo de 0 à 9 baseando em cada indicativo;
5. Quatro métodos de ordenação para criar o portifólio, conservador, moderado , agressivo e bruto.
  - Conservador -> baixo risco;
  - Moderado -> mesmo peso para todos indicadores;
  - Agressivo -> foco na rentabilidade
  - Bruto -> Foco retorno mas com algoritmo bruto
6. O usuário escolhe a quantidade de ativos e o seu perfil;
7. O programa gera um portifólio e calcula seu risco e retorno;

(obs: considerei os indicadores comprando e vendendo de um dia para o outro)

# Docker

docker pull marinisz/algoritmos:1.0


![image](https://user-images.githubusercontent.com/57442852/142782536-1c5764f7-f96c-4e59-b819-ca73ba7c64ba.png)
