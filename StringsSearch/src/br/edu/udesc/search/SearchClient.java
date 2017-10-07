package br.edu.udesc.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchClient {
	
	private static final String WORD = ".";
	private static String content;

	static {
		File   fi =  new File("resource/lipsum.txt");

		try(FileReader     fr = new FileReader(fi);
			BufferedReader br = new BufferedReader(fr) ) 
		{
			String sCurrentLine;
			StringBuilder builder = new StringBuilder();
			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
			content = builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static final void showMsg(String algorithm, int found) {
		System.out.println("Usando o algoritmo [" +algorithm + "] procurando por [" + WORD + "] encontrou " + found + " ocorrencias");
	}
	
	private static final void usarBoyerMoore() {
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(new SearchByBoyerMooreStrategy());
		int found = ctx.search(content, WORD);
		showMsg("BoyerMoore", found);
	}
	
	private static final void usarNaive() {
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(new SearchByNaiveStrategy());
		int found = ctx.search(content, WORD);
		showMsg("Naive", found);
	}
	
	private static final void usarKMP() {
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(new SearchByKMPStrategy());
		int found = ctx.search(content, WORD);
		showMsg("KMP", found);
	}
	
	private static final void usarRabinKarp() {
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(new SearchByRabinKarpStrategy());
		int found = ctx.search(content, WORD);
		showMsg("RabinKarp", found);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		usarBoyerMoore();
		usarNaive();
		usarKMP();
		usarRabinKarp();
	}
}
