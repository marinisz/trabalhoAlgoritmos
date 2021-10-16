package carteira;

public class Ativo {
    private String nome;
    private float retornoEfetivo=0;
    private float retornoEsperado=0;
    private float riscoAtivo=0;

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
}
