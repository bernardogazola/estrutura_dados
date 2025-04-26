import java.util.Scanner;

public class SistemaAtendimento {

    public static void carregarHistorico(Pilha pilha) {
        Elemento[] historico = new Elemento[] {
                new Elemento("REQ001", "Instalação de software", "2024-08-20 10:30"),
                new Elemento("REQ002", "Manutenção preventiva", "2024-08-20 11:00"),
                new Elemento("REQ003", "Atualização de sistema", "2024-08-20 11:30"),
                new Elemento("REQ004", "Suporte técnico", "2024-08-20 12:00"),
                new Elemento("REQ005", "Troca de equipamento", "2024-08-20 12:30"),
                new Elemento("REQ006", "Consulta de garantia", "2024-08-20 13:00"),
                new Elemento("REQ007", "Reparo de impressora", "2024-08-20 13:30"),
                new Elemento("REQ008", "Configuração de rede", "2024-08-20 14:00"),
                new Elemento("REQ009", "Restauração de dados", "2024-08-20 14:30"),
                new Elemento("REQ010", "Consulta técnica", "2024-08-20 15:00")
        };

        for (int i = historico.length - 1; i >= 0; i--) {
            pilha.empilhar(historico[i]);
        }
    }

    public static void carregarFilaAtendimento(Fila fila) {
        Elemento[] filaAtendimento = new Elemento[] {
                new Elemento("CLI001", "Maria Silva", "Dúvida sobre produto"),
                new Elemento("CLI002", "João Souza", "Reclamação de serviço"),
                new Elemento("CLI003", "Ana Costa", "Solicitação de reembolso"),
                new Elemento("CLI004", "Pedro Alves", "Informações de entrega"),
                new Elemento("CLI005", "Carla Dias", "Agendamento de visita"),
                new Elemento("CLI006", "Lucas Martins", "Alteração de pedido"),
                new Elemento("CLI007", "Patrícia Rocha", "Cancelamento de contrato"),
                new Elemento("CLI008", "Rafael Lima", "Renovação de assinatura"),
                new Elemento("CLI009", "Fernanda Gomes", "Suporte para instalação"),
                new Elemento("CLI010", "Carlos Eduardo", "Pedido de orçamento")
        };

        for (Elemento cliente : filaAtendimento) {
            fila.enfileirar(cliente);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pilha historico = new Pilha();
        Fila filaAtendimento = new Fila();

        carregarHistorico(historico);
        carregarFilaAtendimento(filaAtendimento);

        int opcao = 0;

        do {
            System.out.println("\n==== SISTEMA DE GERENCIAMENTO DE ATENDIMENTO ====");
            System.out.println("1. Adicionar solicitação ao histórico");
            System.out.println("2. Consultar última solicitação do histórico");
            System.out.println("3. Remover última solicitação do histórico");
            System.out.println("4. Exibir histórico de solicitações");
            System.out.println("5. Adicionar cliente à fila de atendimento");
            System.out.println("6. Consultar próximo cliente na fila");
            System.out.println("7. Atender próximo cliente");
            System.out.println("8. Exibir fila de atendimento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID da solicitação: ");
                    String id = scanner.nextLine();
                    System.out.print("Digite a descrição da solicitação: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite a data e hora (formato: YYYY-MM-DD HH:MM): ");
                    String dataHora = scanner.nextLine();

                    Elemento novaSolicitacao = new Elemento(id, descricao, dataHora);
                    historico.empilhar(novaSolicitacao);
                    break;

                case 2:
                    Elemento ultimaSolicitacao = historico.consultarTopo();
                    if (ultimaSolicitacao != null) {
                        System.out.println("\nÚltima solicitação do histórico:");
                        System.out.println("ID: " + ultimaSolicitacao.id + ", Descrição: " + ultimaSolicitacao.descricao + ", Data/Hora: " + ultimaSolicitacao.informacaoAdicional);
                    }
                    break;

                case 3:
                    historico.desempilhar();
                    break;

                case 4:
                    historico.exibirPilha();
                    break;

                case 5:
                    System.out.print("Digite o ID do cliente: ");
                    String idCliente = scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o motivo do atendimento: ");
                    String motivoAtendimento = scanner.nextLine();

                    Elemento novoCliente = new Elemento(idCliente, nomeCliente, motivoAtendimento);
                    filaAtendimento.enfileirar(novoCliente);
                    break;

                case 6:
                    Elemento proximoCliente = filaAtendimento.consultarFrente();
                    if (proximoCliente != null) {
                        System.out.println("\nPróximo cliente na fila:");
                        System.out.println("ID: " + proximoCliente.id + ", Nome: " + proximoCliente.descricao + ", Motivo: " + proximoCliente.informacaoAdicional);
                    }
                    break;

                case 7:
                    filaAtendimento.desenfileirar();
                    break;

                case 8:
                    filaAtendimento.exibirFila();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}