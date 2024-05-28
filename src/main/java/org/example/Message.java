package org.example;

public class Message {
    public final int cType;
    public final int bUserId;
    public final byte[] message;

    public Message(int cType, int bUserID, byte[] payload) {
        this.message = payload;
        this.cType = cType;
        this.bUserId = bUserID;
    }

    public int getbUserId() {
        return bUserId;
    }
    public int getcType() {
        return cType;
    }
    public byte[] getMessage() {
        return message;
    }
}
