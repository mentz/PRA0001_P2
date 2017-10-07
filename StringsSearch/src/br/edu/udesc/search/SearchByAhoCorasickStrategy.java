/**
 * 
 */
package br.edu.udesc.search;

/**
 * @author Leo_e_Mentz
 * Método de busca com algoritmo Aho-Corasick (trie)
 */
public class SearchByAhoCorasickStrategy implements ISearchStrategy {
	private final int ALPHABETIC_SIZE = 65536;
	private node root;
	private int psize;
	private char pfirst;
    
	/**
	 * Pesquisa o número de ocorrências de um padrão em um conteúdo usando Aho-Corasick
	 * (trie, na verdade).
	 * 
	 * @author Leo_e_Mentz
     * @param content O conteúdo onde vai pesquisar o pattern
     * @param pattern O padrão a pesquisar em content
     * @return O número de ocorrências de pattern em content
     */
	public int searchFile(String content, String pattern) {
		root = init();
		inserePattern(pattern);
        return quantificaOcorrencias(content);
	}
	
	private class node {
		boolean isEnd;
		node[] child = new node[ALPHABETIC_SIZE];
	}

	/**
	 * Inicializa um nó da árvore Trie.
	 * @return Um nó limpo com seus filhos.
	 */
	private node init(){
		node nd = new node();
		nd.isEnd = false;
		for(int i = 0; i < ALPHABETIC_SIZE; i++){
			nd.child[i] = null;
		}
		return nd;
	}

	/**
	 * Faz a criação da árvore Trie com o padrão de pesquisa.
	 * @param pattern Padrão para pesquisa.
	 */
	void inserePattern(String pattern){
		psize = pattern.length();
		pfirst = pattern.charAt(0);
		node current = root;
		for(int i = 0; i < pattern.length(); i++){
			int letter = (int)pattern.charAt(i);
			if(current.child[letter] == null){
				current.child[letter] = init();
			}
			current = current.child[letter];
		}
		current.isEnd = true;
	}

	/**
	 * Faz a busca percorrendo a Trie e o content.
	 * @param content O texto onde irá buscar o pattern.
	 * @return O número de ocorrências.
	 */
	int quantificaOcorrencias(String content){
		node current = root;
		int count = 0;
		for (int i = 0; i < content.length(); i++){
			int letter = (int)content.charAt(i);
			if (current.child[letter] != null)
			{
				current = current.child[letter];
				if (current.isEnd)
				{
					count++;
					if (letter == pfirst)
					{
						i -= psize; // Cobre caso de "ll" em "lllll".
					}
				}
			}
			else if (current.child[letter] == null)
				current = root;
		}
		return count;
	}
}
