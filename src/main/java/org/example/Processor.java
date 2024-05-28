package org.example;

import java.net.InetAddress;

public class Processor {
    private Encryptor encryptor;
    private Sender sender;

    public Processor(Encryptor encryptor, Sender sender) {
        this.encryptor = encryptor;
        this.sender = sender;
    }

    public void process(Message message) {
        // TODO: process message
        String command = new String(message.getMessage());
        System.out.println("Processing command: " + command);

        // Formulated answer ig?
        handleResponse("Ok", message.getbUserId());
    }

    private void handleResponse(String res, int bUserID) {
        Message resMsg = new Message(1, bUserID, res.getBytes());

        new Thread(() -> {
            try {
                byte[] encryptedRes = encryptor.encrypt(resMsg);
                InetAddress target = InetAddress.getByName("localhost");
                sender.sendMessage(encryptedRes, target);
            } catch (Exception e) {
                System.err.println("Error in Processor's handleResponse!");
            }
        }).start();
    }
}
