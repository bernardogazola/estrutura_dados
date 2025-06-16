public class SelectionSort extends AlgoritmoOrdenacao {

    public SelectionSort() {
        super("Selection Sort");
    }

    @Override
    public void ordenar(int[] vetor, int tamanho) {
        for (int i = 0; i < tamanho - 1; i++) {
            numeroIteracoes++;

            int indiceMenor = i;
            for (int j = i + 1; j < tamanho; j++) {
                numeroIteracoes++;
                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }

            if (indiceMenor != i) {
                trocar(vetor, i, indiceMenor);
            }
        }
    }
}