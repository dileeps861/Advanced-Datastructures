import java.util.ArrayList;
import java.util.List;

/**
 * <b>Leetcode - 68. Text Justification</b><br />
 *
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
 * characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified and no extra space is inserted between words.
 *
 * Note:
 *<ul>
 * <li>A word is defined as a character sequence consisting of non-space characters only.</li>
 * <li>Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.</li>
 * <li>The input array words contains at least one word.</li>
 * </ul>
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16<br />
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;

        List<String> lst = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        while(i < words.length){
            String s1 = sb.toString().trim();
            if ((sb.length() != 0 || words[i].length() > maxWidth) && ((s1.length() + words[i].length()) >= maxWidth))
            {

                String[] str = s1.split(" ");
                int ex = maxWidth - s1.length();
                int extra = (str.length - 1) > 0 ? (ex / (str.length - 1)) : 0;
                ex = ex - (extra * (str.length - 1));

                sb = new StringBuilder();
                for (int l = 0; l < str.length - 1; l++) {
                    sb.append(str[l]);
                    sb.append(" ");
                    int k = 0;
                    while (k < extra) {
                        sb.append(" ");
                        k++;
                    }
                    if (ex > 0) {
                        sb.append(" ");
                        ex--;
                    }
                }

                sb.append(str[str.length - 1]);
                int k = 0;
                while (k < ex) {
                    sb.append(" ");
                    k++;
                }
                lst.add(sb.toString());
                sb = new StringBuilder();

            }
            sb.append(words[i]);
            sb.append(" ");
            i++;
        }
        int extra = maxWidth - sb.length();
        manageLastLine(sb, extra);
        if(sb.length() > maxWidth){
            extra = sb.length() - maxWidth;
            sb.replace(sb.length() - extra , (sb.length()), "");
        }
        lst.add(sb.toString());
        return lst;
    }

    private StringBuilder manageLastLine(StringBuilder sb, int extra){

        int k = 0;
        while(k < extra){
            sb.append(" ");
            k++;
        }
        return sb;
    }
    public static void main(String[] args) {
        TextJustification textJustification =  new TextJustification();
        System.out.println(
                textJustification.fullJustify(
                new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16)
        );
    }
}