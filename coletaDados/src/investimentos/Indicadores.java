package investimentos;

import buscador.RetornaDados;
import carteira.Ativo;
import carteira.Portifolio;

import java.io.IOException;
import java.util.*;

public class Indicadores {
    private RetornaDados buscado = new RetornaDados();//classe que busca dados
    private List<String[]> dados = this.buscado.getDados();//dados buscados do bd
    private List<ArrayList<String[]>> listaSeparada = this.trataDados();//lista de lista com todos os dados de cada ativo em certo periodo
    private List<Ativo> ativos = this.criaAtivos();//lista com os ativos encontrados (geral)
    public Indicadores() throws IOException {
    }

    //getters
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
            float precoCompra = Float.parseFloat(lista.get(i)[lista.get(i).length-3]);
            float precoVenda = (Float.parseFloat(lista.get(i+1)[lista.get(i).length-3]));
            media[i]=retornoEfetivoQualquer(precoCompra,precoVenda,somaDividendos);
        }
        float soma=0;
        for (float a:media){
            soma+=a;
        }
        float mediaTotal = soma/ media.length;
        return mediaTotal;
    }

    /**
     *
     * @param rtEsperado
     * @param rtEfetivo
     * @return
     */
    public float riscoAtivo(float rtEsperado,float rtEfetivo){
        float primeira = rtEfetivo-rtEsperado;
        float resultado = (float) Math.sqrt(rtEsperado*Math.pow(primeira,2));
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
            this.ativos.get(contador).setRiscoAtivo(this.riscoAtivo(this.ativos.get(contador).getRetornoEsperado(),this.ativos.get(contador).getRetornoEfetivo()));

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
    public Ativo[] retornaCarteiraConservadora(int n){
        this.calculaIndicadores();
        Ativo[] aux = ordenaAtivosConservador();
        Ativo[] retorno = new Ativo[n];
        for(int i = 0;i<n;i++){
           retorno[i]=aux[i];
        }
        return retorno;
    }
    /**
     * Gera uma carteira com n ativos
     * @param n - numero de ativos
     */
    public Ativo[] retornaCarteiraModerada(int n){
        this.calculaIndicadores();
        Ativo[] aux = ordenaAtivosModerado();
        Ativo[] retorno = new Ativo[n];
        for(int i = 0;i<n;i++){
            retorno[i]=aux[i];
        }
        return retorno;
    }
    /**
     * Gera uma carteira com n ativos
     * @param n - numero de ativos
     */
    public Ativo[] retornaCarteiraAgressiva(int n){
        this.calculaIndicadores();
        Ativo[] aux = ordenaAtivosAgressivo();
        Ativo[] retorno = new Ativo[n];
        for(int i = 0;i<n;i++){
            retorno[i]=aux[i];
        }
        return retorno;
    }

    /**
     * Gera uma carteira aleatoria
     * @param n
     * @return
     */
    public Ativo[] retornaCarteiraAleatoria(int n){
        this.calculaIndicadores();
        Ativo[] ativoArray = ordenaAleatorio();
        Ativo[] retorno = new Ativo[n];
        for(int i = 0;i<n;i++){
            retorno[i]=ativoArray[i];
        }
        return retorno;
    }

    public Ativo[] retornaCarteiraBruta(int n) throws IOException {
        this.calculaIndicadores();
        float melhor = 0;
        Portifolio retorno = null;
        int vezes = fatorial(n);
        for(int i=0;i<vezes;i++){
            Portifolio atual = new Portifolio(n,4);
            if(atual.getRetorno()>melhor){
                retorno = atual;
            }
        }
        return retorno.getAtivos();
    }

    public static int fatorial( int numero ) {
        int fact = 1;
        for( int i = 1; i <= numero; i++ ) {
            fact *= i;
        }

        return fact;

    }

    /**
     * Ordena pela média baseada no perfil
     * @return
     */
    public Ativo[] ordenaAtivosModerado(){
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
    /**
     * Ordena pela média baseada no perfil
     * @return
     */
    public Ativo[] ordenaAtivosConservador(){
        Ativo[] ordenado = new Ativo[ativos.size()];
        int contador = 0;
        for (Ativo a :ativos) {
            ordenado[contador]=a;
            contador++;
        }

        for(int j = 0;j<ordenado.length;j++){
            for(int i = 0;i<ordenado.length;i++){
                if(ordenado[j].mediaConservador()>ordenado[i].mediaConservador()){
                    Ativo aux = ordenado[i];
                    ordenado[i] = ordenado[j];
                    ordenado[j]=aux;
                }
            }
        }
        return ordenado;
    }
    /**
     * Ordena pela média baseada no perfil
     * @return
     */
    public Ativo[] ordenaAtivosAgressivo(){
        Ativo[] ordenado = new Ativo[ativos.size()];
        int contador = 0;
        for (Ativo a :ativos) {
            ordenado[contador]=a;
            contador++;
        }

        for(int j = 0;j<ordenado.length;j++){
            for(int i = 0;i<ordenado.length;i++){
                if(ordenado[j].mediaAgressivo()>ordenado[i].mediaAgressivo()){
                    Ativo aux = ordenado[i];
                    ordenado[i] = ordenado[j];
                    ordenado[j]=aux;
                }
            }
        }
        return ordenado;
    }

    /**
     * Ordena aleatoriamente
     * @return
     */
    public Ativo[] ordenaAleatorio(){
        Ativo[] ordenado = new Ativo[ativos.size()];
        int contador = 0;
        for (Ativo a :ativos) {
            ordenado[contador]=a;
            contador++;
        }
        List<Ativo> ativosAux = ativos;
        for(int i=0;i<ativosAux.size();i++){
            int min_val = 0;
            int max_val = ativos.size();
            Random ran = new Random();
            int x = ran.nextInt(max_val) + min_val;
            ordenado[i]=ativosAux.get(x);
            ativosAux.remove(x);
        }
        return ordenado;
    }


    /**
     * Pega os indices dos ativos e os ordena na ordem crescente.
     */
    public void notas(){
        String[] listaRetornoEfetivo = this.ordenaRetornoEfetivo();
        String[] listaRetornoEsperado = this.ordenaRetornoEsperado();
        String[] listaRisco = this.ordenaRisco();

        //nota pelo retorno efetivo
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRetornoEfetivo.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRetornoEfetivo[i])){
                    this.getAtivos().get(j).getNotas().add(i+1);
                }
            }
        }
        //nota pelo retorno esperado
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRetornoEsperado.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRetornoEsperado[i])){
                    this.getAtivos().get(j).getNotas().add(i+1);
                }
            }
        }
        //nota pelo risco
        for(int j =0;j<this.getAtivos().size();j++){
            for(int i = 0;i<listaRisco.length;i++){
                if(this.getAtivos().get(j).getNome().equals(listaRisco[i])){
                    this.getAtivos().get(j).getNotas().add(i+1);
                }
            }
        }
    }

    /**
     * Ordena os ativos se baseando no maior retorno efetivo como nota 10
     * @return array de string com os ativos em ordem
     */
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
    /**
     * Ordena os ativos se baseando no maior retorno esperado como nota 10
     * @return array de string com os ativos em ordem
     */
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
    /**
     * Ordena os ativos se baseando no menor risco como nota 10
     * @return array de string com os ativos em ordem
     */
    public String[] ordenaRisco(){
        Ativo[] lista = viraArray();
        String[] nomes = new String[lista.length];
        for(int i=0;i< lista.length;i++){
            for(int j=0;j< lista.length;j++) {
                if (lista[j].getRiscoAtivo() < lista[i].getRiscoAtivo()) {
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

    /**
     * Transforma uma lista em array
     * @return array de ativos
     */
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
