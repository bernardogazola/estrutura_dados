# Sistema de Gerenciamento de Atendimento ao Cliente

## Descrição do Projeto

Este projeto foi desenvolvido como parte da disciplina de Resolução de Problemas Estruturados em Computação e consiste na implementação de um sistema simples de gerenciamento de atendimento ao cliente em Java. O objetivo principal foi aplicar os conceitos de estruturas de dados, especificamente Pilha e Fila, utilizando listas encadeadas para sua implementação, sem o uso de bibliotecas ou funções prontas do Java Collections Framework, conforme as restrições especificadas.

O sistema gerencia duas entidades principais:

1.  **Histórico de Solicitações:** Utiliza uma Pilha (LIFO - Last-In, First-Out) para armazenar o histórico das solicitações de serviço feitas.
2.  **Fila de Atendimento:** Utiliza uma Fila (FIFO - First-In, First-Out) para gerenciar a ordem em que os clientes serão atendidos.

## Estrutura do Código

O projeto está organizado nas seguintes classes Java dentro do diretório `src/`:

- **`Elemento.java`**: Classe simples que representa a entidade de dados armazenada tanto na pilha quanto na fila. Contém atributos como `id`, `descricao`, e `informacaoAdicional` (usado para data/hora na pilha e motivo do atendimento na fila).
- **`No.java`**: Representa o nó da lista encadeada. Cada nó armazena um `Elemento` (`dado`) e uma referência (`proximo`) para o próximo nó na estrutura.
- **`Pilha.java`**: Implementa a estrutura de dados Pilha usando uma lista encadeada.
  - Mantém uma referência `topo` para o último elemento adicionado.
  - Possui métodos como `empilhar` (adicionar ao topo), `desempilhar` (remover do topo), `consultarTopo` (ver o topo sem remover), `estaVazia` (verificar se a pilha está vazia) e `exibirPilha` (listar os elementos).
- **`Fila.java`**: Implementa a estrutura de dados Fila usando uma lista encadeada.
  - Mantém referências `frente` (para o início da fila) e `tras` (para o fim da fila).
  - Possui métodos como `enfileirar` (adicionar ao fim), `desenfileirar` (remover do início), `consultarFrente` (ver o início sem remover), `estaVazia` (verificar se a fila está vazia) e `exibirFila` (listar os elementos).
- **`SistemaAtendimento.java`**: Classe principal que contém o método `main`.
  - Inicializa a `Pilha` e a `Fila`.
  - Carrega dados iniciais de exemplo usando os métodos `carregarHistorico` e `carregarFilaAtendimento`.
  - Apresenta um menu interativo ao usuário para manipular a pilha de histórico e a fila de atendimento (adicionar, remover, consultar, exibir).
  - Realiza a leitura das entradas do usuário e trata possíveis erros de formato.

## Implementação das Estruturas (Lista Encadeada)

Tanto a `Pilha` quanto a `Fila` foram implementadas utilizando a abordagem de lista encadeada simples, gerenciando os nós (`No`) e suas referências (`proximo`) manualmente para realizar as operações de inserção e remoção, respeitando as regras de cada estrutura (LIFO para Pilha, FIFO para Fila).

## Autores

- Bernardo Gazola
- Lucas Kempa
- Brayan Calisto

## Link da Apresentação

https://youtu.be/KdGmmCFDkjE
