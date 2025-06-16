public class CountingSort extends AlgoritmoOrdenacao {

    public CountingSort() {
        super("Counting Sort");
    }

    @Override
    public void ordenar(int[] vetor, int tamanho) {
        if (tamanho == 0) return;

        int max = vetor[0];
        int min = vetor[0];

        for (int i = 1; i < tamanho; i++) {
            numeroIteracoes++;
            if (vetor[i] > max) max = vetor[i];
            if (vetor[i] < min) min = vetor[i];
        }

        int range = max - min + 1;
        int[] contagem = new int[range];
        int[] saida = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            numeroIteracoes++;
            contagem[vetor[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            numeroIteracoes++;
            contagem[i] += contagem[i - 1];
        }

        for (int i = tamanho - 1; i >= 0; i--) {
            numeroIteracoes++;
            saida[contagem[vetor[i] - min] - 1] = vetor[i];
            contagem[vetor[i] - min]--;
            numeroTrocas++;
        }

        for (int i = 0; i < tamanho; i++) {
            numeroIteracoes++;
            vetor[i] = saida[i];
        }
    }
}