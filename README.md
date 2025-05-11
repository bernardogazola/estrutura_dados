# Implementação de Árvore Trie em Java

Este projeto apresenta uma implementação simples de uma Árvore Trie em Java, focada nas operações básicas de inserção, busca e remoção (lógica) de palavras.

## O que é uma Árvore Trie?

A Trie, também conhecida como árvore de prefixos, é uma estrutura de dados em árvore otimizada para armazenar e buscar strings. Cada nó representa um caractere, e os caminhos da raiz até os nós marcados como "fim de palavra" formam as palavras armazenadas.

É especialmente útil para funcionalidades como autocomplete e verificação ortográfica, pois permite buscas eficientes baseadas em prefixos.

## Estrutura do Projeto

O código está organizado nos seguintes arquivos principais:

- `Node.java`: Define a estrutura de um nó da Trie. Cada nó contém:

  - Um array de `filhos` (outros nós), onde cada posição corresponde a uma letra do alfabeto (a-z).
  - Um booleano `ehFimDaPalavra` que indica se o caminho da raiz até este nó forma uma palavra completa.

- `ArvoreTrie.java`: Contém a lógica principal da Trie, incluindo:

  - A `raiz` da árvore.
  - O método `obterIndiceCaractere(char ch)`: Converte um caractere para um índice numérico (0-25) para ser usado no array `filhos`.
  - O método `inserir(String palavra)`: Adiciona uma palavra à Trie. Cria novos nós conforme necessário e marca o último nó da palavra como `ehFimDaPalavra`.
  - O método `buscar(String palavra)`: Verifica se uma palavra existe na Trie. Percorre a árvore de acordo com os caracteres da palavra e checa se o nó final está marcado como `ehFimDaPalavra`.
  - O método `remover(String palavra)`: Realiza uma remoção lógica da palavra. Encontra a palavra e desmarca seu último nó (define `ehFimDaPalavra` como `false`). Os nós não são fisicamente removidos para preservar prefixos de outras palavras.

- `Main.java`: Um exemplo de como utilizar a `ArvoreTrie`, demonstrando as operações de inserção, busca e remoção.

## Funcionalidades Implementadas

- **Inserção de Palavras:** Palavras compostas por letras minúsculas (a-z) podem ser inseridas. Caracteres inválidos são ignorados durante a inserção.
- **Busca de Palavras:** É possível buscar por palavras completas. A busca retorna `true` se a palavra existe e está marcada como "fim de palavra", e `false` caso contrário.
- **Remoção Lógica de Palavras:** Palavras podem ser "removidas" logicamente. Isso significa que elas não são mais consideradas palavras completas na Trie (seu marcador `ehFimDaPalavra` é definido como `false`), mas os nós que as compõem permanecem se fizerem parte de outras palavras.

## Observações

- A implementação atual suporta apenas letras minúsculas de 'a' a 'z'.
- A remoção implementada é lógica. Para uma remoção física (onde os nós são de fato deletados se não forem mais necessários), seria preciso uma lógica adicional para verificar se um nó pode ser removido sem afetar outras palavras.

## Link da Apresentação

https://youtu.be/QFVSIbqzXBs
