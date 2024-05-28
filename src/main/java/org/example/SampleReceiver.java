package org.example;

import java.util.Random;

public class SampleReceiver implements Receiver {
    private Decryptor decryptor;
    private Encryptor encryptor; // To demonstrate decryption
    private Random randomGen;

    public SampleReceiver(Encryptor encryptor, Decryptor decryptor) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.randomGen = new Random();
    }

    @Override
    public void receiveMessage() {
        // TODO: Process and generate messages
        // Generate random message
        String[] messages = {
                "GET_QUANTITY:product1",
                "DEDUCT_QUANTITY:product1:10",
                "ADD_QUANTITY:product1:20",
                "ADD_GROUP:group1",
                "ADD_PRODUCT_TO_GROUP:product1:group1",
                "SET_PRICE:product1:39.99"
        };

        // Pick out random message
        String messageText = messages[randomGen.nextInt(messages.length)];
        byte[] msgBytes = messageText.getBytes();
        Message message = new Message(1, 1, msgBytes);

        // Encrypt to showcase decryption and preserve message
        try {
            byte[] encryptedMsg = encryptor.encrypt(message);
            decryptor.decrypt(encryptedMsg);
        } catch (Exception e) {
            System.err.println("Error in SampleReceiver decryption!");
        }
    }
}
