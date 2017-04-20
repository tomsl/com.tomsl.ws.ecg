/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsl.ws.ecg;

import com.codahale.metrics.health.HealthCheck;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
public class EcgHealthCheck_01 extends HealthCheck {

    public EcgHealthCheck_01() {
        // not needed yet.
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
