package br.edu.udesc.search;

/**
 * Pesquisa usando o método da força bruta (Naive)
 * @author udesc
 *
 */
public class SearchByNaiveStrategy extends ASearchStrategy {
	/**
	 * Pesquisa o número de ocorrências de um padrão em um conteúdo usando método Naive
	 * (força bruta).
	 * 
	 * @author Leo_e_Mentz
     * @param content O conteúdo onde vai pesquisar o pattern
     * @param pattern O padrão a pesquisar em content
     * @return O número de ocorrências de pattern em content
     */
	public int searchFile(String content, String pattern) {
		int contador = 0;
		int n = content.length();
		int m = pattern.length();
		for (int i = 0; i < n - m + 1; i++) {
			int j = 0;
			while (j < m && content.charAt(i + j) == pattern.charAt(j)) {
				j++;
			}
			if (j == m)
				contador++;
		}
		return contador;
	}

}
