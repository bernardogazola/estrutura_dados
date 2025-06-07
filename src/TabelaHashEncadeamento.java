public class TabelaHashEncadeamento {
    private class No {
        Registro registro;
        No proximo;

        public No(Registro registro) {
            this.registro = registro;
            this.proximo = null;
        }
    }

    private No[] tabela;
    private int tamanho;
    private int numElementos;
    private int colisoes;
    private int comparacoes;
    private TipoFuncaoHash tipoFuncao;

    public enum TipoFuncaoHash {
        DIVISAO, MULTIPLICACAO, DOBRAMENTO
    }

    public TabelaHashEncadeamento(int tamanho, TipoFuncaoHash tipoFuncao) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];
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

    // Inserir elemento
    public void inserir(Registro registro) {
        int posicao = funcaoHash(registro.getCodigo());

        // se a posição está vazia, insere direto
        if (tabela[posicao] == null) {
            // posicao vazia, insere direto
            tabela[posicao] = new No(registro);
        } else {
            // ja tem elemento, colisao
            colisoes++;

            // vai ate o final da lista
            No atual = tabela[posicao];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }

            // insere no final
            atual.proximo = new No(registro);
        }

        numElementos++;
    }

    // Buscar elemento
    public Registro buscar(int chave) {
        comparacoes = 0;
        int posicao = funcaoHash(chave);

        // se a posição está vazia, não encontrou
        if (tabela[posicao] == null) {
            return null; // nao achou
        }

        // percorre a lista
        No atual = tabela[posicao];
        while (atual != null) {
            comparacoes++;
            if (atual.registro.getCodigo() == chave) {
                return atual.registro;
            }
            atual = atual.proximo;
        }

        return null;
    }

    public int getColisoes() {
        return colisoes;
    }

    public int getComparacoes() {
        return comparacoes;
    }
}