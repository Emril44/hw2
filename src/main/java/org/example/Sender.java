package org.example;

import java.net.InetAddress;

public class Sender {
    public void sendMessage(byte[] message, InetAddress target) {
        // TODO: implement message sending (just output the message for now)
        System.out.println("Sending to " + target.getHostAddress() + ": " + bytesToHex(message));
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
