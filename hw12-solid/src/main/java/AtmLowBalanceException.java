public class AtmLowBalanceException extends Exception {
    public AtmLowBalanceException(String errorMessage) {
        super(errorMessage);
    }
}