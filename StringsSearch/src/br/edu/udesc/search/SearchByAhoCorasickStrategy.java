/**
 * 
 */
package br.edu.udesc.search;

/**
 * @author Leo_e_Mentz
 * Método de busca com algoritmo Aho-Corasick (trie)
 */
public class SearchByAhoCorasickStrategy implements ISearchStrategy {
	private final int ALPHABETIC_SIZE = 256;
	node root = null;
    
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
		init(root);
		insertWord(content);
        return quantPrefix(pattern);
	}
	
	private class node {
		boolean isEnd;
		int prefixCount;
		node[] child = new node[ALPHABETIC_SIZE];
	}

	void init(node nd){
		nd = new node();
		nd.isEnd = false;
		nd.prefixCount = 0;
		for(int i = 0; i < ALPHABETIC_SIZE; i++){
			nd.child[i] = null;
		}
	}

	void insertWord(String word){
		node current = root;
		current.prefixCount++;
		for(int i = 0; i < word.length(); i++){
			int letter = (int)word.charAt(i) - (int)'a';
			if(current.child[letter] == null){
				init(current.child[letter]);
			}
			current.child[letter].prefixCount++;
			current = current.child[letter];
		}
		current.isEnd = true;
	}

//	public boolean searchWord(String word){
//		node current = root;
//		for(int i = 0; i < word.length(); i++){
//			int letter = word.charAt(i) - 'a';
//			if(current.child[letter] == null){
//				return false;
//			}		
//			current = current.child[letter];
//		}
//		return current.isEnd;
//	}

	int quantPrefix(String prefix){
		node current = root;
		for(int i = 0; i < prefix.length(); i++){
			int letter = prefix.charAt(i) - 'a';
			if(current.child[letter] == null){
				return 0;
			}		
			current = current.child[letter];
		}
		return current.prefixCount;
	}
}
