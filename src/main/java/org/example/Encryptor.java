package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.Key;

public class Encryptor {
    private Key key;
    private static final String CIPHER_ALGORITHM = "AES";

    public Encryptor(byte[] key) {
        this.key = new SecretKeySpec(key, CIPHER_ALGORITHM);
    }

    public byte[] encrypt(Message message) throws Exception {
        // TODO: implement message encryption
        byte[] serializedMsg = serializeMsg(message);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(serializedMsg);
    }

    private byte[] serializeMsg(Message message) {
        int totalLength = Integer.BYTES * 2 + message.getMessage().length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        buffer.putInt(message.getcType());
        buffer.putInt(message.getbUserId());
        buffer.put(message.getMessage());
        return buffer.array();
    }
}
