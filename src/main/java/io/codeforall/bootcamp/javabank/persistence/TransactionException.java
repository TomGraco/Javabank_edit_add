package io.codeforall.bootcamp.javabank.persistence;

/**
 * A {@link RuntimeException} used to wrap database transactions errors
 */
public class TransactionException extends RuntimeException {

    /**
     * @see RuntimeException#RuntimeException(Throwable)
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
