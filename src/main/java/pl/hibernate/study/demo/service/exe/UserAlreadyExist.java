package pl.hibernate.study.demo.service.exe;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String error) {
        super(error);
    }
}
