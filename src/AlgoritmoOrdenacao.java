public abstract class AlgoritmoOrdenacao {
    protected String nome;
    protected long tempoExecucao;
    protected long numeroTrocas;
    protected long numeroIteracoes;

    public AlgoritmoOrdenacao(String nome) {
        this.nome = nome;
        resetarMetricas();
    }

    public abstract void ordenar(int[] vetor, int tamanho);

    public void executar(int[] vetor, int tamanho) {
        resetarMetricas();
        long inicio = System.nanoTime();
        ordenar(vetor, tamanho);
        long fim = System.nanoTime();
        tempoExecucao = fim - inicio;
    }

    protected void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
        numeroTrocas++;
    }

    protected void resetarMetricas() {
        tempoExecucao = 0;
        numeroTrocas = 0;
        numeroIteracoes = 0;
    }

    public String getNome() {
        return nome;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public long getNumeroTrocas() {
        return numeroTrocas;
    }

    public long getNumeroIteracoes() {
        return numeroIteracoes;
    }
}