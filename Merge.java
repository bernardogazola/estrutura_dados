import java.util.Scanner;

public class Merge {
    public static FilaListaEncadeada merge(FilaListaEncadeada filaA, FilaListaEncadeada filaB) {
        System.out.println("Merge Filas Dinamicas");
        FilaListaEncadeada filaC = new FilaListaEncadeada();
        while (!filaA.estaVazia() && !filaB.estaVazia()) {
            int inicioA = filaA.consultarInicio();
            int inicioB = filaB.consultarInicio();
            if (inicioA <= inicioB) {
                filaC.insere(filaA.remove());
            } else {
                filaC.insere(filaB.remove());
            }
        }
        while (!filaA.estaVazia()) {
            filaC.insere(filaA.remove());
        }
        while (!filaB.estaVazia()) {
            filaC.insere(filaB.remove());
        }

        return filaC;
    }

    public static FilaCircularEstatica mergeEstatico(FilaCircularEstatica filaA, FilaCircularEstatica filaB) {
        System.out.println("Merge Filas Estaticas");
        int capacidadeC = filaA.getCapacidade() + filaB.getCapacidade();
        FilaCircularEstatica filaC = new FilaCircularEstatica(capacidadeC);

        while (!filaA.filaVazia() && !filaB.filaVazia()) {
            int inicioA = filaA.consultarInicio();
            int inicioB = filaB.consultarInicio();
            if (inicioA <= inicioB) {
                filaC.insereElemento(filaA.removeElemento());
            } else {
                filaC.insereElemento(filaB.removeElemento());
            }
        }
        while (!filaA.filaVazia()) {
            filaC.insereElemento(filaA.removeElemento());
        }
        while (!filaB.filaVazia()) {
            filaC.insereElemento(filaB.removeElemento());
        }

        return filaC;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int valor;

        FilaListaEncadeada filaADinamica = new FilaListaEncadeada();
        FilaListaEncadeada filaBDinamica = new FilaListaEncadeada();
        FilaListaEncadeada filaCDinamica;

        System.out.println("Fila A dinamica");
        System.out.println("Digite números ORDENADOS crescentemente (0 para terminar):");
        while (true) {
            System.out.print("Fila A dinamica - Digite valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor == 0) break;
                filaADinamica.insere(valor);
                filaADinamica.imprime();
            } else {
                System.out.println("ERRO! Entrada invalida.");
                scanner.next();
            }
        }
        System.out.println("Fila A dinamica final:");
        filaADinamica.imprime();

        System.out.println("Fila B dinamica");
        System.out.println("Digite números ORDENADOS crescentemente (0 para terminar):");
        while (true) {
            System.out.print("Fila A dinamica - Digite o valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor == 0) break;
                filaBDinamica.insere(valor);
                filaBDinamica.imprime();
            } else {
                System.out.println("ERRO!! Entrada invalida.");
                scanner.next();
            }
        }
        System.out.println("Fila B dinamica final:");
        filaBDinamica.imprime();

        filaCDinamica = merge(filaADinamica, filaBDinamica);
        System.out.println("Merge -> Fila C");
        filaCDinamica.imprime();
        System.out.println("\nFilas A e B apos merge:");
        filaADinamica.imprime();
        filaBDinamica.imprime();

        FilaCircularEstatica filaAEstatica;
        FilaCircularEstatica filaBEstatica;
        FilaCircularEstatica filaCEstatica;
        int capacidadeA = 5, capacidadeB = 6;

        System.out.print("\nDigite a capacidade TOTAL para a Fila Estatica A (tem que ser maior que 1): ");
        if (scanner.hasNextInt()) {
            capacidadeA = scanner.nextInt();
            if(capacidadeA <= 1) capacidadeA = 5;
        } else {
            System.out.println("Invalido, usando 5.");
            scanner.next();
        }
        filaAEstatica = new FilaCircularEstatica(capacidadeA);
        System.out.println("Capacidade util A: " + filaAEstatica.getCapacidadeUtil());

        System.out.print("Digite a capacidade TOTAL para a Fila Estatica B (tem que ser maior que 1): ");
        if (scanner.hasNextInt()) {
            capacidadeB = scanner.nextInt();
            if(capacidadeB <= 1) capacidadeB = 6;
        } else {
            System.out.println("Invalido, usando 6.");
            scanner.next();
        }
        filaBEstatica = new FilaCircularEstatica(capacidadeB);
        System.out.println("Capacidade util B: " + filaBEstatica.getCapacidadeUtil());

        System.out.println("Fila A estatica");
        System.out.println("Digite números ORDENADOS crescentemente (0 para terminar, max " + filaAEstatica.getCapacidadeUtil() + " números):");
        while (!filaAEstatica.filaCheia()) {
            System.out.print("Fila A estatica - Digite valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor == 0) break;
                if (!filaAEstatica.insereElemento(valor)) break;
                filaAEstatica.imprimeFila();
            } else {
                System.out.println("ERRO!! Entrada invalida.");
                scanner.next();
            }
        }
        if(filaAEstatica.filaCheia()) {
            System.out.println("Fila A Estatica cheia.");
        }
        System.out.println("Fila A estatica final:");
        filaAEstatica.imprimeFila();

        System.out.println("Fila B estatica");
        System.out.println("Digite números ORDENADOS crescentemente (0 para terminar, max " + filaBEstatica.getCapacidadeUtil() + " números):");
        while (!filaBEstatica.filaCheia()) {
            System.out.print("Fila B estatica - Digite valor: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor == 0) break;
                if (!filaBEstatica.insereElemento(valor)) break;
                filaBEstatica.imprimeFila();
            } else {
                System.out.println("ERRO!! Entrada invalida.");
                scanner.next();
            }
        }
        if(filaBEstatica.filaCheia()) {
            System.out.println("Fila B Estatica cheia.");
        }
        System.out.println("Fila B estatica final:");
        filaBEstatica.imprimeFila();

        filaCEstatica = mergeEstatico(filaAEstatica, filaBEstatica);
        System.out.println("Merge -> Fila C");
        filaCEstatica.imprimeFila();
        System.out.println("\nFilas A e B apos merge:");
        filaAEstatica.imprimeFila();
        filaBEstatica.imprimeFila();

        scanner.close();
    }
}