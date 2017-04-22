/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsl.ws.ecg;

import com.codahale.metrics.annotation.Timed;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
@Path("/ecg/")
public class EcgResource {

    @GET
    @Timed
    @Path("{mail}")
    public String checkMailOrDomain(@PathParam("mail") Optional<String> mail) {
        String ret = "huhu";

        String m = mail.get().trim();

        if (StringUtils.isEmpty(m)) {
            ret = " Mail is empty ...";
        } else if (this.isMail(m)) {
            ret = " looks like a mail ";
        } else if (this.isDomain(m)) {
            ret = " could be a domain ";
        } else {
            ret = " does not look like a mail or domain ... check anyway";
        }

        EcgCheck chk = EcgCheck.getInstance();
        ret += "\n allowed: " + chk.isMailAllowed(m);

        return ret;
    }

    @GET
    @Path("/ismail/{mail}")
    public String isMail2(@PathParam("mail") String mail) {
        return this.isMail(mail) + "";
    }

    @GET
    @Path("/isdomain/{domain}")
    public String isDomain2(@PathParam("domain") String domain) {
        return this.isDomain(domain) + "";
    }

    /**
     * Returns true if the given 'domain' String match to regex.
     *
     * @param domain String
     * @return true if given String could be a domain
     */
    private boolean isDomain(String domain) {
        boolean ret = false;
        String in = domain.toLowerCase();
        //String pattern = "^([a-z0-9_]{2-50}).([a-z0-9_]{2,5})$";
        String pattern = "^[a-zA-Z0-9][a-zA-Z0-9-\\._]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(in);
        if (m.find()) {
            ret = true;
        }

        return ret;
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
