public class Fila {
    private int MAX;
    private int[] fila;
    private int inicio;
    private int fim;

    public Fila(int MAX) {
        if (MAX <= 1) {
            System.out.println("ERRO: MAX deve ser maior que 1.");
        }
        this.MAX = MAX;
        this.fila = new int[MAX];
        this.inicio = 0;
        this.fim = 0;
        System.out.println("Fila criada com MAX: " + MAX + ". Capacidade útil: " + (MAX - 1));
    }

    public boolean filaVazia() {
        return inicio == fim;
    }

    public boolean filaCheia() {
        return (fim + 1) % MAX == inicio;
    }

    public boolean insereElemento(int elemento) {
        System.out.print("Tentando inserir " + elemento + "... ");
        if (filaCheia()) {
            System.out.println("ERRO: Fila cheia!");
            return false;
        }
        fila[fim] = elemento;
        fim = (fim + 1) % MAX;
        System.out.println("Deu boa. Início: " + inicio + ", Fim: " + fim);
        return true;
    }

    public int removeElemento() {
        System.out.print("Tentando remover... ");
        if (filaVazia()) {
            System.out.println("ERRO: Fila vazia!");
            throw new RuntimeException("Não é possível remover de uma fila vazia.");
        }
        int elementoRemovido = fila[inicio];
        inicio = (inicio + 1) % MAX;
        System.out.println("Elemento " + elementoRemovido + " removido. Início: " + inicio + ", Fim: " + fim);
        return elementoRemovido;
    }

    public void imprimir() {
        if (filaVazia()) {
            System.out.println("Fila: [vazia] (inicio=" + inicio + ", fim=" + fim + ")");
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

    public static void main(String[] args) {
        Fila minhaFila = new Fila(5);

        minhaFila.imprimir();
        System.out.println("Fila vazia? " + (minhaFila.filaVazia() ? "Sim" : "Não"));
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));

        // INSERINDO ATÉ MAX-1 (capacidade útil)
        minhaFila.insereElemento(10);
        minhaFila.insereElemento(20);
        minhaFila.insereElemento(30);
        minhaFila.insereElemento(40);
        minhaFila.imprimir();
        System.out.println("Fila vazia? " + (minhaFila.filaVazia() ? "Sim" : "Não"));
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));

        // TENTAR INSERIR NA FILA CHEIA
        minhaFila.insereElemento(50);
        minhaFila.imprimir();

        // REMOVER
        minhaFila.removeElemento();
        minhaFila.imprimir();
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));

        // INSERIR DE NOVO - TESTAR CIRCULARIDADE
        minhaFila.insereElemento(50);
        minhaFila.imprimir();
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));

        // REMOVER TUDO
        minhaFila.removeElemento();
        minhaFila.removeElemento();
        minhaFila.removeElemento();
        minhaFila.imprimir();
        minhaFila.removeElemento();
        minhaFila.imprimir(); // SE DEU BOA TA VAZIA
        System.out.println("Fila vazia? " + (minhaFila.filaVazia() ? "Sim" : "Não"));
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));

        // TENTAR REMOVER DA FILA VAZIA
        try {
            minhaFila.removeElemento();
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        minhaFila.imprimir();

        // TENTAR INSERIR ATÉ MAX-1 (capacidade útil) DE NOVO
        minhaFila.insereElemento(11);
        minhaFila.insereElemento(22);
        minhaFila.insereElemento(33);
        minhaFila.insereElemento(44);
        minhaFila.imprimir();
        System.out.println("Fila cheia? " + (minhaFila.filaCheia() ? "Sim" : "Não"));
    }
}