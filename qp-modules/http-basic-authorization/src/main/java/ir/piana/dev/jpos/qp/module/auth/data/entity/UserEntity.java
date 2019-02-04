package ir.piana.dev.jpos.qp.module.auth.data.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/3/2019
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    private long id;
    private String username;
    private String password;
    private String roles;

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ROLES")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
