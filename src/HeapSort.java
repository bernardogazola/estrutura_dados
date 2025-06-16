public class HeapSort extends AlgoritmoOrdenacao {

    public HeapSort() {
        super("Heap Sort");
    }

    @Override
    public void ordenar(int[] vetor, int tamanho) {
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            heapificar(vetor, tamanho, i);
        }

        for (int i = tamanho - 1; i > 0; i--) {
            numeroIteracoes++;

            trocar(vetor, 0, i);

            heapificar(vetor, i, 0);
        }
    }

    private void heapificar(int[] vetor, int tamanhoHeap, int indiceRaiz) {
        numeroIteracoes++;

        int maior = indiceRaiz;
        int filhoEsquerda = 2 * indiceRaiz + 1;
        int filhoDireita = 2 * indiceRaiz + 2;

        if (filhoEsquerda < tamanhoHeap && vetor[filhoEsquerda] > vetor[maior]) {
            maior = filhoEsquerda;
        }

        if (filhoDireita < tamanhoHeap && vetor[filhoDireita] > vetor[maior]) {
            maior = filhoDireita;
        }

        if (maior != indiceRaiz) {
            trocar(vetor, indiceRaiz, maior);

            heapificar(vetor, tamanhoHeap, maior);
        }
    }
}