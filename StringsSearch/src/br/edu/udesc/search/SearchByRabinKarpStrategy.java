package br.edu.udesc.search;

public class SearchByRabinKarpStrategy extends ASearchStrategy {
	/**
	 * Pesquisa o número de ocorrências de um padrão em um conteúdo usando RabinKarp.
	 * 
	 * @author Leo_e_Mentz
     * @param content O conteúdo onde vai pesquisar o pattern
     * @param pattern O padrão a pesquisar em content
     * @return O número de ocorrências de pattern em content
     */
	public int searchFile(String content, String word) {
		int M = word.length();
		int N = content.length();
		int contador = 0;
		long patHash = hash(word);

		for (int i = 0; i <= N - M; i++) {
			long txtHash = hash(content.substring(i, i+M));
		    if (patHash == txtHash)
		    	if (content.substring(i, i+M).compareTo(word) == 0)
		    		contador++;
		}
		
		return contador; 
	}

	private long hash(String word)
	{
		long ret = 277;
		for (int i = 0; i < word.length(); i++)
		{
			ret += word.charAt(i);
		}
		
		return ret;
	}

}
