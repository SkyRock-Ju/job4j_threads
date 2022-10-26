package cash;

abstract class BaseException extends RuntimeException {

    public BaseException() {
        super("Unexpected error");
    }

    public BaseException(String msg) {
        super(msg);
    }
}

class ConflictException extends BaseException {
    ConflictException(String msg) {
        super(msg);
    }
}
