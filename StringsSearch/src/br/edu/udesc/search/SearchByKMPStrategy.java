package br.edu.udesc.search;

public class SearchByKMPStrategy extends ASearchStrategy {

	public int searchFile(String content, String word) {
		int[] LPS = new int[word.length() + 1];
		pre(LPS, word);
		
		int i, j;
		int contador = 0;
		i = j = 0;
		int N = content.length(), M = word.length();
		while(i < N){
			if(word.charAt(j) == content.charAt(i)){
				i++;
				j++;
			}
			if(j == M){
				contador++;
				j = LPS[j - 1];
			} else if(i < N && word.charAt(j) != content.charAt(i)){
				if(j != 0){
					j = LPS[j - 1];
				} else {
					i++;
				}
			}
		}
		
		return contador;
	}
	
	void pre(int[] LPS, String word){
		int i = 1, j = 0;
		LPS[0] = 0;
		while(i < (int)word.length()){
			if(word.charAt(i) == word.charAt(j)){
				j++;
				LPS[i++] = j;
			} else {
				if(j != 0){
					j = LPS[j - 1];
				} else {
					LPS[i++] = 0;
				}
			}
		}
	}
}
