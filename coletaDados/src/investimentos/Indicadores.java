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

    /**
     * Pega os dados e faz uma lista de listas, onde cada lista tem apenas dados ded um ativo;
     * @return lista de listas
     */
    public List<ArrayList<String[]>> trataDados(){
        List<ArrayList<String[]>> listasIndividuais = new ArrayList<>();//lista com lista de ativos
        List<String> ativos = new ArrayList<String>();//titulo dos ativos
        String ativoInicial="";
        for(String[] a : dados){
            if((!a[0].equals(ativoInicial))&&(a[0].length()<=4)){
                ativos.add(a[0]);
                ativoInicial=a[0];
            }
        }
        for(int i=0;i<ativos.size();i++){
            ArrayList<String[]> aux= new ArrayList<>();
            for(int j=0;j<dados.size();j++){
                String[] atual = dados.get(j);
                if(atual[0].equals(ativos.get(i))){
                    aux.add(atual);
                }

            }
            listasIndividuais.add(aux);
        }

        int contador =0;
        for (String a:listasIndividuais.get(contador).get(contador)
             ) {
            System.out.println(a);
            contador++;
        }
        return listasIndividuais;
    }


}
