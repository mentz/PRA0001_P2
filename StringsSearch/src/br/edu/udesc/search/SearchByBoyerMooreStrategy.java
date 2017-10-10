package br.edu.udesc.search;

public class SearchByBoyerMooreStrategy extends ASearchStrategy {
	/**
	 * Pesquisa o número de ocorrências de um padrão em um conteúdo usando BoyerMoore.
	 * 
	 * @author Leo_e_Mentz
     * @param content O conteúdo onde vai pesquisar o pattern
     * @param pattern O padrão a pesquisar em content
     * @return O número de ocorrências de pattern em content
     */
    public int searchFile(String content, String pattern) {
    	int count = 0;
    	
        if (pattern.length() == 0) {
        	count = 0;
            return count;
        }
        int charTable[] = makeCharTable(pattern);
        int offsetTable[] = makeOffsetTable(pattern);
        for (int i = pattern.length() - 1, j; i < content.length();) {
            for (j = pattern.length() - 1; pattern.charAt(j) == content.charAt(i); --i, --j) {
                if (j == 0) {
                    count++;
                    break;
                }
            }

            i += Math.max(offsetTable[pattern.length() - 1 - j], charTable[content.charAt(i)]);
        }
        return count;
    }

    /**
     * Makes the jump table based on the mismatched character information.
     */
    private static int[] makeCharTable(String needle) {
        final int ALPHABET_SIZE = 65536; // UTF-8
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i) {
            table[i] = needle.length();
        }
        for (int i = 0; i < needle.length() - 1; ++i) {
            table[needle.charAt(i)] = needle.length() - 1 - i;
        }
        return table;
    }

    /**
     * Makes the jump table based on the scan offset which mismatch occurs.
     */
    private static int[] makeOffsetTable(String needle) {
        int[] table = new int[needle.length()];
        int lastPrefixPosition = needle.length();
        for (int i = needle.length() - 1; i >= 0; --i) {
            if (isPrefix(needle, i + 1)) {
                lastPrefixPosition = i + 1;
            }
            table[needle.length() - 1 - i] = lastPrefixPosition - i + needle.length() - 1;
        }
        for (int i = 0; i < needle.length() - 1; ++i) {
            int slen = suffixLength(needle, i);
            table[slen] = needle.length() - 1 - i + slen;
        }
        return table;
    }

    /**
     * Is needle[p:end] a prefix of needle?
     */
    private static boolean isPrefix(String needle, int p) {
        for (int i = p, j = 0; i < needle.length(); ++i, ++j) {
            if (needle.charAt(i) != needle.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the maximum length of the substring ends at p and is a suffix.
     */
    private static int suffixLength(String needle, int p) {
        int len = 0;
        for (int i = p, j = needle.length() - 1;
                 i >= 0 && needle.charAt(i) == needle.charAt(j); --i, --j) {
            len += 1;
        }
        return len;
    }
}
