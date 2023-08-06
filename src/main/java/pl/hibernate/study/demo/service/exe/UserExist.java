package pl.hibernate.study.demo.service.exe;

public class UserExist extends Exception{
    public UserExist(String error) {
        super(error);
    }
}
