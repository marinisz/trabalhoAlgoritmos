package buscador;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RetornaDados {
    List<String[]> dados = this.leitor("dados/dados.csv");

    public RetornaDados() throws IOException {
    }

    public List<String[]> getDados() {
        return dados;
    }

    public static List<String[]> leitor(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        List<String[]> retorno = new ArrayList();
        while (true) {
            if (linha != null) {
                String[] dados = linha.split(",");
                if(dados[0]!="")
                retorno.add(dados);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return retorno;
    }

}
