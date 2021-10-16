package investimentos;

import buscador.RetornaDados;
import carteira.Ativo;

import java.io.IOException;
import java.util.*;

public class Indicadores {
    RetornaDados buscado = new RetornaDados();
    List<String[]> dados = this.buscado.getDados();
    List<ArrayList<String[]>> listaSeparada = this.trataDados();
    List<Ativo> ativos = this.criaAtivos();
    public Indicadores() throws IOException {
    }

    public RetornaDados getBuscado() {
        return buscado;
    }

    public List<String[]> getDados() {
        return dados;
    }

    public List<ArrayList<String[]>> getListaSeparada() {
        return listaSeparada;
    }

    public List<Ativo> getAtivos() {
        return ativos;
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
        return listasIndividuais;
    }

    /**
     * Calcula o retorno efetivo de um devido ativo
     * @param lista com os dados dos dias
     * @return o valor do retorno efetivo daquele ativo
     */
    public float retornoEfetivo(ArrayList<String[]> lista){
        float precoCompra = Float.parseFloat(lista.get(0)[lista.get(0).length-2]);
        float precoVenda =  Float.parseFloat(lista.get(lista.size()-1)[lista.get(lista.size()-1).length-2]);
        float somaDividendos = 0;
        for(String[] a : lista){
            somaDividendos+=Float.parseFloat(a[a.length-1]);
        }
        float retornoEfetivoValor = ((somaDividendos+precoVenda)-precoCompra)/precoCompra;
        return retornoEfetivoValor;
    }

    public float retornoEfetivoQualquer(float compra,float venda,float dividendos){
        return ((dividendos+venda)-compra)/compra;
    }

    public float retornoEsperado(ArrayList<String[]> lista){
        float[] media = new float[lista.size()-1];
        float somaDividendos = 0;
        float precoCompra = Float.parseFloat(lista.get(0)[lista.get(0).length-2]);
        for(int i=0;i<media.length;i++){
            float precoVenda = (Float.parseFloat(lista.get(i+1)[lista.get(lista.size()-1).length-2]));
            somaDividendos+=Float.parseFloat(lista.get(i+1)[lista.get(i+1).length-1]);
            media[i]=retornoEfetivoQualquer(precoCompra,precoVenda,somaDividendos);
        }
        float soma=0;
        for (float a:media){
            soma+=a;
        }
        float mediaTotal = soma/ media.length;
        return mediaTotal;
    }

    public float riscoAtivo(Ativo ativo){
        float subtracao = ativo.getRetornoEfetivo()-ativo.getRetornoEsperado();
        float resultado = (float) Math.sqrt(Math.pow(subtracao,2));
        return resultado;
    }

    /**
     * Adiciona ativos à lista de ativos
     * @return
     */
    public List<Ativo> criaAtivos(){
        List<Ativo> auxAtivos = new ArrayList<>();
        for (ArrayList<String[]> a:listaSeparada){
            Ativo ativo = new Ativo(a.get(0)[0]);
            auxAtivos.add(ativo);
        }
        return auxAtivos;
    }

    /**
     * Calcula todos os indicadores
     * Retorno efetivo
     * Retorno esperado
     * risco do ativo
     */
    public List<Ativo> calculaIndicadores(){
        int contador = 0;
        for(ArrayList<String[]> a : listaSeparada){
            //retorno efetivo
            this.ativos.get(contador).setRetornoEfetivo(this.retornoEfetivo(a));
            //retorno esperado
            this.ativos.get(contador).setRetornoEsperado(this.retornoEsperado(a));
            //risco do ativo
            this.ativos.get(contador).setRiscoAtivo(this.riscoAtivo(this.ativos.get(contador)));

            //aumenta contador
            contador++;
        }
        for(Ativo ativo : ativos){
            System.out.println(ativo.getRetornoEfetivo());
        }
        return this.getAtivos();
    }
}
