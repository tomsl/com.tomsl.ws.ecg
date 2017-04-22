/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomsl.ws.ecg.ecg;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
public class MainTest {

    private ArrayList<String> mail_lst = new ArrayList<>();

    private void testmal() {
        System.out.println("in MAIN - TEST");
        System.out.println("");

        //EcgList l = new EcgList();
        EcgCheck chk = EcgCheck.getInstance();

        //System.out.println("chk file : " + chk.getFilename());
        chk.setFilename("ecg-liste.hash");
        chk.setFilename("4243_testliste.hash");
        System.out.println("");

        this.showblocks();
//
        this.testLst();
//        System.out.println(chk.isInEcg("@home.lan"));
//        System.out.println(chk.isInEcg("@home.land"));

        System.out.println("");
        this.silenttest();
    }

    public void silenttest() {
        long start = System.currentTimeMillis();
        EcgCheck chk = EcgCheck.getInstance();
        System.out.println("Silent Test");

        System.out.print("Start ... ");
        int a = 0;
        int a_true = 0;
        int a_false = 0;

        int loopcount = 5000;
        for (int i = 0; i < loopcount; i++) {
            for (String s : this.mail_lst) {
                if (chk.isInEcg(s)) {
                    a_true++;
                } else {
                    a_false++;
                }
                a++;
            }
        }// outer loop N

        System.out.println("Silent Test ende " + a + "(" + a_true + "/" + a_false + ")");
        System.out.println(System.currentTimeMillis() - start + " ms");
        System.out.println("checks overall : " + a + " * " + chk.getEntryCount()
                + " * " + this.mail_lst.size()
                + " -> " + (a * chk.getEntryCount() * this.mail_lst.size()));
    }

    public void showblocks() {
        System.out.println("");
        EcgCheck chk = EcgCheck.getInstance();
        System.out.println(" Show Blocks");

    }

    public void testLst() {
        System.out.println("");
        EcgCheck chk = EcgCheck.getInstance();
        System.out.println(" Show TEstlist");

        mail_lst.forEach(m -> {
            System.out.println("[" + new String(chk.sha(m.getBytes())) + "]");
        });
    }


    public static void main(String[] args) {
        new MainTest().testmal();
    }

    {

        mail_lst.add("asdf@tomsl.com");
        mail_lst.add("karl.testinger@firma.at");
        mail_lst.add("max.musterman@home.at");
        mail_lst.add("@home.lan");
        mail_lst.add("@firma.lan");
        mail_lst.add("t.streimelweger@schrack-seconet.com");

    }
}
