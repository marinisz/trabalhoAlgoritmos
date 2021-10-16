import exceptions.opcaoInvalidaException;
import investimentos.Indicadores;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Indicadores indicador = new Indicadores();
        indicador.calculaIndicadores();
        int opcao=-1;
//        try {
//            opcao = opcao();
//            System.out.println("Gerando carteira com "+opcao+" ativos: ");
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
