import carteira.Portifolio;

import java.io.IOException;
import java.util.Scanner;

public class ParaActions {
    public static void main(String[] args) throws IOException {
        String[] array = {"conservadora","moderada","agressiva","aleatoria","bruta"};
        Portifolio portifolio1 = new Portifolio(5,1);
        Portifolio portifolio2 = new Portifolio(5,2);
        Portifolio portifolio3 = new Portifolio(5,3);
        Portifolio portifolio31 = new Portifolio(5,3);
        Portifolio portifolio4 = new Portifolio(5,4);
        Portifolio portifolio5 = new Portifolio(5,5);

        System.out.println("Carteira "+array[0]+"\n"+portifolio1);
        System.out.println("\nCarteira "+array[1]+"\n"+portifolio2);
        System.out.println("\nCarteira "+array[2]+"\n"+portifolio3);
        System.out.println("\nCarteira "+array[3]+"\n"+portifolio31);
        System.out.println("\nCarteira "+array[3]+"\n"+portifolio4);
        System.out.println("\nCarteira "+array[4]+"\n"+portifolio5);

//

    }
}
