
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
public class TestIsMail {

    public final static ArrayList<String> TT = new ArrayList<>();

    static {
        TT.add("tomsl@tomsl.com");
        TT.add("TOMSL@tomsl.com");
        TT.add("tomsl@TOMSL.com");
        TT.add("tomsl@TOMSL.acom");
        TT.add("tomsl@TOMSL.adcom");
        TT.add("tomsl@TOMSL.adddcom");
        TT.add("a@domain-asdf.asdf.adddcom");
        TT.add("ab@domain-asdf.asdf.adddcom");
        TT.add("-@domain-asdf.asdf.adddcom");
        TT.add("a_@domain-asdf.asdf.adddcom");
        TT.add("a.b@domain-asdfasdf.addd");
        TT.add("a_b@domain-asdfasdf.addd");
        TT.add("a-b@domain-asdfasdf.addd");
        TT.add(":@domain-asdfasdf.addd");
        TT.add(".@domain-asdfasdf.addd");
        TT.add(".@domain-asdfasdf.addd");
    }

    public static void main(String[] args) {
        TestIsMail a = new TestIsMail();
        for (String s : TT) {
            System.out.print(a.isMail(s) ? "TRUE  : " : "FALSE : ");
            System.out.print(s);
            System.out.println("");
        }
    }

    private boolean isMail(String mail) {
        boolean ret = false;
        String in = mail.trim();
        //String pattern = "^[A-Z0-9]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(in);
        if (m.find()) {
            ret = true;
        }
        return ret;
    }
}
