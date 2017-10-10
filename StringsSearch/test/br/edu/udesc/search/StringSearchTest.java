/**
 * 
 */
package br.edu.udesc.search;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	Logger log;

	@Before
	public void setup()
	{
		sources[0] = "resource/lipsum.txt";
		sources[1] = "resource/aaaaaaaabbbbbbbb.txt";
		sources[2] = "resource/WarAndPeace.txt";
		
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

		this.pattern[1][0] = "a";
		this.pattern[1][1] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		this.pattern[1][2] = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		this.pattern[1][3] = "ab";
		this.pattern[1][4] = "b";
		this.pattern[1][5] = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbba";
		this.pattern[1][6] = "abababababababababababababab";
		
		this.pattern[2][0] = "hello";
		this.pattern[2][1] = "a";
		this.pattern[2][2] = "you";
		this.pattern[2][3] = "large gathering";
		this.pattern[2][4] = "Pávlovna";
		this.pattern[2][5] = ".";
		this.pattern[2][6] = "Man’s free will differs from every other force in that man is directly\n" + 
				"conscious of it, but in the eyes of reason it in no way differs from\n" + 
				"any other force. The forces of gravitation, electricity, or chemical\n" + 
				"affinity are only distinguished from one another in that they are\n" + 
				"differently defined by reason. Just so the force of man’s free will\n" + 
				"is distinguished by reason from the other forces of nature only by the\n" + 
				"definition reason gives it. Freedom, apart from necessity, that is,\n" + 
				"apart from the laws of reason that define it, differs in no way from\n" + 
				"gravitation, or heat, or the force that makes things grow; for reason,\n" + 
				"it is only a momentary undefinable sensation of life.\n" + 
				"\n" + 
				"And as the undefinable essence of the force moving the heavenly bodies,\n" + 
				"the undefinable essence of the forces of heat and electricity, or\n" + 
				"of chemical affinity, or of the vital force, forms the content of\n" + 
				"astronomy, physics, chemistry, botany, zoology, and so on, just in the\n" + 
				"same way does the force of free will form the content of history.\n" + 
				"But just as the subject of every science is the manifestation of this\n" + 
				"unknown essence of life while that essence itself can only be the\n" + 
				"subject of metaphysics, even the manifestation of the force of free will\n" + 
				"in human beings in space, in time, and in dependence on cause forms\n" + 
				"the subject of history, while free will itself is the subject of\n" + 
				"metaphysics.\n" + 
				"\n" + 
				"In the experimental sciences what we know we call the laws of\n" + 
				"inevitability, what is unknown to us we call vital force. Vital force is\n" + 
				"only an expression for the unknown remainder over and above what we know\n" + 
				"of the essence of life.\n" + 
				"\n" + 
				"So also in history what is known to us we call laws of inevitability,\n" + 
				"what is unknown we call free will. Free will is for history only an\n" + 
				"expression for the unknown remainder of what we know about the laws of\n" + 
				"human life.\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"CHAPTER XI\n" + 
				"\n" + 
				"History examines the manifestations of man’s free will in connection\n" + 
				"with the external world in time and in dependence on cause, that is, it\n" + 
				"defines this freedom by the laws of reason, and so history is a science\n" + 
				"only in so far as this free will is defined by those laws.\n" + 
				"\n" + 
				"The recognition of man’s free will as something capable of influencing\n" + 
				"historical events, that is, as not subject to laws, is the same for\n" + 
				"history as the recognition of a free force moving the heavenly bodies\n" + 
				"would be for astronomy.\n" + 
				"\n" + 
				"That assumption would destroy the possibility of the existence of laws,\n" + 
				"that is, of any science whatever. If there is even a single body\n" + 
				"moving freely, then the laws of Kepler and Newton are negatived and no\n" + 
				"conception of the movement of the heavenly bodies any longer exists. If\n" + 
				"any single action is due to free will, then not a single historical law\n" + 
				"can exist, nor any conception of historical events.\n" + 
				"\n" + 
				"For history, lines exist of the movement of human wills, one end\n" + 
				"of which is hidden in the unknown but at the other end of which a\n" + 
				"consciousness of man’s will in the present moves in space, time, and\n" + 
				"dependence on cause.\n" + 
				"\n" + 
				"The more this field of motion spreads out before our eyes, the more\n" + 
				"evident are the laws of that movement. To discover and define those laws\n" + 
				"is the problem of history.\n" + 
				"\n" + 
				"From the standpoint from which the science of history now regards its\n" + 
				"subject on the path it now follows, seeking the causes of events in\n" + 
				"man’s free will, a scientific enunciation of those laws is impossible,\n" + 
				"for however man’s free will may be restricted, as soon as we recognize\n" + 
				"it as a force not subject to law, the existence of law becomes\n" + 
				"impossible.\n" + 
				"\n" + 
				"Only by reducing this element of free will to the infinitesimal, that\n" + 
				"is, by regarding it as an infinitely small quantity, can we convince\n" + 
				"ourselves of the absolute inaccessibility of the causes, and then\n" + 
				"instead of seeking causes, history will take the discovery of laws as\n" + 
				"its problem.\n" + 
				"\n" + 
				"The search for these laws has long been begun and the new methods of\n" + 
				"thought which history must adopt are being worked out simultaneously\n" + 
				"with the self-destruction toward which—ever dissecting and dissecting\n" + 
				"the causes of phenomena—the old method of history is moving.\n" + 
				"\n" + 
				"All human sciences have traveled along that path. Arriving at\n" + 
				"infinitesimals, mathematics, the most exact of sciences, abandons the\n" + 
				"process of analysis and enters on the new process of the integration\n" + 
				"of unknown, infinitely small, quantities. Abandoning the conception\n" + 
				"of cause, mathematics seeks law, that is, the property common to all\n" + 
				"unknown, infinitely small, elements.\n" + 
				"\n" + 
				"In another form but along the same path of reflection the other sciences\n" + 
				"have proceeded. When Newton enunciated the law of gravity he did not say\n" + 
				"that the sun or the earth had a property of attraction; he said that all\n" + 
				"bodies from the largest to the smallest have the property of attracting\n" + 
				"one another, that is, leaving aside the question of the cause of the\n" + 
				"movement of the bodies, he expressed the property common to all bodies\n" + 
				"from the infinitely large to the infinitely small. The same is done by\n" + 
				"the natural sciences: leaving aside the question of cause, they seek for\n" + 
				"laws. History stands on the same path. And if history has for its object\n" + 
				"the study of the movement of the nations and of humanity and not the\n" + 
				"narration of episodes in the lives of individuals, it too, setting\n" + 
				"aside the conception of cause, should seek the laws common to all the\n" + 
				"inseparably interconnected infinitesimal elements of free will.\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"\n" + 
				"CHAPTER XII\n" + 
				"\n" + 
				"From the time the law of Copernicus was discovered and proved, the mere\n" + 
				"recognition of the fact that it was not the sun but the earth that moves\n" + 
				"sufficed to destroy the whole cosmography of the ancients. By disproving\n" + 
				"that law it might have been possible to retain the old conception of\n" + 
				"the movements of the bodies, but without disproving it, it would seem\n" + 
				"impossible to continue studying the Ptolemaic worlds. But even after\n" + 
				"the discovery of the law of Copernicus the Ptolemaic worlds were still\n" + 
				"studied for a long time.\n" + 
				"\n" + 
				"From the time the first person said and proved that the number of births\n" + 
				"or of crimes is subject to mathematical laws, and that this or that\n" + 
				"mode of government is determined by certain geographical and economic\n" + 
				"conditions, and that certain relations of population to soil produce\n" + 
				"migrations of peoples, the foundations on which history had been built\n" + 
				"were destroyed in their essence.\n" + 
				"\n" + 
				"By refuting these new laws the former view of history might have been\n" + 
				"retained; but without refuting them it would seem impossible to continue\n" + 
				"studying historic events as the results of man’s free will. For if a\n" + 
				"certain mode of government was established or certain migrations\n" + 
				"of peoples took place in consequence of such and such geographic,\n" + 
				"ethnographic, or economic conditions, then the free will of those\n" + 
				"individuals who appear to us to have established that mode of government\n" + 
				"or occasioned the migrations can no longer be regarded as the cause.\n" + 
				"\n" + 
				"And yet the former history continues to be studied side by side with the\n" + 
				"laws of statistics, geography, political economy, comparative philology,\n" + 
				"and geology, which directly contradict its assumptions.\n" + 
				"\n" + 
				"The struggle between the old views and the new was long and stubbornly\n" + 
				"fought out in physical philosophy. Theology stood on guard for the\n" + 
				"old views and accused the new of violating revelation. But when truth\n" + 
				"conquered, theology established itself just as firmly on the new\n" + 
				"foundation.\n" + 
				"\n" + 
				"Just as prolonged and stubborn is the struggle now proceeding between\n" + 
				"the old and the new conception of history, and theology in the same way\n" + 
				"stands on guard for the old view, and accuses the new view of subverting\n" + 
				"revelation.\n" + 
				"\n" + 
				"In the one case as in the other, on both sides the struggle provokes\n" + 
				"passion and stifles truth. On the one hand there is fear and regret for\n" + 
				"the loss of the whole edifice constructed through the ages, on the other\n" + 
				"is the passion for destruction.\n" + 
				"\n" + 
				"To the men who fought against the rising truths of physical philosophy,\n" + 
				"it seemed that if they admitted that truth it would destroy faith in\n" + 
				"God, in the creation of the firmament, and in the miracle of Joshua the\n" + 
				"son of Nun. To the defenders of the laws of Copernicus and Newton, to\n" + 
				"Voltaire for example, it seemed that the laws of astronomy destroyed\n" + 
				"religion, and he utilized the law of gravitation as a weapon against\n" + 
				"religion.\n" + 
				"\n" + 
				"Just so it now seems as if we have only to admit the law of\n" + 
				"inevitability, to destroy the conception of the soul, of good and evil,\n" + 
				"and all the institutions of state and church that have been built up on\n" + 
				"those conceptions.\n" + 
				"\n" + 
				"So too, like Voltaire in his time, uninvited defenders of the law of\n" + 
				"inevitability today use that law as a weapon against religion, though\n" + 
				"the law of inevitability in history, like the law of Copernicus in\n" + 
				"astronomy, far from destroying, even strengthens the foundation on which\n" + 
				"the institutions of state and church are erected.\n" + 
				"\n" + 
				"As in the question of astronomy then, so in the question of history\n" + 
				"now, the whole difference of opinion is based on the recognition or\n" + 
				"nonrecognition of something absolute, serving as the measure of visible\n" + 
				"phenomena. In astronomy it was the immovability of the earth, in history\n" + 
				"it is the independence of personality—free will.\n" + 
				"\n" + 
				"As with astronomy the difficulty of recognizing the motion of the earth\n" + 
				"lay in abandoning the immediate sensation of the earth’s fixity and of\n" + 
				"the motion of the planets, so in history the difficulty of recognizing\n" + 
				"the subjection of personality to the laws of space, time, and cause\n" + 
				"lies in renouncing the direct feeling of the independence of one’s own\n" + 
				"personality. But as in astronomy the new view said: “It is true that we\n" + 
				"do not feel the movement of the earth, but by admitting its immobility\n" + 
				"we arrive at absurdity, while by admitting its motion (which we do not\n" + 
				"feel) we arrive at laws,” so also in history the new view says: “It is\n" + 
				"true that we are not conscious of our dependence, but by admitting our\n" + 
				"free will we arrive at absurdity, while by admitting our dependence on\n" + 
				"the external world, on time, and on cause, we arrive at laws.”\n" + 
				"\n" + 
				"In the first case it was necessary to renounce the consciousness of an\n" + 
				"unreal immobility in space and to recognize a motion we did not feel;\n" + 
				"in the present case it is similarly necessary to renounce a freedom\n" + 
				"that does not exist, and to recognize a dependence of which we are not\n" + 
				"conscious.";
		
		for(int i = 0, j; i < pattern.length; i++)
			for(j = 0; j < pattern[i].length; j++)
				this.expected[i][j] = ctx.search(content[i], pattern[i][j]);
		

		log = Logger.getLogger(StringSearchTest.class.getName());
		log.setLevel(Level.FINE);
		
		Handler fileHandler;
		
		try {
			fileHandler = new FileHandler("resource/logger.log", 20000000, 1);
			log.addHandler(fileHandler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				long t0 = System.currentTimeMillis();
				found[i][j] = ctx.search(content[i], pattern[i][j]);
				long t1 = System.currentTimeMillis();
				log.log(Level.FINE, "Strategy: " + strategy.getClass().getSimpleName() + " - Text: " + i
						+ " - Test: " + j + " - Time: " + (t1-t0) + " - Found: " + found[i][j] + "\n");
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
