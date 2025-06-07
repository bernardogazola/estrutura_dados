public class TabelaHashRehashing {
    private Registro[] tabela;
    private int tamanho;
    private int numElementos;
    private int colisoes;
    private int comparacoes;
    private TipoFuncaoHash tipoFuncao;

    public enum TipoFuncaoHash {
        DIVISAO, MULTIPLICACAO, DOBRAMENTO
    }

    public TabelaHashRehashing(int tamanho, TipoFuncaoHash tipoFuncao) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
        this.numElementos = 0;
        this.colisoes = 0;
        this.comparacoes = 0;
        this.tipoFuncao = tipoFuncao;
    }

    private int funcaoHash(int chave) {
        switch (tipoFuncao) {
            case DIVISAO:
                return funcaoHashDivisao(chave);
            case MULTIPLICACAO:
                return funcaoHashMultiplicacao(chave);
            case DOBRAMENTO:
                return funcaoHashDobramento(chave);
            default:
                return 0;
        }
    }

    // hash por divisão
    private int funcaoHashDivisao(int chave) {
        return chave % tamanho;
    }

    // hash por multiplicação
    private int funcaoHashMultiplicacao(int chave) {
        double A = 0.6180339887; // constante da razão áurea (peguei na internet)
        double parte = chave * A;
        parte = parte - (int) parte;
        return (int) (tamanho * parte);
    }

    // hash por dobramento
    private int funcaoHashDobramento(int chave) {
        String chaveStr = String.format("%09d", chave);
        int soma = 0;

        // Divide em grupos de 3 dígitos e soma
        for (int i = 0; i < 9; i += 3) {
            soma += Integer.parseInt(chaveStr.substring(i, i + 3));
        }

        return soma % tamanho;
    }

    // rehashing simples
    private int rehash(int posicao) {
        return (posicao + 1) % tamanho;
    }

    // Inserir elemento
    public void inserir(Registro registro) {
        if (numElementos >= tamanho) {
            return; // tabela cheia
        }

        int posicao = funcaoHash(registro.getCodigo());

        // se a posição está vazia, insere
        if (tabela[posicao] == null) {
            tabela[posicao] = registro;
            numElementos++;
        } else {
            // teve colisao
            colisoes++;

            // procura proxima posicao livre
            int tentativas = 0;
            while (tabela[posicao] != null && tentativas < tamanho) {
                posicao = rehash(posicao);
                tentativas++;
            }

            if (tabela[posicao] == null) {
                tabela[posicao] = registro;
                numElementos++;
            }
        }
    }

    // Buscar elemento
    public Registro buscar(int chave) {
        comparacoes = 0;
        int posicao = funcaoHash(chave);

        while (tabela[posicao] != null) {
            comparacoes++;

            if (tabela[posicao].getCodigo() == chave) {
                return tabela[posicao];
            }

            posicao = rehash(posicao);

            // evita loop infinito
            if (comparacoes > tamanho) {
                break;
            }
        }

        return null;
    }

    public int getColisoes() {
        return colisoes;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public int getNumElementos() {
        return numElementos;
    }
}