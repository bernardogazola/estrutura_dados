import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n1. Traduzir de código Morse para texto");
            System.out.println("2. Traduzir de texto para código Morse");
            System.out.println("3. Inserir novo caractere na árvore");
            System.out.println("4. Visualizar árvore Morse (compacta)");
            System.out.println("5. Visualizar árvore Morse (detalhada)");
            System.out.println("6. Buscar caractere na árvore");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a mensagem em código Morse:");
                    System.out.println("(Separe cada caractere com espaço e use / para espaços)");
                    System.out.print("> ");
                    String mensagemMorse = scanner.nextLine();

                    String traduzido = arvore.traduzirMorse(mensagemMorse);
                    System.out.println("\nMensagem traduzida: " + traduzido);
                    break;

                case 2:
                    System.out.println("Digite o texto a ser traduzido para código Morse:");
                    System.out.print("> ");
                    String texto = scanner.nextLine();

                    String morse = arvore.traduzirTexto(texto);
                    System.out.println("\nCódigo Morse: " + morse);
                    break;

                case 3:
                    System.out.println("Digite o caractere a ser inserido:");
                    System.out.print("> ");
                    String caractereStr = scanner.nextLine();

                    if (caractereStr.length() != 1) {
                        System.out.println("\nErro: Por favor, insira apenas um caractere.");
                        break;
                    }

                    char caractere = Character.toUpperCase(caractereStr.charAt(0));

                    System.out.println("Digite o código Morse para o caractere '" + caractere + "':");
                    System.out.println("(Use apenas pontos . e traços -)");
                    System.out.print("> ");
                    String codigo = scanner.nextLine();

                    if (!arvore.codigoValido(codigo)) {
                        System.out.println("\nErro: O código Morse deve conter apenas pontos (.) e traços (-).");
                        break;
                    }

                    char existente = arvore.buscar(codigo);
                    if (existente != '\0') {
                        System.out.println("\nO código '" + codigo + "' já está associado ao caractere '" + existente + "'.");
                        System.out.println("Deseja substituir? (S/N)");
                        System.out.print("> ");
                        String resposta = scanner.nextLine();

                        if (!resposta.equalsIgnoreCase("S")) {
                            System.out.println("\nOperação cancelada.");
                            break;
                        }
                    }

                    arvore.inserir(codigo, caractere);
                    System.out.println("\nCaractere '" + caractere + "' inserido com sucesso com o código '" + codigo + "'.");
                    break;

                case 4:
                    System.out.println("\nÁRVORE MORSE (compacta)");
                    arvore.desenhar();
                    break;

                case 5:
                    System.out.println("\nÁRVORE MORSE (detalhada)");
                    arvore.exibir();
                    break;

                case 6:
                    System.out.println("\nDigite o caractere que deseja buscar:");
                    System.out.print("> ");
                    String buscar = scanner.nextLine();
                    char resultado = arvore.buscar(buscar);
                    System.out.println("\nResultado da busca: " + (resultado != '\0' ? resultado : "Caractere não encontrado na árvore."));
                    break;

                case 0:
                    System.out.println("\nSaindo...");
                    break;

                default:
                    System.out.println("\nOpção inválida! Por favor, escolha uma opção válida.");
            }
        }
        scanner.close();
    }
}
