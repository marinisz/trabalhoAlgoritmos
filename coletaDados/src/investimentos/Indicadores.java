package investimentos;

import buscador.RetornaDados;
import carteira.Ativo;

import java.io.IOException;
import java.util.*;

public class Indicadores {
    private RetornaDados buscado = new RetornaDados();
    private List<String[]> dados = this.buscado.getDados();
    private List<ArrayList<String[]>> listaSeparada = this.trataDados();
    private List<Ativo> ativos = this.criaAtivos();
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

    /**
     * mesma logica do retorno efetivo
     * @param compra preco compra
     * @param venda preco venda
     * @param dividendos dividendos
     * @return
     */
    public float retornoEfetivoQualquer(float compra,float venda,float dividendos){
        return ((dividendos+venda)-compra)/compra;
    }

    /**
     * desvio padrao do retorno efetivo - esperado
     * @param lista
     * @return
     */
    public float retornoEsperado(ArrayList<String[]> lista){
        float[] media = new float[lista.size()-1];
        for(int i=0;i<media.length-1;i++){
            float somaDividendos = Float.parseFloat(lista.get(i)[lista.get(i).length-1]);
            float precoCompra = Float.parseFloat(lista.get(i)[lista.get(i).length-2]);
            float precoVenda = (Float.parseFloat(lista.get(i+1)[lista.get(i).length-2]));
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
        this.notas();
        return this.getAtivos();
    }

    /**
     * Gera uma carteira com n ativos
     * @param n - numero de ativos
     */
    public Ativo[] retornaCarteira (int n){
        this.calculaIndicadores();
        Ativo[] aux = ordenaAtivos();
        Ativo[] retorno = new Ativo[n];
        for(int i = 0;i<n;i++){
           retorno[i]=aux[i];
        }
        return retorno;
    }

    /**
     * Ordena pela média baseada no perfil (normal é moderado)
     * @return
     */
    public Ativo[] ordenaAtivos(){
        Ativo[] ordenado = new Ativo[ativos.size()];
        int contador = 0;
        for (Ativo a :ativos) {
            ordenado[contador]=a;
            contador++;
        }

        for(int j = 0;j<ordenado.length;j++){
            for(int i = 0;i<ordenado.length;i++){
                if(ordenado[j].mediaModerado()>ordenado[i].mediaModerado()){
                    Ativo aux = ordenado[i];
                    ordenado[i] = ordenado[j];
                    ordenado[j]=aux;
                }
            }
        }
        return ordenado;
    }

    public void notas(){
        String[] listaRetornoEfetivo = this.ordenaRetornoEfetivo();
        String[] listaRetornoEsperado = this.ordenaRetornoEsperado();
        String[] listaRisco = this.ordenaRisco();

        //nota pelo retorno efetivo
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRetornoEfetivo.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRetornoEfetivo[i])){
                    this.getAtivos().get(j).getNotas().add(i);
                }
            }
        }
        //nota pelo retorno esperado
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRetornoEsperado.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRetornoEsperado[i])){
                    this.getAtivos().get(j).getNotas().add(i);
                }
            }
        }
        //nota pelo risco
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRisco.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRisco[i])){
                    this.getAtivos().get(j).getNotas().add(i);
                }
            }
        }
    }

    public String[] ordenaRetornoEfetivo(){
        Ativo[] lista = viraArray();
        String[] nomes = new String[lista.length];
        for(int i=0;i< lista.length;i++){
            for(int j=0;j< lista.length;j++) {
                if (lista[j].getRetornoEfetivo() > lista[i].getRetornoEfetivo()) {
                    Ativo aux = lista[i];
                    lista[i]=lista[j];
                    lista[j]=aux;
                }
            }
        }
        int contador = 0;
        for(Ativo a : lista){
            nomes[contador] = a.getNome();
            contador++;
        }
        return nomes;
    }
    public String[] ordenaRetornoEsperado(){
        Ativo[] lista = viraArray();
        String[] nomes = new String[lista.length];
        for(int i=0;i< lista.length;i++){
            for(int j=0;j< lista.length;j++) {
                if (lista[j].getRetornoEsperado() > lista[i].getRetornoEsperado()) {
                    Ativo aux = lista[i];
                    lista[i]=lista[j];
                    lista[j]=aux;
                }
            }
        }
        int contador = 0;
        for(Ativo a : lista){
            nomes[contador] = a.getNome();
            contador++;
        }
        return nomes;
    }
    public String[] ordenaRisco(){
        Ativo[] lista = viraArray();
        String[] nomes = new String[lista.length];
        for(int i=0;i< lista.length;i++){
            for(int j=0;j< lista.length;j++) {
                if (lista[j].getRetornoEsperado() > lista[i].getRetornoEsperado()) {
                    Ativo aux = lista[i];
                    lista[i]=lista[j];
                    lista[j]=aux;
                }
            }
        }
        int contador = 0;
        for(Ativo a : lista){
            nomes[contador] = a.getNome();
            contador++;
        }
        return nomes;
    }

    public Ativo[] viraArray(){
        Ativo[] array = new Ativo[this.getAtivos().size()];
        int i = 0;
        for(Ativo a : this.getAtivos()){
            array[i]=a;
            i++;
        }
        return array;
    }

}
