package pl.hibernate.study.demo.service.exe;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String error) {
        super(error);
    }
}
