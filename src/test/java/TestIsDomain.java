
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
public class TestIsDomain {
    private static final ArrayList<String> TT = new ArrayList<>();

    static {
        TT.add(".com");
        TT.add("asdf.asdf");
        TT.add("domain.tld");
        TT.add("d.t");
        TT.add("d.t.");
        TT.add(".d.t");
        TT.add(".d.t");
        TT.add("dd.td");
        TT.add("dd.t");
        TT.add("much_to_long_sdfasdfasdfasasdfasdfasdfasdfasdfadfasdfasdfasdfaasdfasdfasdfasdfasdfasdf.tf");
        TT.add("ddsasdfasdfasdfasdfasdfasdfasdfasdfaasdfasdfasdfasdfasdfasdf.tf");
        TT.add("dd.asia");
        TT.add("dd3.asia");
        TT.add("dd.asiad");
        TT.add("d-d.a");
        TT.add("d-d.at");
        TT.add("subdomain.domain.tld");
        TT.add("subdomain.domain.tlda");
        TT.add("subdomain.domain.tldaa");
        TT.add("subdomain.dom ain.tldaa");
        TT.add("ab.cd");
        TT.add("abc.de");

    }

    public boolean testMayBeDomain(String domain) {
        boolean ret = false;
        domain = domain.toLowerCase();
        //String pattern = "^([a-z0-9_]{2-50}).([a-z0-9_]{2,5})$";
        String pattern = "^[a-zA-Z0-9][a-zA-Z0-9-\\._]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(domain);
        if (m.find()) {
            ret = true;
        }

        return ret;
    }

    public static void main(String[] args) {

        TestIsDomain a = new TestIsDomain();
        for (String s : TT) {
            System.out.print(a.testMayBeDomain(s) ? "TRUE " : "FALSE");
            System.out.print(" : ");
            System.out.print(s);
            System.out.println("");
        }

    }

}
