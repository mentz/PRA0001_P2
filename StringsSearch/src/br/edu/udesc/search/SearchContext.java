package br.edu.udesc.search;

public class SearchContext {

	private ISearchStrategy strategy;
	// define a strategy
	public void setSearchStrategy(ISearchStrategy strategy) {
	    this.strategy = strategy;
	}
	  
	  // use a strategy
	public int search(String content, String word) {
		return strategy.searchFile(content, word);
	}
}