package org.xinyue.xsecurity.exception;

public class CustomizedException extends RuntimeException {
    private int id;

    public CustomizedException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
