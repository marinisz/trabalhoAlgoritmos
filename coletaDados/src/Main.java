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
        int opcao = -1;
            while(opcao<1||opcao>2){
                opcao = querCompararPortifolios();
            }
        if(opcao==1){
            compararPortifolios(portifolio1,tipo);
        }

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

    public static int querCompararPortifolios(){
        System.out.println("Deseja comparar sua carteira com os demais perfis?");
        System.out.println("1)Sim \n2)Não");
        Scanner teclado = new Scanner(System.in);
        int opcao = teclado.nextInt();
        if(opcao<1 || opcao >2){
            System.out.println("Opção inválida");
        }
        return opcao;
    }

    public static void compararPortifolios(Portifolio portifolio, int tipo) throws IOException {
        String[] array = {"conservadora","moderada","agressiva","aleatoria","bruta"};
        int tamanho = portifolio.getAtivos().length;
        Portifolio portifolio1 = null;
        Portifolio portifolio2 = null;
        Portifolio portifolio3 = null;
        Portifolio portifolio4 = null;
        Portifolio portifolio31 = null;

        switch (tipo){
            //conservador
            case 1:
                portifolio1 = new Portifolio(tamanho,tipo+1);
                portifolio2 = new Portifolio(tamanho,tipo+2);
                portifolio3 = new Portifolio(tamanho,tipo+3);
                portifolio31 = new Portifolio(tamanho,tipo+3);
                portifolio4 = new Portifolio(tamanho,tipo+4);
                System.out.println("Carteira "+array[1]+"\n"+portifolio1);
                System.out.println("\nCarteira "+array[2]+"\n"+portifolio2);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio3);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio31);
                System.out.println("\nCarteira "+array[4]+"\n"+portifolio4);
                return;
                //moderado
            case 2:
                portifolio1 = new Portifolio(tamanho,tipo-1);
                portifolio2 = new Portifolio(tamanho,tipo+1);
                portifolio3 = new Portifolio(tamanho,tipo+2);
                portifolio31 = new Portifolio(tamanho,tipo+2);
                portifolio4 = new Portifolio(tamanho,tipo+3);
                System.out.println("Carteira "+array[0]+"\n"+portifolio1);
                System.out.println("\nCarteira "+array[2]+"\n"+portifolio2);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio3);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio31);
                System.out.println("\nCarteira "+array[4]+"\n"+portifolio4);
                return;
                //agressivo
            case 3:
                portifolio1 = new Portifolio(tamanho,tipo-2);
                portifolio2 = new Portifolio(tamanho,tipo-1);
                portifolio3 = new Portifolio(tamanho,tipo+1);
                portifolio31 = new Portifolio(tamanho,tipo+1);
                portifolio4 = new Portifolio(tamanho,tipo+2);
                System.out.println("Carteira "+array[0]+"\n"+portifolio1);
                System.out.println("\nCarteira "+array[1]+"\n"+portifolio2);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio3);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio31);
                System.out.println("\nCarteira "+array[4]+"\n"+portifolio4);
                return;
                //aleatorio
            case 4:
                portifolio1 = new Portifolio(tamanho,tipo-3);
                portifolio2 = new Portifolio(tamanho,tipo-2);
                portifolio3 = new Portifolio(tamanho,tipo-1);
                portifolio4 = new Portifolio(tamanho,tipo+1);
                System.out.println("Carteira "+array[0]+"\n"+portifolio1);
                System.out.println("\nCarteira "+array[1]+"\n"+portifolio2);
                System.out.println("\nCarteira "+array[2]+"\n"+portifolio3);
                System.out.println("\nCarteira "+array[4]+"\n"+portifolio4);
                return;
                //bruto
            case 5:
                portifolio1 = new Portifolio(tamanho,tipo-4);
                portifolio2 = new Portifolio(tamanho,tipo-3);
                portifolio3 = new Portifolio(tamanho,tipo-2);
                portifolio31 = new Portifolio(tamanho,tipo-1);
                portifolio4 = new Portifolio(tamanho,tipo-1);
                System.out.println("Carteira "+array[0]+"\n"+portifolio1);
                System.out.println("\nCarteira "+array[1]+"\n"+portifolio2);
                System.out.println("\nCarteira "+array[2]+"\n"+portifolio3);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio31);
                System.out.println("\nCarteira "+array[3]+"\n"+portifolio4);
                return;
        }

    }
}
