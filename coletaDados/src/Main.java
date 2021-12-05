import carteira.Portifolio;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] array = {"conservadora","moderada","agressiva","aleatoria","bruta"};
        int quantidadeAtivos=-1;
            while(quantidadeAtivos<1||quantidadeAtivos>10){
                quantidadeAtivos = quantidadeAtivos();
            }
        int tipo = -1;
            while(tipo<1 || tipo >5){
                tipo = tipoPerfil();
            }
        System.out.println("\nGerando carteira de perfil "+array[tipo-1]+" com "+quantidadeAtivos+" ativos: ");
        Portifolio portifolio1 = new Portifolio(quantidadeAtivos,tipo);
        System.out.println(portifolio1);
//

    }
    public static int quantidadeAtivos() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escolha quantos ativos deseja na sua carteira (entre 1 e 10): ");
        int opcao =-1;
        opcao = teclado.nextInt();
        if(opcao<1 || opcao >10){
            System.out.println("Opção inválida");
        }
        return opcao;
    }

    public static int tipoPerfil(){
        int tipo = -1;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual seu perfil de investidor?");
        System.out.print("1 - Conservador\n2 - Moderado\n3 - Agressivo\n4 - Carteira Aleatória\n5 - Carteira algoritmo bruto\nMeu perfil: ");
        tipo = teclado.nextInt();
        if(tipo<1 || tipo >5){
            System.out.println("Opção inválida");
        }
        return tipo;
    }
}
