public class FilaCircularEstatica {
    private int MAX;
    private int[] fila;
    private int inicio;
    private int fim;

    public FilaCircularEstatica(int MAX) {
        if (MAX <= 1) {
            System.out.println("ERRO!! MAX deve ser maior que 1.");
        }
        this.MAX = MAX;
        this.fila = new int[MAX];
        this.inicio = 0;
        this.fim = 0;
    }

    public boolean filaVazia() {
        return inicio == fim;
    }

    public boolean filaCheia() {
        return (fim + 1) % MAX == inicio;
    }

    public boolean insereElemento(int elemento) {
        if (filaCheia()) {
            System.out.println("EERRO! Fila cheia!");
            return false;
        }
        fila[fim] = elemento;
        fim = (fim + 1) % MAX;
        return true;
    }

    public int removeElemento() {
        if (filaVazia()) {
            System.out.println("ERRO!! Fila vazia! Nao é possivel remover.");
            return -1;
        }
        int elementoRemovido = fila[inicio];
        inicio = (inicio + 1) % MAX;
        System.out.println("Elemento " + elementoRemovido + " removido.");
        return elementoRemovido;
    }

    public int consultarInicio() {
        if (filaVazia()) {
            return -1;
        }
        return fila[inicio];
    }

    public void imprimeFila() {
        if (filaVazia()) {
            System.out.println("Fila: [vazia]");
            return;
        }
        System.out.print("Fila: | ");
        int indiceAtual = inicio;
        int count = 0;
        while (indiceAtual != fim) {
            System.out.print(fila[indiceAtual] + " ");
            indiceAtual = (indiceAtual + 1) % MAX;
            System.out.print("| ");
            count++;
        }
        System.out.println(" (inicio=" + inicio + ", fim=" + fim + ", ocupados=" + count + "/" + (MAX-1) + ")");
    }

    public int getCapacidade() {
        return MAX;
    }
    public int getCapacidadeUtil() {
        return MAX - 1;
    }
}