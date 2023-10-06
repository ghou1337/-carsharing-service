package pl.hibernate.study.demo.service.exe;

public class RentHistoryWasNotRecorded extends RuntimeException{
    public RentHistoryWasNotRecorded (String error, Exception e) {
        super(error);
    }
}
