/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsl.ws.ecg;

import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
@Path("/sha1/{value}")
@Produces(MediaType.APPLICATION_JSON)
public class Sha1MakerResource {

    @GET
    @Timed
    public String makeSha1(@PathParam("value") String in) {
        String ret = "";
        String sha1 = DigestUtils.sha1Hex(in);
        ret += "{";
        ret += "\n\t\"value\" : \"" + in + "\"";
        ret += "\n\t\"sha1\" : \"" + sha1 + "\"";
        ret += "\n}";
        return ret;
    }
}
