package carteira;

import java.util.Arrays;

public class Portifolio {
    private Ativo[] ativos;
    private float retorno = 0;
    private float risco = 0;

    public Portifolio(int n){
        this.ativos = new Ativo[n];
    }

    public Ativo[] getAtivos() {
        return ativos;
    }

    public void setAtivos(Ativo[] ativos) {
        this.ativos = ativos;
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

    @Override
    public String toString() {
        String retorno = "|";
        for (Ativo a:getAtivos()){
            retorno+=a.getNome()+"|";
        }
        retorno+="\nRisco: "+this.getRisco()+"\nRetorno: "+this.getRetorno();
        return retorno;
    }
}
