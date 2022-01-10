package client.graphics;

public class NoJFrameException extends Exception {
    /**
     * Call exception with own message.
     * @param s message thrown in exception
     */
    public NoJFrameException(final String s) {
        super(s);
    }
}
