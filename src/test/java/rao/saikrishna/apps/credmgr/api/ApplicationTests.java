package rao.saikrishna.apps.credmgr.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rao.saikrishna.apps.credmgr.api.utils.CryptoUtils;

@SpringBootTest
class ApplicationTests {

    @Autowired
    CryptoUtils cryptoUtils;

    @Test
    void contextLoads() {
        System.out.println(cryptoUtils.encryptText("jaya"));
    }

}
