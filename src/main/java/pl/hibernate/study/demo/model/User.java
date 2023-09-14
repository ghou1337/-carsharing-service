package pl.hibernate.study.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carsharing_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Shouldn't be empty")
    @Size(min = 3, max = 30, message = "Login should be grater then 3 characters")
    @Column(name = "login")
    private String login;
    @NotEmpty(message = "Shouldn't be empty")
    @Size(min = 3, max = 30, message = "Password should be grater then 3 characters")
    @Column(name = "password")
    private String password;
    @NotEmpty(message = "Shouldn't be empty")
    @Email
    @Size(min = 3, max = 30, message = "Login should be grater then 3 characters")
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "permissions")
    private String role;
    @Column(name = "money")
    private float money;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userVehicle")
    private List<Vehicle> rentedVehicle;

    public User() {
    }

    public User(int id, String login, String password, String email, String name, float money) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Vehicle> getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(List<Vehicle> rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
