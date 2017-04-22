/*
 * EcgCheck class
 * 
 * Also could be used 'stand alone' - copy to your project
 * @author tomsl
 * @since 2017.04.22
 */
package com.tomsl.ws.ecg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author tomsl <tomsl@tomsl.com> / <t.streimelweger@schrack-seconet.com>
 */
public class EcgCheck {

    /* SINGLETON */
    private static String filename = "4243_testliste.hash";
    private static EcgCheck instance;
    private MessageDigest md;

    // for 'B' Modus, but is too slow
    private HashSet<byte[]> data = new HashSet<>();

    /* internal datastruc
        choosen HashMap for good search performance, value is only zero, never used.
     */
    private HashMap<String, Integer> data2 = new HashMap<>();

    /**
     * Returns Singleton Instance of EcgCheck Class.
     *
     * @return EchCheck Singleton Instance
     */
    public static EcgCheck getInstance() {
        synchronized (EcgCheck.class) {
            if (instance == null) {
                instance = new EcgCheck();
            }
        }
        System.out.println("Called Instance of 'EcgCheck' [" + filename + "]");
        return instance;
    }

    /**
     * CONSTRUCTOR - PRIVATE
     */
    private EcgCheck() {
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.toString());
        }

        synchronized (this) {
            //ecglist = new EcgList(filename);
            data = new HashSet<>();
        }
    }

    /**
     * Set the Filename with ECG-Data. Also flush and reload internal data
     * struct.
     *
     * @param _filename
     */
    public void setFilename(String _filename) {
        filename = _filename;
        try {
            synchronized (EcgCheck.class) {
                this.loadData();
            }//sync
        } catch (IOException ex) {
            Logger.getLogger(EcgCheck.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("File set to : " + _filename);
        System.out.println("Size: " + data.size());
    }

    /**
     * Returns current Filename of ECG - Hashfile.
     *
     * @return String filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns TRUE if given Mail or Domain is allowed ( = not Blocked / not
     * included ) in ECG LIST.
     *
     * @param mailordomain
     * @param mail or domain
     * @return true if is allowed
     */
    public boolean isMailAllowed(String mailordomain) {
        return !this.isInEcg(mailordomain);
    }

    private byte[] string2byte(String s) {
        return null;
    }

    /**
     * Returns true if the given mail/domain is in ECG-LIST
     *
     * @param mailordomain String with mail or domain
     * @return true if the given String's sha1 is in ECG-LIST
     */
    public boolean isInEcg(String mailordomain) {
        // make a byte-array of given String
        byte[] b = sha(mailordomain.getBytes());

        // we use Strings to compare 
        // cause byte[] with same content are not equal
        if (this.data2.containsKey(new String(b))) {
            return true;
        }

        // if mail not hits. we also have to check the domain.
        // cut off everything before the '@'
        String domain = StringUtils.substringAfterLast(mailordomain, "@");
        b = sha(domain.getBytes());
        if (this.data2.containsKey(new String(b))) {
            return true;
        }
        // if not found the mail or the domain in the ECG-List
        return false;
    }

    /**
     *
     * @param mailordomain
     * @return
     * @deprecated is TOO SLOW ;-)
     */
    public boolean ecg_test_b(String mailordomain) {
        boolean ret = false;
        String mail = mailordomain;
        //byte[] mailbyte = mail.getBytes();
        byte[] mailbytes = sha(mail.getBytes());

        // domain is part of mail after (last) '@'
        String domain = StringUtils.substringAfterLast(mailordomain, "@");
        byte[] domainbyte = sha(domain.getBytes());

        for (byte[] bb : this.data) {
            if (Arrays.equals(mailbytes, bb)) {
                return true;
            }
            if (Arrays.equals(domainbyte, bb)) {
                return true;
            }
        }
        return ret;
    }

    /**
     * Load Data from File into internal Data-Struct. SYNCHRONIZED.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private synchronized void loadData() throws FileNotFoundException, IOException {
        this.data.clear();
        this.data2.clear();
        byte[] inbytes = new byte[20];
        int b = -1;
        FileInputStream fis = new FileInputStream(new File(this.getFilename()));
        while ((b = fis.read(inbytes)) != -1) {
            this.data.add(inbytes.clone());
            this.data2.put(new String(inbytes.clone()), 0);
        }
    }

    /**
     *
     * @param string
     * @return
     */
    protected byte[] sha(String string) {
        return sha(string.getBytes());
    }

    /**
     *
     * @param inbyte
     * @return
     */
    protected byte[] sha(byte[] inbyte) {
        return md.digest(inbyte);
    }

    /**
     * Returns Count of Entries in ECG-List.
     *
     * @return int count of blocked entries in ECG-List
     */
    public int getEntryCount() {
        return this.data.size();
    }

    class BBlock {

    }
}
