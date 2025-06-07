import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class TesteTabelaHash {
    private static final int[] TAMANHOS_TABELA = {1000, 10000, 100000};
    private static final int[] TAMANHOS_CONJUNTO = {1000000, 5000000, 20000000};
    private static final long SEED = 12345;
    private static int[][] conjuntosDados;

    public static void main(String[] args) {
        System.out.println("Iniciando testes de tabela hash...\n");

        System.out.println("Gerando conjuntos de dados...");
        gerarConjuntosDados();

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("resultados.csv"));
            writer.println("Implementacao,TamanhoTabela,FuncaoHash,TamanhoConjunto,TempoInsercao(ms),Colisoes,TempoBusca(ms),Comparacoes");

            System.out.println("\nTestando rehashing...");
            testarRehashing(writer);

            System.out.println("\nTestando encadeamento...");
            testarEncadeamento(writer);

            writer.close();
            System.out.println("\nfim dos testes!");
            System.out.println("Resultados salvos em resultados.csv");
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void gerarConjuntosDados() {
        conjuntosDados = new int[3][];
        Random random = new Random(SEED);

        for (int i = 0; i < 3; i++) {
            int tamanho = TAMANHOS_CONJUNTO[i];
            conjuntosDados[i] = new int[tamanho];
            System.out.println("Gerando conjunto " + (i+1) + " com " + tamanho + " elementos");

            for (int j = 0; j < tamanho; j++) {
                // número de 9 dígitos
                conjuntosDados[i][j] = 100000000 + random.nextInt(900000000);
            }
        }
    }

    private static void testarRehashing(PrintWriter writer) {
        for (int tamanhoTabela : TAMANHOS_TABELA) {
            for (TabelaHashRehashing.TipoFuncaoHash tipoFuncao : TabelaHashRehashing.TipoFuncaoHash.values()) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Testando Rehashing: Tabela=" + tamanhoTabela +
                            ", Função=" + tipoFuncao +
                            ", Conjunto=" + TAMANHOS_CONJUNTO[i]);

                    TabelaHashRehashing tabela = new TabelaHashRehashing(tamanhoTabela, tipoFuncao);

                    long inicioInsercao = System.currentTimeMillis();

                    // tenta inserir todos mas rehashing tem limite
                    for (int j = 0; j < conjuntosDados[i].length && j < tamanhoTabela; j++) {
                        tabela.inserir(new Registro(conjuntosDados[i][j]));
                    }

                    long tempoInsercao = System.currentTimeMillis() - inicioInsercao;
                    int elementosInseridos = tabela.getNumElementos();
                    int colisoes = tabela.getColisoes();

                    System.out.println("  Inseridos: " + elementosInseridos + " elementos");

                    // faz 5 buscas aleatorias
                    Random rand = new Random(SEED);
                    long inicioBusca = System.currentTimeMillis();
                    int totalComparacoes = 0;

                    for (int k = 0; k < 5; k++) {
                        int indice = rand.nextInt(elementosInseridos);
                        tabela.buscar(conjuntosDados[i][indice]);
                        totalComparacoes += tabela.getComparacoes();
                    }

                    long tempoBusca = System.currentTimeMillis() - inicioBusca;
                    double mediaComparacoes = totalComparacoes / 5.0;

                    writer.println("Rehashing," + tamanhoTabela + "," + tipoFuncao + "," +
                            TAMANHOS_CONJUNTO[i] + "," + tempoInsercao + "," +
                            colisoes + "," + tempoBusca + "," + mediaComparacoes);
                }
            }
        }
    }

    private static void testarEncadeamento(PrintWriter writer) {
        for (int tamanhoTabela : TAMANHOS_TABELA) {
            for (TabelaHashEncadeamento.TipoFuncaoHash tipoFuncao : TabelaHashEncadeamento.TipoFuncaoHash.values()) {
                for (int i = 0; i < conjuntosDados.length; i++) {
                    System.out.println("Testando Encadeamento: Tabela=" + tamanhoTabela +
                            ", Função=" + tipoFuncao +
                            ", Conjunto=" + TAMANHOS_CONJUNTO[i]);

                    TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(tamanhoTabela, tipoFuncao);

                    long inicioInsercao = System.currentTimeMillis();

                    for (int codigo : conjuntosDados[i]) {
                        tabela.inserir(new Registro(codigo));
                    }

                    long tempoInsercao = System.currentTimeMillis() - inicioInsercao;
                    int colisoes = tabela.getColisoes();

                    // faz 5 buscas aleatorias
                    Random rand = new Random(SEED);
                    long inicioBusca = System.currentTimeMillis();
                    int totalComparacoes = 0;

                    for (int k = 0; k < 5; k++) {
                        int indice = rand.nextInt(conjuntosDados[i].length);
                        tabela.buscar(conjuntosDados[i][indice]);
                        totalComparacoes += tabela.getComparacoes();
                    }

                    long tempoBusca = System.currentTimeMillis() - inicioBusca;
                    double mediaComparacoes = totalComparacoes / 5.0;

                    writer.println("Encadeamento," + tamanhoTabela + "," + tipoFuncao + "," +
                            TAMANHOS_CONJUNTO[i] + "," + tempoInsercao + "," +
                            colisoes + "," + tempoBusca + "," + mediaComparacoes);
                }
            }
        }
    }
}