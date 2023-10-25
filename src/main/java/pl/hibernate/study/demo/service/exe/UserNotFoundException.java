package pl.hibernate.study.demo.service.exe;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String error) {
        super(error);
    }
}
