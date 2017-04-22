/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsl.ws.ecg;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
public class ECG_WS_Application extends Application<Ecg_Configuration> {

    public static void main(String[] args) throws Exception {
        new ECG_WS_Application().run(args);
    }

    @Override
    public void run(Ecg_Configuration conf, Environment env) throws Exception {
        final EcgResource ecg_res = new EcgResource();
        final EcgHealthCheck_01 ecg_chk01 = new EcgHealthCheck_01();
        final Sha1MakerResource sha_res = new Sha1MakerResource();

        env.healthChecks().register("chk_01", ecg_chk01);
        env.jersey().register(ecg_res);
        env.jersey().register(sha_res);
    }
}
