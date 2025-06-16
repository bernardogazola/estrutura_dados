import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TesteAlgoritmos {

    private static final int[] TAMANHOS = {1000, 10000, 100000, 500000, 1000000};
    private static final int NUM_RODADAS = 5;

    private static final long SEED = 12345;

    public static void main(String[] args) {
        AlgoritmoOrdenacao[] algoritmos = {
                new SelectionSort(),
                new QuickSort(),
                new HeapSort(),
                new CountingSort()
        };

        try {
            FileWriter writer = new FileWriter("resultados.csv");
            writer.write("Algoritmo,Tamanho,Rodada,Tempo(ms),Trocas,Iteracoes\n");

            for (AlgoritmoOrdenacao algoritmo : algoritmos) {
                System.out.println("\n=== Testando " + algoritmo.getNome() + " ===");

                for (int tamanho : TAMANHOS) {
                    System.out.println("\nTamanho: " + tamanho);

                    for (int rodada = 0; rodada < NUM_RODADAS; rodada++) {
                        int[] vetor = gerarVetor(tamanho, SEED);

                        algoritmo.executar(vetor, tamanho);

                        double tempoMs = algoritmo.getTempoExecucao() / 1_000_000.0;

                        writer.write(String.format("%s,%d,%d,%.3f,%d,%d\n",
                                algoritmo.getNome(),
                                tamanho,
                                rodada + 1,
                                tempoMs,
                                algoritmo.getNumeroTrocas(),
                                algoritmo.getNumeroIteracoes()
                        ));

                        System.out.printf("  Rodada %d: %.3f ms\n", rodada + 1, tempoMs);
                    }
                }
            }

            writer.close();
            System.out.println("\nResultados salvos em 'resultados.csv'");

        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }

    private static int[] gerarVetor(int tamanho, long seed) {
        Random random = new Random(seed);
        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(1000000);
        }

        return vetor;
    }
}