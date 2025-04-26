public class Pilha {
    private No topo;

    public Pilha() {
        topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(Elemento elemento) {
        No novoNo = new No(elemento);
        if (estaVazia()) {
            topo = novoNo;
        } else {
            novoNo.proximo = topo;
            topo = novoNo;
        }
        System.out.println("Solicitação empilhada: " + elemento.id);
    }

    public Elemento desempilhar() {
        if (estaVazia()) {
            System.out.println("Erro: Pilha vazia");
            return null;
        }

        Elemento elementoRemovido = topo.dado;
        topo = topo.proximo;
        System.out.println("Solicitação desempilhada: " + elementoRemovido.id);
        return elementoRemovido;
    }

    public Elemento consultarTopo() {
        if (estaVazia()) {
            System.out.println("Erro: Pilha vazia");
            return null;
        }
        return topo.dado;
    }

    public void exibirPilha() {
        if (estaVazia()) {
            System.out.println("Histórico de Solicitações: Pilha vazia");
            return;
        }

        System.out.println("Histórico de Solicitações (mais recente primeiro):");
        No atual = topo;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". ID: " + atual.dado.id +
                    ", Descrição: " + atual.dado.descricao +
                    ", Data/Hora: " + atual.dado.informacaoAdicional);
            atual = atual.proximo;
            posicao++;
        }
    }
}