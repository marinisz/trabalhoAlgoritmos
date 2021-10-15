package investimentos;

import buscador.RetornaDados;

import java.io.IOException;
import java.util.*;

public class Indicadores {
    RetornaDados buscado = new RetornaDados();
    List<String[]> dados = this.buscado.getDados();
    public Indicadores() throws IOException {
    }

    public RetornaDados getBuscado() {
        return buscado;
    }

    public List<String[]> getDados() {
        return dados;
    }

    public void retornoEfetivo(){
        List<String[]> umFundo = new ArrayList<String[]>();
        List<String> ativos = new ArrayList<String>();
        String ativoInicial="";
        for(String[] a : dados){
            if((!a[0].equals(ativoInicial))&&(a[0].length()<=4)){
                ativos.add(a[0]);
                ativoInicial=a[0];
            }
        }
        for(String a : ativos){
            System.out.println(a);
        }
        double somaDividendos=0;

    }

}
