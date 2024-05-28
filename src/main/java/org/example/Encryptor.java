package org.example;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Encryptor {
    private Key key;

    public Encryptor(byte[] key) {
        this.key = new SecretKeySpec(key, "AES");
    }

    public byte[] encrypt(Message message) {
        // TODO: implement message encryption
        return null;
    }
}
