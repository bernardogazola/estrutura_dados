# APS_1

Utilizei como base os conceitos vistos nos materiais da disciplina, como `01 - Pilha .pdf`, `02 - Filas.pdf`, `03 - Lista Encadeada.pdf` e `Revisão Pilha e Fila - algoritmo para implementacao .pdf`.

## Como Executar

**Passos:**

1.  **Compile:** No terminal, dentro da pasta que contém o diretório `aps1`, execute:

    ```bash
    javac *.java
    ```

    _(Isso compilará todos os arquivos: PilhaListaEncadeada, FilaListaEncadeada, FilaCircularEstatica e Merge)_

2.  **Execute:**
    - **Testar Pilha:** `java PilhaListaEncadeada` (Abrirá um menu interativo)
    - **Testar Fila:** `java FilaListaEncadeada` (Abrirá um menu interativo)
    - **Testar Merge:** `java Merge` (Pedirá para digitar os elementos ORDENADOS de A e B dinâmicas, depois A e B estáticas, e mostrará os resultados dos merges)

### 1. Pilha Dinâmica (`PilhaListaEncadeada.java`)

- **O que faz:** Implementa uma Pilha (o último que entra é o primeiro que sai - LIFO).
- **Como foi feito:** Usei uma lista encadeada simples. Cada elemento (`No`) guarda o `dado` (como visto no zip contendo a lista encadeada que o professor fez em sala) e aponta pro `proximo`. A classe principal só precisa de um ponteiro pro `topo`.
- **Operações:**
  - `insere(valor)`: Cria um novo nó, faz ele apontar pro topo antigo e atualiza o topo.
  - `remove()`: Pega o valor do topo, atualiza o topo pro próximo e retorna o valor. Se tentar remover com a pilha vazia, imprime um erro e retorna um valor especial -1.
  - `imprime()`: Lista todos os elementos da pilha, do topo para a base.
- **Teste:** O `main` dessa classe tem um menu pra gente poder testar na hora: inserir, remover, ver a pilha. A entrada de dados verifica se é número usando `hasNextInt()` pra não dar erro se digitar letra.

### 2. Fila Dinâmica (`FilaListaEncadeada.java`)

- **O que faz:** Implementa uma Fila (o primeiro que entra é o primeiro que sai - FIFO).
- **Como foi feito:** Usei lista encadeada também, mas precisei de dois ponteiros: `inicio` e `fim`. Isso deixa a inserção (no fim) e a remoção (do início) rápidas. Cada `No` também tem `dado` e `proximo`.
- **Operações:**
  - `insere(valor)`: Cria um nó. Se a fila tá vazia, `inicio` e `fim` apontam pra ele. Se não, o antigo `fim.proximo` aponta pro novo, e o `fim` é atualizado.
  - `remove()`: Pega o valor do `inicio`, atualiza o `inicio` pro próximo. Tem um detalhe: se o `inicio` ficar `null` (fila esvaziou), tem que atualizar o `fim` pra `null` também.
  - `imprime()`: Lista todos os elementos da fila, do início para o fim.
  - `consultarInicio()`: Adicionei essa pra poder usar no Merge depois. Só olha quem tá no início, sem remover.
- **Teste:** O `main` aqui também é interativo com menu, igual ao da Pilha, usando `hasNextInt()` para validar a entrada.

### 3. Merge de Filas Ordenadas (`Merge.java` e `FilaCircularEstatica.java`)

- **O que faz:** Combina duas filas (A e B), que a gente assume que já estão ordenadas, em uma terceira fila (C) que também fica ordenada.
- **"2 Programas Diferentes":** A descrição pedia pra fazer usando "vetores e listas encadeadas, ou seja, 2 programas diferentes". Pra atender isso, fiz duas versões da função de merge dentro da classe `MergeFilas`:
  1.  **`merge(FilaDinamica a, FilaDinamica b)`:** Recebe e retorna a `FilaDinamica` (lista encadeada) que implementei na Parte 2.
  2.  **`mergeEstatico(FilaCircularEstatica a, FilaCircularEstatica b)`:** Recebe e retorna uma `FilaCircularEstatica` (vetor). Pra isso, precisei incluir a classe `FilaCircularEstatica.java` aqui na APS_1, baseada na implementação do TDE 01 / PDF de Revisão.
- **Lógica do Merge:** É a mesma pras duas versões. Enquanto A e B tem elementos, compara quem tá no começo de cada uma (`consultarInicio`). Remove o menor da sua fila original (usando `remove()` ou `removeElemento()`, que já avisam qual elemento saiu) e insere ele na fila C (usando `insere()` ou `insereElemento()`). Quando uma fila esvazia, só copia o resto da outra pra C.
- **Teste:** O `main` do `MergeFilas.java` testa as **duas** versões. Ele pede pro usuário digitar os elementos ordenados para as filas A e B (primeiro para as dinâmicas, depois define a capacidade e pede para as estáticas). Depois de rodar cada merge, ele mostra a fila C resultante. A entrada de dados também usa `hasNextInt()`.
