import java.util.Scanner;

public class FilaListaEncadeada {
    private static class No {
        int dado;
        No proximo;

        No(int elemento) {
            this.dado = elemento;
            this.proximo = null;
        }
    }

    private No inicio;
    private No fim;

    public FilaListaEncadeada() {
        this.inicio = null;
        this.fim = null;
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public void insere(int elemento) {
        No novoNo = new No(elemento);
        if (estaVazia()) {
            inicio = novoNo;
        } else {
            fim.proximo = novoNo;
        }
        fim = novoNo;
    }

    public int remove() {
        if (estaVazia()) {
            System.out.println("ERRO!! Fila vazia! Não é possível remover.");
            return -1;
        }
        int elementoRemovido = inicio.dado;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        }
        System.out.println("Elemento " + elementoRemovido + " removido da fila.");
        return elementoRemovido;
    }

    public int consultarInicio() {
        if (estaVazia()) {
            System.out.println("ERRO!! Fila vazia! Não é possível consultar.");
            return -1;
        }
        return inicio.dado;
    }

    public void imprime() {
        if (estaVazia()) {
            System.out.println("Fila: [vazia]");
            return;
        }
        System.out.print("Fila: ");
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.dado + " -> ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FilaListaEncadeada fila = new FilaListaEncadeada();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\nEscolha uma opcao:");
            System.out.println("  1: Inserir elemento");
            System.out.println("  2: Remover elemento");
            System.out.println("  3: Imprimir fila");
            System.out.println("  4: Consultar inicio");
            System.out.println("  0: Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor inteiro para inserir: ");
                    int valor = scanner.nextInt();
                    fila.insere(valor);
                    System.out.println("Elemento " + valor + " inserido.");
                    fila.imprime();
                    break;
                case 2:
                    fila.remove();
                    fila.imprime();
                    break;
                case 3:
                    fila.imprime();
                    break;
                case 4:
                    int inicio = fila.consultarInicio();
                    if (inicio != -1) {
                        System.out.println("Elemento no inicio: " + inicio);
                    } else {
                        System.out.println("Fila esta vazia.");
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