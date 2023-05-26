package pl.messages.web.model;

public class User {

    private int id;
    private String login;
    private String password;
    private String email;
    private int rentedCarID;

    public User() {
    }

    public User(int id, String login, String password, int rentedCarID, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.rentedCarID = rentedCarID;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRentedCarID() {
        return rentedCarID;
    }

    public void setRentedCarID(int rentedCarID) {
        this.rentedCarID = rentedCarID;
    }
}
