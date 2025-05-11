public class Node {
    static final int TAMANHO_ALFABETO = 26;

    Node[] filhos;
    boolean isFimDaPalavra;

    public Node() {
        filhos = new Node[TAMANHO_ALFABETO];
        isFimDaPalavra = false;
    }
}