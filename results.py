import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

plt.style.use('seaborn-v0_8')
sns.set_palette("husl")

df = pd.read_csv('resultados.csv')

def criar_graficos():
    # 1. Imagem 1: análise de desempenho
    fig = plt.figure(figsize=(20, 12))

    for idx, tamanho_conjunto in enumerate(df['TamanhoConjunto'].unique()):
        data = df[df['TamanhoConjunto'] == tamanho_conjunto]

        # coluna 1: tempo de inserção
        ax1 = plt.subplot(3, 4, idx * 4 + 1)
        pivot_insercao = data.pivot_table(values='TempoInsercao(ms)', index='TamanhoTabela', columns=['Implementacao', 'FuncaoHash'])
        pivot_insercao.plot(kind='bar', ax=ax1, legend=(idx==0))
        ax1.set_title(f'Tempo de Inserção - {tamanho_conjunto/1000000:.0f}M elementos')
        ax1.set_xlabel('Tamanho da Tabela')
        ax1.set_ylabel('Tempo (ms)')

        # coluna 2: colisões
        ax2 = plt.subplot(3, 4, idx * 4 + 2)
        pivot_colisoes = data.pivot_table(values='Colisoes', index='TamanhoTabela', columns=['Implementacao', 'FuncaoHash'])
        pivot_colisoes.plot(kind='bar', ax=ax2, legend=False)
        ax2.set_title(f'Número de Colisões - {tamanho_conjunto/1000000:.0f}M elementos')
        ax2.set_xlabel('Tamanho da Tabela')
        ax2.set_ylabel('Colisões')

        # coluna 3: tempo de busca
        ax3 = plt.subplot(3, 4, idx * 4 + 3)
        pivot_busca = data.pivot_table(values='TempoBusca(ms)', index='TamanhoTabela', columns=['Implementacao', 'FuncaoHash'])
        pivot_busca.plot(kind='bar', ax=ax3, legend=False)
        ax3.set_title(f'Tempo de Busca - {tamanho_conjunto/1000000:.0f}M elementos')
        ax3.set_xlabel('Tamanho da Tabela')
        ax3.set_ylabel('Tempo (ms)')

        # coluna 4: comparações
        ax4 = plt.subplot(3, 4, idx * 4 + 4)
        pivot_comparacoes = data.pivot_table(values='Comparacoes', index='TamanhoTabela', columns=['Implementacao', 'FuncaoHash'])
        pivot_comparacoes.plot(kind='bar', ax=ax4, legend=False)
        ax4.set_title(f'Comparações Médias - {tamanho_conjunto/1000000:.0f}M elementos')
        ax4.set_xlabel('Tamanho da Tabela')
        ax4.set_ylabel('Comparações')

    plt.suptitle('Análise Completa de Desempenho - Tabela Hash', fontsize=16, y=0.995)
    plt.tight_layout()
    plt.savefig('analise_desempenho.png', dpi=300, bbox_inches='tight')
    plt.close()

    # 2. Imagem 2: comparação geral e análise por função hash
    fig = plt.figure(figsize=(16, 12))

    # coluna 1: comparação geral (médias)
    ax = plt.subplot(2, 2, 1)
    summary = df.groupby(['Implementacao', 'FuncaoHash']).agg({
        'TempoInsercao(ms)': 'mean',
        'Colisoes': 'mean',
        'TempoBusca(ms)': 'mean',
        'Comparacoes': 'mean'
    }).round(2)

    summary.plot(kind='bar', ax=ax)
    ax.set_title('Comparação Geral - Médias de Todas as Configurações')
    ax.set_xlabel('Implementação e Função Hash')
    ax.set_ylabel('Valores Médios (escala log)')
    ax.set_yscale('log')
    ax.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
    plt.setp(ax.xaxis.get_majorticklabels(), rotation=45, ha='right')

    # coluna 2: eficiência por tamanho de tabela
    ax = plt.subplot(2, 2, 2)
    efficiency = df.groupby(['TamanhoTabela', 'Implementacao'])['Colisoes'].mean()
    efficiency.unstack().plot(kind='bar', ax=ax)
    ax.set_title('Colisões Médias por Tamanho de Tabela')
    ax.set_xlabel('Tamanho da Tabela')
    ax.set_ylabel('Colisões Médias')
    ax.legend(title='Implementação')

    # coluna 3: comparação de funções hash
    ax = plt.subplot(2, 1, 2)
    func_comparison = df.groupby(['FuncaoHash', 'Implementacao']).agg({
        'TempoInsercao(ms)': 'mean',
        'TempoBusca(ms)': 'mean'
    }).sum(axis=1).unstack()

    func_comparison.plot(kind='bar', ax=ax)
    ax.set_title('Desempenho Total por Função Hash')
    ax.set_xlabel('Função Hash')
    ax.set_ylabel('Tempo Total (ms)')
    ax.legend(title='Implementação')

    plt.suptitle('Análise Comparativa', fontsize=16)
    plt.tight_layout()
    plt.savefig('analise_comparativa.png', dpi=300, bbox_inches='tight')
    plt.close()

    print("Arquivos criados:")
    print("1. analise_desempenho.png - Visão completa do desempenho")
    print("2. analise_comparativa.png - Comparações e escalabilidade")

    gerar_relatorio_texto(df)

def gerar_relatorio_texto(df):
    # utilitário para formatar o tempo
    def formatar_tempo(ms):
        if ms < 1000:
            return f"{ms:.2f} ms"
        elif ms < 60000:
            return f"{ms:.2f} ms ({ms/1000:.2f} segundos)"
        elif ms < 3600000:
            minutos = ms / 60000
            return f"{ms:.2f} ms ({minutos:.2f} minutos)"
        else:
            horas = ms / 3600000
            return f"{ms:.2f} ms ({horas:.2f} horas)"
    
    with open('relatorio_texto.txt', 'w', encoding='utf-8') as f:
        f.write("RELATÓRIO - ANÁLISE TABELA HASH\n\n")

        # melhor configuração geral
        melhor = df.loc[df['TempoInsercao(ms)'].idxmin()]
        f.write(f"MELHOR CONFIGURAÇÃO (Tempo de Inserção):\n")
        f.write(f"- Implementação: {melhor['Implementacao']}\n")
        f.write(f"- Função Hash: {melhor['FuncaoHash']}\n")
        f.write(f"- Tamanho Tabela: {melhor['TamanhoTabela']}\n")
        f.write(f"- Tempo: {formatar_tempo(melhor['TempoInsercao(ms)'])}\n\n")

        f.write("MÉDIAS POR IMPLEMENTAÇÃO:\n")
        for impl in df['Implementacao'].unique():
            data = df[df['Implementacao'] == impl]
            f.write(f"\n{impl}:\n")
            f.write(f"- Tempo médio inserção: {formatar_tempo(data['TempoInsercao(ms)'].mean())}\n")
            f.write(f"- Colisões médias: {data['Colisoes'].mean():.0f}\n")
            f.write(f"- Tempo médio busca: {formatar_tempo(data['TempoBusca(ms)'].mean())}\n")
            f.write(f"- Comparações médias: {data['Comparacoes'].mean():.2f}\n")

        f.write("\nMÉDIAS POR FUNÇÃO HASH:\n")
        for func in df['FuncaoHash'].unique():
            data = df[df['FuncaoHash'] == func]
            tempo_total = data['TempoInsercao(ms)'].mean() + data['TempoBusca(ms)'].mean()
            f.write(f"\n{func}:\n")
            f.write(f"- Tempo médio total: {formatar_tempo(tempo_total)}\n")
            f.write(f"- Colisões médias: {data['Colisoes'].mean():.0f}\n")
        
        f.write("\nMÉDIAS POR IMPLEMENTAÇÃO E FUNÇÃO HASH:\n")
        for impl in df['Implementacao'].unique():
            f.write(f"\n{impl}:\n")
            for func in df['FuncaoHash'].unique():
                data = df[(df['Implementacao'] == impl) & (df['FuncaoHash'] == func)]
                if not data.empty:
                    f.write(f"  {func}:\n")
                    f.write(f"  - Tempo médio inserção: {formatar_tempo(data['TempoInsercao(ms)'].mean())}\n")
                    f.write(f"  - Colisões médias: {data['Colisoes'].mean():.0f}\n")
                    f.write(f"  - Tempo médio busca: {formatar_tempo(data['TempoBusca(ms)'].mean())}\n")
                    f.write(f"  - Comparações médias: {data['Comparacoes'].mean():.2f}\n")
                    f.write(f"  - Tempo médio total: {formatar_tempo(data['TempoInsercao(ms)'].mean() + data['TempoBusca(ms)'].mean())}\n")

        f.write("\nMÉDIAS POR TAMANHO DE TABELA:\n")
        for impl in df['Implementacao'].unique():
            f.write(f"\n{impl}:\n")
            for func in df['FuncaoHash'].unique():
                data_func = df[(df['Implementacao'] == impl) & (df['FuncaoHash'] == func)]
                if not data_func.empty:
                    f.write(f"  {func}:\n")
                    for tamanho in sorted(df['TamanhoTabela'].unique()):
                        data = data_func[data_func['TamanhoTabela'] == tamanho]
                        if not data.empty:
                            f.write(f"    Tamanho {tamanho}:\n")
                            f.write(f"      - Tempo médio inserção: {formatar_tempo(data['TempoInsercao(ms)'].mean())}\n")
                            f.write(f"      - Colisões médias: {data['Colisoes'].mean():.0f}\n")
                            f.write(f"      - Tempo médio busca: {formatar_tempo(data['TempoBusca(ms)'].mean())}\n")
                            f.write(f"      - Comparações médias: {data['Comparacoes'].mean():.2f}\n")

    print("3. relatorio_texto.txt - Relatório em texto")

if __name__ == "__main__":
    criar_graficos()