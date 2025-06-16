public class QuickSort extends AlgoritmoOrdenacao {

    public QuickSort() {
        super("Quick Sort");
    }

    @Override
    public void ordenar(int[] vetor, int tamanho) {
        quickSort(vetor, 0, tamanho - 1);
    }

    private void quickSort(int[] vetor, int inicio, int fim) {
        numeroIteracoes++;

        if (inicio < fim) {
            int posicaoPivo = particionar(vetor, inicio, fim);

            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    private int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int esquerda = inicio;
        int direita = fim;

        while (esquerda < direita) {
            while (esquerda <= fim && vetor[esquerda] <= pivo) {
                esquerda++;
                numeroIteracoes++;
            }

            while (vetor[direita] > pivo) {
                direita--;
                numeroIteracoes++;
            }

            if (esquerda < direita) {
                trocar(vetor, esquerda, direita);
            }
        }

        trocar(vetor, inicio, direita);

        return direita;
    }
}