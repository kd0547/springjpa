package org.example.springweb1.domain.item;

public class NotEnuoughStockException extends RuntimeException {
    public NotEnuoughStockException(String message) {
        super(message);
    }

    public NotEnuoughStockException() {
        super();
    }

    public NotEnuoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnuoughStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnuoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
