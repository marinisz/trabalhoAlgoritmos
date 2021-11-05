import carteira.Portifolio;
import exceptions.opcaoInvalidaException;
import investimentos.Indicadores;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcao=-1;
//        try {
//            opcao = opcao();
//            int tipo = -1;
//            while(tipo<1 || tipo >3){
//                Scanner teclado = new Scanner(System.in);
//                System.out.println("Qual seu perfil de investidor?");
//                System.out.print("1 - Conservador\n2 - Moderado\n3 - Agressivo\nMeu perfil: ");
//                tipo = teclado.nextInt();
//                if(tipo<1 || tipo >3){
//                    System.out.println("Opção inválida");
//                }
//            }
//            System.out.println("Gerando carteira com "+opcao+" ativos: ");
            Portifolio portifolio1 = new Portifolio(6,1);
            Portifolio portifolio2 = new Portifolio(6,2);
            Portifolio portifolio3 = new Portifolio(6,3);
            System.out.println(portifolio1);
            System.out.println();
            System.out.println(portifolio2);
            System.out.println();
            System.out.println(portifolio3);
            //para mudar o perfil tem que ir no indicadores na linha 158
//        }catch (opcaoInvalidaException e) {
//            System.out.println(e);
//        }

    }
    public static int opcao() throws opcaoInvalidaException {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escolha quantos ativos deseja na sua carteira (entre 1 e 10): ");
        int opcao =-1;
        opcao = teclado.nextInt();
        if(opcao<1||opcao>10){
                throw new opcaoInvalidaException("Valor fora do permitido");
        }
        return opcao;
    }
}
