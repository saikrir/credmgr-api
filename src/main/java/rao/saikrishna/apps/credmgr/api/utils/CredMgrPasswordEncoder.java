package rao.saikrishna.apps.credmgr.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CredMgrPasswordEncoder implements PasswordEncoder {

    @Autowired
    private CryptoUtils cryptoUtils;

    @Override
    public String encode(CharSequence rawPassword) {
        return cryptoUtils.encryptText(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.equals(rawPassword, encodedPassword);
    }
}
