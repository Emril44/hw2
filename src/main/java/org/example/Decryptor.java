package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.Key;

public class Decryptor {
    private Key key;
    private Processor processor;
    private static final String CIPHER_ALGORITHM = "AES";

    public Decryptor(byte[] key, Processor processor) {
        this.key = new SecretKeySpec(key, CIPHER_ALGORITHM);
        this.processor = processor;
    }

    public void decrypt(byte[] message) throws Exception {
        // TODO: implement decryption
        new Thread(() -> {
            try {
                Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, key);
                byte[] decryptedData = cipher.doFinal(message);

                // Retrieved message
                Message decryptedMsg = deserializeMsg(decryptedData);
                processor.process(decryptedMsg);
            } catch (Exception e) {
                System.err.println("Exception in decrypt(byte[] message)!");
            }
        }).start();
    }

    private Message deserializeMsg(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int cType = buffer.getInt();
        int bUserID = buffer.getInt();
        byte[] message = new byte[buffer.remaining()];
        buffer.get(message);
        return new Message(cType, bUserID, message);
    }
}
