package edu.uh.plainsight.util;

public class OutOfEncryptionSpaceException extends Exception {
    public OutOfEncryptionSpaceException() {
        super("EncryptThread ran out of usable pixels to encrypt data into.");
    }
}
