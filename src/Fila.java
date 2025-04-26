public class Fila {
    private No frente;
    private No tras;

    public Fila() {
        frente = null;
        tras = null;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public void enfileirar(Elemento elemento) {
        No novoNo = new No(elemento);
        if (estaVazia()) {
            frente = novoNo;
            tras = novoNo;
        } else {
            tras.proximo = novoNo;
            tras = novoNo;
        }
        System.out.println("Cliente enfileirado: " + elemento.id);
    }

    public Elemento desenfileirar() {
        if (estaVazia()) {
            System.out.println("Erro: Fila vazia");
            return null;
        }

        Elemento elementoRemovido = frente.dado;
        frente = frente.proximo;

        if (frente == null) {
            tras = null;
        }

        System.out.println("Cliente atendido: " + elementoRemovido.id);
        return elementoRemovido;
    }

    public Elemento consultarFrente() {
        if (estaVazia()) {
            System.out.println("Erro: Fila vazia");
            return null;
        }
        return frente.dado;
    }

    public void exibirFila() {
        if (estaVazia()) {
            System.out.println("Fila de Atendimento: Fila vazia");
            return;
        }

        System.out.println("Fila de Atendimento (ordem de chegada):");
        No atual = frente;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". ID: " + atual.dado.id +
                    ", Nome: " + atual.dado.descricao +
                    ", Motivo: " + atual.dado.informacaoAdicional);
            atual = atual.proximo;
            posicao++;
        }
    }
}