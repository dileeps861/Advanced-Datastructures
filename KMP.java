/**
 * KMP Algorithm for substring search.
 */
public class KMP {
    private final String  pattern;
    private final int[][]  dfa;

    /**
     * Constructor to initialize the DFA based KMP.
     * @param pattern the pattern string
     */
    public KMP(String pattern) {
        this.pattern = pattern;
        this.dfa = new int[256][pattern.length()];
        constructDFA(dfa, pattern);
    }

    /**
     * Construct the dfa of given pattern.
     * @param dfa the dfa array
     * @param pattern the pattern string
     */
    private void constructDFA( int[][] dfa, String pattern){
        dfa[pattern.charAt(0)][0] = 1;

        for (int i = 1, x=0;i < pattern.length(); i++ ){
            for (int j = 0; j < dfa.length; j++) {
                dfa[j][i] = dfa[j][x];
            }
            dfa[pattern.charAt(i)][i] = i+1;
            x = dfa[pattern.charAt(i)][x];
        }
    }

    /**
     * Text in which search for the pattern.
     * @param text the text
     * @return the index searched.
     */
    public int search(String text){
        int i =0; int j =0;
        for (; i < text.length() && j < pattern.length(); i++) {
            j = dfa[text.charAt(i)][j];
        }
        if(j == pattern.length()) return i - pattern.length();
        else return text.length();
    }
    public static void main(String[] args) {
        String pat = "aba";
        String txt = "babcabacd";
        KMP kmp = new KMP(pat);
        System.out.println("text: " + txt);
        int offset = kmp.search(txt);
        System.out.print("pattern index: "+ offset);
    }
}
