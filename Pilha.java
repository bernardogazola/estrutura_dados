public class Pilha {
    private int MAX;
    private int[] pilha;
    private int topo;

    public Pilha(int MAX) {
        if (MAX <= 0) {
            System.out.println("ERRO: MAX deve ser maior que 0.");
        }
        this.MAX = MAX;
        this.pilha = new int[MAX];
        this.topo = -1;
        System.out.println("Pilha criada com MAX: " + MAX);
    }

    public boolean pilhaVazia() {
        return topo == -1;
    }

    public boolean pilhaCheia() {
        return topo == MAX - 1;
    }

    public boolean empilhaElemento(int elemento) {
        System.out.print("Tentando empilhar " + elemento + "... ");
        if (pilhaCheia()) {
            System.out.println("ERRO: Pilha cheia!");
            return false;
        }
        topo++;
        pilha[topo] = elemento;
        System.out.println("Deu boa. Topo agora em " + topo);
        return true;
    }

    public int desempilhaElemento() {
        System.out.print("Tentando desempilhar... ");
        if (pilhaVazia()) {
            System.out.println("ERRO: Pilha vazia!");
            throw new RuntimeException("Não é possível desempilhar de uma pilha vazia.");
        }
        int elementoRemovido = pilha[topo];
        topo--;
        System.out.println("Elemento " + elementoRemovido + " removido. Topo agora em " + topo);
        return elementoRemovido;
    }

    public int consultaTopo() {
        System.out.print("Consultando topo... ");
        if (pilhaVazia()) {
            System.out.println("ERRO: Pilha vazia!");
            throw new RuntimeException("Não é possível consultar o topo de uma pilha vazia.");
        }
        int elementoTopo = pilha[topo];
        System.out.println("Elemento no topo: " + elementoTopo);
        return elementoTopo;
    }

    public void imprimir() {
        if (pilhaVazia()) {
            System.out.println("Pilha: [vazia]");
            return;
        }
        System.out.print("Pilha: | ");
        for (int i = 0; i <= topo; i++) {
            System.out.print(pilha[i]);
            System.out.print(" | ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Pilha minhaPilha = new Pilha(5);

        minhaPilha.imprimir();

        // INSERIR
        minhaPilha.empilhaElemento(10);
        minhaPilha.imprimir();
        minhaPilha.empilhaElemento(20);
        minhaPilha.empilhaElemento(30);
        minhaPilha.imprimir();
        minhaPilha.consultaTopo();

        // INSERIR ATÉ CAPACIDADE MAX
        minhaPilha.empilhaElemento(40);
        minhaPilha.empilhaElemento(50);
        minhaPilha.imprimir();
        System.out.println("Pilha cheia? " + (minhaPilha.pilhaCheia() ? "Sim" : "Não"));

        // TENTAR INSERIR NA PILHA CHEIA
        minhaPilha.empilhaElemento(60);
        minhaPilha.imprimir();

        // REMOVER
        minhaPilha.desempilhaElemento();
        minhaPilha.imprimir();
        minhaPilha.consultaTopo();

        // REMOVER TUDO
        minhaPilha.desempilhaElemento();
        minhaPilha.desempilhaElemento();
        minhaPilha.desempilhaElemento();
        minhaPilha.imprimir();
        minhaPilha.desempilhaElemento();
        minhaPilha.imprimir();
        System.out.println("Pilha vazia? " + (minhaPilha.pilhaVazia() ? "Sim" : "Não"));

        // TENTAR REMOVER DA PILHA VAZIA
        try {
            minhaPilha.desempilhaElemento();
        } catch (RuntimeException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        minhaPilha.imprimir();
    }
}