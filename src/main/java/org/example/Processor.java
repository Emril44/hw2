package org.example;

public class Processor {
    public void process(Message message) {
        // TODO: process message
        String command = new String(message.getMessage());
        System.out.println("Processing command: " + command);

        // Formulated answer ig?
        System.out.println("OK");
    }
}
