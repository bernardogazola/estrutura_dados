import java.util.Scanner;

public class PilhaListaEncadeada {
    class No {
        int dado;
        No proximo;

        No(int elemento) {
            this.dado = elemento;
            this.proximo = null;
        }
    }

    private No topo;

    public PilhaListaEncadeada() {
        this.topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void insere(int elemento) {
        No novoNo = new No(elemento);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public int remove() {
        if (estaVazia()) {
            System.out.println("ERRO!! Pilha vazia! Não é possível remover.");
            return -1;
        }
        int elementoRemovido = topo.dado;
        topo = topo.proximo;
        System.out.println("Elemento " + elementoRemovido + " removido da pilha.");
        return elementoRemovido;
    }

    public int consultarTopo() {
        if (estaVazia()) {
            System.out.println("ERRO! Pilha vazia! Não é possível consultar.");
            return -1;
        }
        return topo.dado;
    }

    public void imprime() {
        if (estaVazia()) {
            System.out.println("Pilha: [vazia]");
            return;
        }
        System.out.print("Pilha: ");
        No atual = topo;
        while (atual != null) {
            System.out.print(atual.dado + " -> ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PilhaListaEncadeada pilha = new PilhaListaEncadeada();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opcao:");
            System.out.println("  1: Inserir elemento");
            System.out.println("  2: Remover elemento");
            System.out.println("  3: Imprimir pilha");
            System.out.println("  4: Consultar topo");
            System.out.println("  0: Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor inteiro para inserir: ");
                    if (scanner.hasNextInt()) {
                        int valor = scanner.nextInt();
                        pilha.insere(valor);
                        System.out.println("Elemento " + valor + " inserido.");
                        pilha.imprime();
                    } else {
                        System.out.println("Valor invalido.");
                        scanner.next();
                    }
                    break;
                case 2:
                    pilha.remove();
                    pilha.imprime();
                    break;
                case 3:
                    pilha.imprime();
                    break;
                case 4:
                    int topo = pilha.consultarTopo();
                    if (topo != -1) {
                        System.out.println("Elemento no topo: " + topo);
                    } else {
                        System.out.println("Pilha esta vazia.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }

        scanner.close();
    }
}