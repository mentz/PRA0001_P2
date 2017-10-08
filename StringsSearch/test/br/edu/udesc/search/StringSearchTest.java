/**
 * 
 */
package br.edu.udesc.search;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Testa os métodos para verificar seu funcionamento.
 * @author udesc
 */
public class StringSearchTest {
	private String[] sources = new String[3];
	private String[] content = new String[3];
	private String[][] pattern = new String[3][7];
	private long[][] expected = new long[3][7];

	@Before
	public void setup()
	{
		sources[0] = "resource/lipsum.txt";
		sources[1] = "resource/LettersOfLincoln.txt";
		sources[2] = "resource/aaaaaaaabbbbbbbb.txt";
		
		File fi;
		
		for(int i = 0; i < sources.length; i++)
		{
			fi = new File(sources[i]);

			try(FileReader     fr = new FileReader(fi);
				BufferedReader br = new BufferedReader(fr) ) 
			{
				String sCurrentLine;
				StringBuilder builder = new StringBuilder();
				while ((sCurrentLine = br.readLine()) != null) {
					builder.append(sCurrentLine);
				}
				content[i] = builder.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(new SearchByNaiveStrategy());
		
		this.pattern[0][0] = "lorem";
		this.pattern[0][1] = "Lorem";
		this.pattern[0][2] = "Lorem ipsum dolor sit amet";
		this.pattern[0][3] = "O joãozinho é FEIOSO";
		this.pattern[0][4] = "naocasa";
		this.pattern[0][5] = ".";
		this.pattern[0][6] = "Pellentesque ut purus pretium, placerat lacus non, venenatis odio. Sed nisl sem, congue in dolor sit amet, tincidunt sodales ex. Mauris cursus urna vel purus iaculis, at gravida lacus convallis. Quisque eget elit vehicula, feugiat tortor id, sagittis purus. Curabitur tincidunt venenatis enim sit amet interdum. Aenean at tempus quam. Morbi scelerisque, mauris quis faucibus dapibus, felis libero facilisis magna, eget rhoncus eros tellus a lectus. Mauris finibus, risus vel volutpat ornare, lectus metus condimentum est, vel scelerisque nunc velit id magna. Proin sed consequat diam. Donec rhoncus, orci vel malesuada volutpat, purus urna placerat enim, eu egestas ex erat sit amet tellus. In ullamcorper quam sollicitudin, fermentum ante a, vehicula leo. Suspendisse tortor velit, tincidunt non placerat quis, suscipit in purus. Donec quis congue velit, ut rhoncus loryeem.";

		this.pattern[1][0] = "Lincoln";
		this.pattern[1][1] = "But his standing before posterity will";
		this.pattern[1][2] = "letter";
		this.pattern[1][3] = "dear";
		this.pattern[1][4] = "naocasaemnenhumtexto";
		this.pattern[1][5] = ".";
		this.pattern[1][6] = "Pellentesque ut purus pretium, placerat lacus non, venenatis odio. Sed nisl sem, congue in dolor sit amet, tincidunt sodales ex. Mauris cursus urna vel purus iaculis, at gravida lacus convallis. Quisque eget elit vehicula, feugiat tortor id, sagittis purus. Curabitur tincidunt venenatis enim sit amet interdum. Aenean at tempus quam. Morbi scelerisque, mauris quis faucibus dapibus, felis libero facilisis magna, eget rhoncus eros tellus a lectus. Mauris finibus, risus vel volutpat ornare, lectus metus condimentum est, vel scelerisque nunc velit id magna. Proin sed consequat diam. Donec rhoncus, orci vel malesuada volutpat, purus urna placerat enim, eu egestas ex erat sit amet tellus. In ullamcorper quam sollicitudin, fermentum ante a, vehicula leo. Suspendisse tortor velit, tincidunt non placerat quis, suscipit in purus. Donec quis congue velit, ut rhoncus lorem.";
		
		this.pattern[2][0] = "a";
		this.pattern[2][1] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		this.pattern[2][2] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		this.pattern[2][3] = "ab";
		this.pattern[2][4] = "b";
		this.pattern[2][5] = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbba";
		this.pattern[2][6] = "abababababababababababababab";
		
		for(int i = 0, j; i < pattern.length; i++)
			for(j = 0; j < pattern[i].length; j++)
				this.expected[i][j] = ctx.search(content[i], pattern[i][j]);

	}
	
	public void testSuite(ISearchStrategy strategy)
	{
		SearchContext ctx = new SearchContext();
		ctx.setSearchStrategy(strategy);
		
		long[][] found = new long[content.length][7];
		
		for(int i = 0, j; i < content.length; i++)
		{
			for(j = 0; j < pattern[i].length; j++)
			{
				found[i][j] = ctx.search(content[i], pattern[i][j]);
				assertEquals(expected[i][j], found[i][j]);
			}
		}
	}
	
	@Test
	public void testNaive() {
		testSuite(new SearchByNaiveStrategy());
	}

	@Test
	public void testKMP() {
		testSuite(new SearchByKMPStrategy());
	}

	@Test
	public void testBoyerMoore() {
		testSuite(new SearchByBoyerMooreStrategy());
	}

	@Test
	public void testRabinKarp() {
		testSuite(new SearchByRabinKarpStrategy());
	}

	@Test
	public void testAhoCorasick() {
		testSuite(new SearchByAhoCorasickStrategy());
	}
}
