/**
 * 
 */
package br.edu.udesc.search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo_e_Mentz
 * Método de busca com algoritmo Aho-Corasick (trie)
 */
public class SearchByAhoCorasickStrategy extends ASearchStrategy {
	private node root;
    
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
		Map<Integer, node> child;
		int depth = -1;
	}

	/**
	 * Inicializa um nó da árvore Trie.
	 * @return Um nó limpo com seus filhos.
	 */
	private node init(){
		node nd = new node();
		nd.isEnd = false;
		nd.depth = 0;
		nd.child = new HashMap<Integer, node>();
		
		return nd;
	}

	/**
	 * Faz a criação da árvore Trie com somente o padrão de pesquisa.
	 * @param pattern Padrão para pesquisa.
	 */
	void inserePattern(String pattern){
		int d = 0;
		node current = root;
		for(int i = 0; i < pattern.length(); i++){
			int letter = (int)pattern.charAt(i);
			if(current.child.containsKey(letter) == false){
				current.child.put(letter, init());
			}
			current = current.child.get(letter);
			current.depth = d;
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
			if (current.child.containsKey(letter))
			{
				current = current.child.get(letter);
				if (current.isEnd)
				{
					count++;
					i -= current.depth; // Cobre caso de "ll" em "lllll".
				}
			}
			else if (!current.child.containsKey(letter))
			{
				i -= current.depth - 1; // Cobre caso de "llb" em "lllllb".
				current = root;
			}
		}
		return count;
	}
}
