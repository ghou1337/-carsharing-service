package pl.hibernate.study.demo.model;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    private String username;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carRenter")
    private List<RentedVehicleHistory> rentingUsers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<RentingVehicle> rentedVehicleHistories;

    public User() {
    }

    public User(int id, String username, String password, String email, String name, float money) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Integer getMoneyInt() {
        return Math.round(this.money);
    }

}
