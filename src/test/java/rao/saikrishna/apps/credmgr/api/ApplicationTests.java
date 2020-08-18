package rao.saikrishna.apps.credmgr.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rao.saikrishna.apps.credmgr.api.utils.CryptoUtils;
import rao.saikrishna.apps.credmgr.api.utils.TokenUtils;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private CryptoUtils cryptoUtils;

    static {
        System.setProperty("APP_KEY", "skrao");
        System.setProperty("DB_USER", "web_user");
        System.setProperty("DB_PASSWORD", "tempid1");
    }


    @Test
    void contextLoads() {
        System.out.println("Password " + cryptoUtils.encryptText("dust"));
        System.out.println("Password " + cryptoUtils.encryptText("bowl"));
    }

}
