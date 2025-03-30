public class ListaEncadeada {
    class No {
        int dado;
        No proximo;

        No(int elemento) {
            this.dado = elemento;
            this.proximo = null;
        }
    }

    No inicio = null;

    public void insereElemento(int elemento) {
        System.out.println("Inserindo elemento: " + elemento);
        No novoNo = new No(elemento);

        if (inicio == null) {
            inicio = novoNo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
    }

    public void removeElemento(int valor) {
        if (inicio == null) {
            System.out.println("ERRO: Lista vazia!");
            return;
        }

        if (inicio.dado == valor) {
            System.out.println("Removendo o elemento do início: " + valor);
            inicio = inicio.proximo;
            return;
        }

        No atual = inicio;
        while (atual.proximo != null) {
            if (atual.proximo.dado == valor) {
                System.out.println("Removendo o elemento: " + valor);
                atual.proximo = atual.proximo.proximo;
                return;
            }
            atual = atual.proximo;
        }

        System.out.println("ERRO: Elemento " + valor + " não encontrado!");
    }

    public void exibeLista() {
        No atual = inicio;
        if (atual == null) {
            System.out.println("Lista: [vazia]");
            return;
        }
        System.out.print("Lista: | ");
        while (atual != null) {
            System.out.print(atual.dado + " | ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaEncadeada minhaLista = new ListaEncadeada();

        // INSERIR
        minhaLista.insereElemento(10);
        minhaLista.insereElemento(20);
        minhaLista.insereElemento(30);
        minhaLista.insereElemento(40);
        minhaLista.insereElemento(50);
        minhaLista.exibeLista();

        // REMOVER ELEMENTO DO MEIO
        minhaLista.removeElemento(30);
        minhaLista.exibeLista();

        // REMOVER ELEMENTO DO INICIO
        minhaLista.removeElemento(10);
        minhaLista.exibeLista();

        // REMOVER ELEMENTO DO FIM
        minhaLista.removeElemento(50);
        minhaLista.exibeLista();

        // TENTAR REMOVER ELEMENTO QUE NÃO EXISTE
        minhaLista.removeElemento(99);
        minhaLista.exibeLista();

        // REMOVER O RESTO
        minhaLista.removeElemento(20);
        minhaLista.exibeLista();
        minhaLista.removeElemento(40);
        minhaLista.exibeLista();

        // TENTAR REMOVER DA LISTA VAZIA
        minhaLista.removeElemento(100);
        minhaLista.exibeLista();
    }
}