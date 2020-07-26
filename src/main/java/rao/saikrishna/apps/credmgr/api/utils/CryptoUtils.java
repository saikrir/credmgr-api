package rao.saikrishna.apps.credmgr.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class CryptoUtils {
    @Value("${APP_KEY}")
    private String APP_KEY;

    private SecretKeySpec secretKeySpec;
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    @PostConstruct
    public void initializeKey() {
        MessageDigest messageDigest = null;
        try {
            byte[] appSecretBytes = APP_KEY.getBytes(UTF8);
            messageDigest = MessageDigest.getInstance("SHA1");
            appSecretBytes = messageDigest.digest(appSecretBytes);
            appSecretBytes = Arrays.copyOf(appSecretBytes, 16);
            secretKeySpec = new SecretKeySpec(appSecretBytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to initialize Secret Key " + e.getMessage());
        }
    }

    public String encryptText(String stringToEncrypt) {
        String encryptedText = null;
        try {
            Cipher cipher = initCipher(true);
            byte[] bytesToEncrypt = cipher.doFinal(stringToEncrypt.getBytes(UTF8));
            encryptedText = Base64.getEncoder()
                    .encodeToString(bytesToEncrypt);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new UnsupportedOperationException("Unable to encrypt text " + e.getMessage());
        }
        return encryptedText;
    }

    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            Cipher cipher = initCipher(false);
            decryptedText = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | BadPaddingException | IllegalBlockSizeException e) {
            throw new UnsupportedOperationException("Unable to decrypt text " + e.getMessage());
        }
        return decryptedText;
    }

    protected Cipher initCipher(boolean encryptMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        int cipherMode = encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(cipherMode, secretKeySpec);
        return cipher;
    }


}
