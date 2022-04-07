package Components;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoresComparator implements Comparator<String> {

    Pattern p1 = Pattern.compile("[0-9]+  ");
    Pattern p2 = Pattern.compile("[0-9]+");
    @Override
    public int compare(String o1, String o2) {
        Matcher m1 = p1.matcher(o1);
        Matcher m2 = p1.matcher(o2);
        if(m1.find() && m2.find()){
            Matcher m3 = p2.matcher(m1.group(0));
            Matcher m4 = p2.matcher(m2.group(0));
            if(m3.find() && m4.find()) {
                return Integer.parseInt(m4.group(0)) - Integer.parseInt(m3.group(0));
            }
        }
        return 0;
    }
}