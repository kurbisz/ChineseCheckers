package connection;

/**
 * Cannot connect to server.
 */
public class NoConnectionException extends Exception {

    public NoConnectionException(String str) {
        super(str);
    }

}
