package carteira;

import investimentos.Indicadores;

import java.io.IOException;

public class Portifolio {
    private Ativo[] ativos;
    private float retorno = 0;
    private float risco = 0;

    public Portifolio(int n,int tipo) throws IOException {
        this.ativos = new Ativo[n];
        geraPortifolio(n,tipo);
    }

    public Ativo[] getAtivos() {
        return ativos;
    }

    public float getRetorno() {
        return retorno;
    }

    public void setRetorno(float retorno) {
        this.retorno = retorno;
    }

    public float getRisco() {
        return risco;
    }

    public void setRisco(float risco) {
        this.risco = risco;
    }

    public void geraPortifolio(int quantidade,int tipo) throws IOException {
        Indicadores indicadores = new Indicadores();
        Ativo[] todos=null;
        if(tipo==1){
            todos = indicadores.retornaCarteiraConservadora(quantidade);
        }
        else if(tipo==2){
            todos = indicadores.retornaCarteiraModerada(quantidade);
        }
        else if(tipo==3){
            todos = indicadores.retornaCarteiraAgressiva(quantidade);
        }
        else if(tipo==4){
            todos = indicadores.retornaCarteiraAleatoria(quantidade);
        }
        else if(tipo==5){
            todos = indicadores.retornaCarteiraBruta(quantidade);
        }

        for(int i =0;i<ativos.length;i++){
            this.ativos[i]=todos[i];
        }
        geraRetorno();
        geraRisco();
    }
    public void geraRetorno(){
        float somatorio=0;
        for (Ativo a :ativos) {
            somatorio+=a.getRetornoEsperado()*(1.00/ativos.length);
        }
        this.setRetorno(somatorio);
    }
    public void geraRisco(){
        float somatorio=0;
        for (Ativo a :ativos) {
            somatorio+=a.getRiscoAtivo()*(1.00/ativos.length);
        }
        this.setRisco(somatorio);
    }


    @Override
    public String toString() {
        String retorno = "| ";
        for (Ativo a:getAtivos()){
            retorno+=a.getNome()+" | ";
        }
        retorno+="\nRisco: "+this.getRisco()+"\nRetorno: "+this.getRetorno();
        return retorno.toUpperCase();
    }
}
