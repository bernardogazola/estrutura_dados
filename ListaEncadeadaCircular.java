public class ListaEncadeadaCircular {
    class No {
        int dado;
        No proximo;

        No(int elemento) {
            this.dado = elemento;
            this.proximo = null;
        }
    }

    No ultimo = null;

    public boolean listaVazia() {
        return ultimo == null;
    }

    public void insereElemento(int elemento) {
        No novoNo = new No(elemento);

        if (listaVazia()) {
            ultimo = novoNo;
            novoNo.proximo = ultimo;
            System.out.println("Inserindo o elemento: " + elemento + " - (PRIMEIRO)");
        } else {
            novoNo.proximo = ultimo.proximo;
            ultimo.proximo = novoNo;
            ultimo = novoNo;
            System.out.println("Inserindo o elemento: " + elemento);
        }
    }

    public void removeElemento(int valor) {
        if (listaVazia()) {
            System.out.println("ERRO: Lista vazia!");
            return;
        }

        No atual = ultimo.proximo;
        No anterior = ultimo;

        do {
            if (atual.dado == valor) {
                if (atual == ultimo && atual.proximo == ultimo) {
                    ultimo = null;
                    System.out.println("Removendo elemento: " + valor + " - (ERA O ÚNICO)");
                    return;
                }

                anterior.proximo = atual.proximo;

                if (atual == ultimo) {
                    ultimo = anterior;
                    System.out.println("Removendo o elemento: " + valor + " - (ERA O ULTIMO)");
                } else {
                    System.out.println("Removendo o elemento: " + valor);
                }
                return;
            }

            anterior = atual;
            atual = atual.proximo;
        } while (atual != ultimo.proximo);

        System.out.println("ERRO: Elemento " + valor + " não encontrado!");
    }

    public void exibeLista() {
        if (listaVazia()) {
            System.out.println("Lista: [vazia]");
            return;
        }

        System.out.print("Lista: | ");
        No atual = ultimo.proximo;

        do {
            System.out.print(atual.dado + " | ");
            atual = atual.proximo;
        } while (atual != ultimo.proximo);

        System.out.println("(volta para o inicio: " + ultimo.proximo.dado + ")");
    }

    public static void main(String[] args) {
        ListaEncadeadaCircular minhaLista = new ListaEncadeadaCircular();

        minhaLista.exibeLista();

        // INSERIR
        minhaLista.insereElemento(10);
        minhaLista.exibeLista();
        minhaLista.insereElemento(20);
        minhaLista.exibeLista();
        minhaLista.insereElemento(30);
        minhaLista.exibeLista();
        minhaLista.insereElemento(40);
        minhaLista.exibeLista();

        // REMOVER ELEMENTO DO MEIO
        minhaLista.removeElemento(20);
        minhaLista.exibeLista();

        // REMOVER O PRIMEIRO
        minhaLista.removeElemento(10);
        minhaLista.exibeLista();

        // REMOVER O ULTIMO
        minhaLista.removeElemento(40);
        minhaLista.exibeLista();

        // INSERIR DE NOVO
        minhaLista.insereElemento(50);
        minhaLista.insereElemento(60);
        minhaLista.exibeLista();

        // TENTAR REMOVER ELEMENTO QUE NAO EXISTE
        minhaLista.removeElemento(99);
        minhaLista.exibeLista();

        // REMOVER TUDO
        minhaLista.removeElemento(50);
        minhaLista.exibeLista();
        minhaLista.removeElemento(30);
        minhaLista.exibeLista();
        minhaLista.removeElemento(60);
        minhaLista.exibeLista();

        // TENTAR REMOVER DA LISTA VAZIA
        minhaLista.removeElemento(100);
        minhaLista.exibeLista();
    }
}