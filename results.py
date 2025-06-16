import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

plt.style.use('seaborn-v0_8-darkgrid')
sns.set_palette("husl")

print("Carregando dados...")
df = pd.read_csv('resultados.csv')

df_stats = df.groupby(['Algoritmo', 'Tamanho']).agg({
    'Tempo(ms)': ['mean', 'std'],
    'Trocas': ['mean', 'std'],
    'Iteracoes': ['mean', 'std']
}).reset_index()

df_stats.columns = ['Algoritmo', 'Tamanho',
                    'Tempo_media', 'Tempo_std',
                    'Trocas_media', 'Trocas_std',
                    'Iteracoes_media', 'Iteracoes_std']

print("\nGerando graficos...")

# 1. Grafico principal de tempo (linha com erro)
fig, ax = plt.subplots(figsize=(12, 8))
for algo in df_stats['Algoritmo'].unique():
    dados = df_stats[df_stats['Algoritmo'] == algo]
    ax.errorbar(dados['Tamanho'], dados['Tempo_media'],
                yerr=dados['Tempo_std'],
                marker='o', markersize=8, linewidth=2.5,
                capsize=5, capthick=2, label=algo)

ax.set_xlabel('Tamanho do Vetor', fontsize=12)
ax.set_ylabel('Tempo de Execução (ms)', fontsize=12)
ax.set_title('Comparação de Tempo de Execução dos Algoritmos', fontsize=14, fontweight='bold')
ax.set_xscale('log')
ax.set_yscale('log')
ax.legend(fontsize=11)
ax.grid(True, alpha=0.3)
plt.tight_layout()
plt.savefig('01_tempo_execucao_geral.png', dpi=300)
plt.close()

# 2. Graficos separados por tamanho (subplots)
tamanhos = df['Tamanho'].unique()
fig, axes = plt.subplots(2, 3, figsize=(15, 10))
axes = axes.flatten()

for i, tam in enumerate(tamanhos):
    dados_tam = df[df['Tamanho'] == tam]
    media_tempo = dados_tam.groupby('Algoritmo')['Tempo(ms)'].mean().sort_values()

    axes[i].bar(media_tempo.index, media_tempo.values,
                color=['#FF6B6B', '#4ECDC4', '#45B7D1', '#96CEB4'])
    axes[i].set_title(f'Tamanho: {tam:,} elementos', fontsize=12)
    axes[i].set_ylabel('Tempo (ms)')
    axes[i].tick_params(axis='x', rotation=45)

    for j, (alg, val) in enumerate(media_tempo.items()):
        axes[i].text(j, val + val*0.05, f'{val:.2f}',
                    ha='center', va='bottom', fontsize=9)

if len(tamanhos) < 6:
    fig.delaxes(axes[-1])

plt.suptitle('Tempo de Execução por Tamanho de Vetor', fontsize=16, fontweight='bold')
plt.tight_layout()
plt.savefig('02_tempo_por_tamanho.png', dpi=600)
plt.close()

# 3. Grafico especifico para o maior tamanho (1M)
algoritmos = df_stats['Algoritmo'].unique()
df_1m = df[df['Tamanho'] == 1000000]
fig, axes = plt.subplots(1, 3, figsize=(15, 5))

medias = df_1m.groupby('Algoritmo')['Tempo(ms)'].mean().sort_values()
bars1 = axes[0].bar(range(len(medias)), medias.values,
                    color=['#2ecc71', '#3498db', '#e74c3c', '#f39c12'])
axes[0].set_xticks(range(len(medias)))
axes[0].set_xticklabels(medias.index, rotation=45)
axes[0].set_ylabel('Tempo (ms)')
axes[0].set_title('Tempo de Execução (1M elementos)')

for i, v in enumerate(medias.values):
    axes[0].text(i, v + v*0.05, f'{v:.1f}ms', ha='center', va='bottom')

medias_trocas = df_1m.groupby('Algoritmo')['Trocas'].mean().sort_values()
bars2 = axes[1].bar(range(len(medias_trocas)), medias_trocas.values,
                    color=['#2ecc71', '#3498db', '#e74c3c', '#f39c12'])
axes[1].set_xticks(range(len(medias_trocas)))
axes[1].set_xticklabels(medias_trocas.index, rotation=45)
axes[1].set_ylabel('Número de Trocas')
axes[1].set_title('Número de Trocas (1M elementos)')

medias_iter = df_1m.groupby('Algoritmo')['Iteracoes'].mean().sort_values()
bars3 = axes[2].bar(range(len(medias_iter)), medias_iter.values,
                    color=['#2ecc71', '#3498db', '#e74c3c', '#f39c12'])
axes[2].set_xticks(range(len(medias_iter)))
axes[2].set_xticklabels(medias_iter.index, rotation=45)
axes[2].set_ylabel('Número de Iterações')
axes[2].set_title('Número de Iterações (1M elementos)')

plt.suptitle('Comparação para 1 Milhão de Elementos', fontsize=14, fontweight='bold')
plt.tight_layout()
plt.savefig('03_comparacao_1milhao.png', dpi=300)
plt.close()

print("\n=== ESTATISTICAS RESUMIDAS ===")
print("\nMédias por Algoritmo e Tamanho:")
print(df_stats.to_string(index=False, float_format='%.2f'))

# salva tabela formatada
print("\nGerando tabela markdown...")
with open('tabela_completa.md', 'w') as f:
    f.write("# Resultados Completos da Análise\n\n")
    f.write("## Tabela de Médias\n\n")
    f.write("| Algoritmo | Tamanho | Tempo (ms) | Desvio Tempo | Trocas | Desvio Trocas | Iterações | Desvio Iter |\n")
    f.write("|-----------|---------|------------|--------------|---------|---------------|-----------|-------------|\n")

    for _, row in df_stats.iterrows():
        f.write(f"| {row['Algoritmo']} | {row['Tamanho']:,} | "
                f"{row['Tempo_media']:.2f} | {row['Tempo_std']:.2f} | "
                f"{row['Trocas_media']:.0f} | {row['Trocas_std']:.0f} | "
                f"{row['Iteracoes_media']:.0f} | {row['Iteracoes_std']:.0f} |\n")

    f.write("\n## Análise de Performance\n\n")

    # melhor algoritmo por tamanho
    f.write("### Melhor Algoritmo por Tamanho (Tempo)\n\n")
    for tam in tamanhos:
        dados_tam = df_stats[df_stats['Tamanho'] == tam]
        melhor = dados_tam.loc[dados_tam['Tempo_media'].idxmin()]
        f.write(f"- **{tam:,} elementos**: {melhor['Algoritmo']} ({melhor['Tempo_media']:.2f} ms)\n")

print("\n=== GRAFICOS GERADOS COM SUCESSO! ===")
print("\nArquivos salvos:")
print("1. 01_tempo_execucao_geral.png - Comparação geral com barras de erro")
print("2. 02_tempo_por_tamanho.png - Tempo separado por tamanho de vetor")
print("3. 03_comparacao_1milhao.png - Foco em vetores de 1M elementos")
print("\nTabela: tabela_completa.md")

# analise extra no console
print("\n=== ANALISE ADICIONAL ===")
print("\nRanking de Tempo (1M elementos):")
ranking = df_1m.groupby('Algoritmo')['Tempo(ms)'].mean().sort_values()
for i, (algo, tempo) in enumerate(ranking.items(), 1):
    print(f"{i}º {algo}: {tempo:.2f} ms")

print("\nTaxa de crescimento médio (1K → 1M):")
for algo in algoritmos:
    tempo_1k = df_stats[(df_stats['Algoritmo'] == algo) &
                        (df_stats['Tamanho'] == 1000)]['Tempo_media'].values[0]
    tempo_1m = df_stats[(df_stats['Algoritmo'] == algo) &
                        (df_stats['Tamanho'] == 1000000)]['Tempo_media'].values[0]
    taxa = tempo_1m / tempo_1k
    print(f"{algo}: {taxa:.1f}x")