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
        System.setProperty("APP_KEY", "random");
        System.setProperty("DB_USER", "chickbuk");
        System.setProperty("DB_PASSWORD", "chikbuk");
    }


    @Test
    void contextLoads() {
        System.out.println("Password " + cryptoUtils.encryptText("dust"));
        System.out.println("Password " + cryptoUtils.encryptText("bowl"));
    }

}
