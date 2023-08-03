package pl.hibernate.study.demo.service.exe;

public class NotEnoughBalanceException extends Exception{
    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
