# Projeto Árvore Binária de Código Morse

## Sobre o Projeto

Este projeto implementa uma árvore binária para representar e traduzir códigos Morse. O objetivo principal é permitir a conversão entre texto e Código Morse, além de visualizar a estrutura da árvore que armazena os caracteres e seus respectivos códigos.

A ideia é que cada nó na árvore corresponda a um passo na formação de um código Morse. Movimentos para a esquerda representam um "." (ponto) e para a direita um "-" (traço).

## Estrutura da Árvore Morse

A árvore é construída da seguinte forma:

- A **raiz** é um nó inicial vazio.
- Cada **nó** (`Node.java`) pode armazenar um caractere (`valor`).
- Possui dois filhos: `esquerda` (para o ".") e `direita` (para o "-").
- Quando um caractere é inserido, seu código Morse determina o caminho da raiz até o nó onde o caractere será armazenado. Por exemplo, para 'A' (código ".-"), partindo da raiz, seguimos para a esquerda (.), depois para a direita (-), e o caractere 'A' é armazenado nesse nó final.

## Funcionalidades

O programa oferece as seguintes funcionalidades através de um menu interativo (`Main.java`):

1.  **Traduzir de Código Morse para Texto:**

    - Usuário insere uma mensagem em Código Morse (ex: `.... . .-.. .-.. --- / -- ..- -. -.. ---`).
    - Caracteres Morse devem ser separados por espaço.
    - Espaços entre palavras no Morse são representados por "/".
    - O programa retorna o texto traduzido (ex: "HELLO MUNDO").

2.  **Traduzir de Texto para Código Morse:**

    - Usuário insere um texto (ex: "HELLO WORLD").
    - O programa retorna a representação em Código Morse (ex: `.... . .-.. .-.. --- / .-- --- .-. .-.. -..`).
    - Espaços no texto original são convertidos para "/" no Morse.

3.  **Inserir Novo Caractere na Árvore:**

    - Permite adicionar um novo caractere e seu respectivo código Morse à árvore.
    - Valida se o código Morse inserido contém apenas "." e "-".
    - Verifica se o código já existe e, em caso afirmativo, pergunta se deseja substituir.

4.  **Visualizar Árvore Morse (Compacta):**

    - Exibe a árvore de forma hierárquica e compacta no console, mostrando os caracteres em seus respectivos nós. Nós intermediários (que são apenas caminhos) são representados por "\*".

5.  **Visualizar Árvore Morse (Detalhada):**

    - Lista todos os caracteres presentes na árvore junto com seus códigos Morse correspondentes.

6.  **Buscar Caractere na Árvore:**
    - Permite ao usuário digitar um código Morse e verificar qual caractere ele representa na árvore.

## Estrutura do Código

O projeto é composto por três arquivos Java principais, localizados na pasta `src/`:

- `Node.java`: Define a estrutura de um nó da árvore binária (contém o `valor` do caractere, e referências para os nós `esquerda` e `direita`).
- `ArvoreBinariaMorse.java`: Contém toda a lógica da árvore de Morse.
  - `inserir()`: Adiciona um caractere e seu código à árvore.
  - `buscar()`: Procura um caractere na árvore a partir de um código Morse.
  - `codificar()`: Encontra o código Morse de um determinado caractere.
  - `traduzirMorse()`: Converte uma string em Código Morse para texto.
  - `traduzirTexto()`: Converte uma string de texto para Código Morse.
  - `desenhar()`: Imprime a visualização compacta da árvore.
  - `exibir()`: Imprime a visualização detalhada da árvore (caractere: código).
  - `carregarTabelaPadrao()`: Inicializa a árvore com os caracteres alfanuméricos padrão do Código Morse.
  - `codigoValido()`: Verifica se uma string de código Morse é válida.
- `Main.java`: Apresenta o menu de console para o usuário interagir com as funcionalidades da árvore.

## Link da Apresentação

https://youtu.be/qSt31CwQbNA
