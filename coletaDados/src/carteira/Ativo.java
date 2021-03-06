package carteira;

import java.util.ArrayList;

public class Ativo{
    private String nome;
    private float retornoEfetivo=0;
    private float retornoEsperado=0;
    private float riscoAtivo=0;
    private ArrayList<Integer> notas=new ArrayList<>();

    public Ativo(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getRetornoEfetivo() {
        return retornoEfetivo;
    }

    public void setRetornoEfetivo(float retornoEfetivo) {
        this.retornoEfetivo = retornoEfetivo;
    }

    public float getRetornoEsperado() {
        return retornoEsperado;
    }

    public void setRetornoEsperado(float retornoEsperado) {
        this.retornoEsperado = retornoEsperado;
    }

    public float getRiscoAtivo() {
        return riscoAtivo;
    }

    public void setRiscoAtivo(float riscoAtivo) {
        this.riscoAtivo = riscoAtivo;
    }

    public ArrayList<Integer> getNotas() {
        return notas;
    }

    //Calculo de medias  (baseando nas notas)

    public float mediaConservador(){
        float media = ((this.getNotas().get(0)*1)+(this.getNotas().get(1)*1)+(this.getNotas().get(2)*4));
        return media/6;
    }
    public float mediaModerado(){
        float media = ((this.getNotas().get(0))+(this.getNotas().get(1))+(this.getNotas().get(2)));
        return media/3;
    }
    public float mediaAgressivo(){
        float media = ((this.getNotas().get(0))+(this.getNotas().get(1)));
        return media/2;
    }

    @Override
    public String toString() {
        String notas = "";
        for(int nota : this.getNotas()){
            notas+=nota+",";
        }
        notas = notas.substring(0,notas.length()-1);
        return "Ativo{" +
                "nome='" + nome + '\'' +
                ", retornoEfetivo=" + retornoEfetivo +
                ", retornoEsperado=" + retornoEsperado +
                ", riscoAtivo=" + riscoAtivo +
                ", notas=" + notas +
                '}';
    }
}
