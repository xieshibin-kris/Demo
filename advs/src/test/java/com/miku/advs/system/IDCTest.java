package com.miku.advs.system;

import net.ipip.ipdb.IDC;
import org.junit.Test;

import java.io.IOException;

public class IDCTest {

    @Test
    public void testIDC() {
        try {
            IDC db = new IDC("D:\\Codes\\advs\\src\\main\\webapp\\ipfile\\ipipfree.ipdb");
            System.out.println(db.buildTime());
            System.out.println(db.languages());
            System.out.println(db.fields());
            System.out.println(db.isIPv4());
            System.out.println(db.isIPv6());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
