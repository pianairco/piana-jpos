package ir.piana.jpos.test.judicature.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
@Entity
@Table(name = "USER_TBL", schema = "JUDICATURE", catalog = "")
@SequenceGenerator(name = "user-seq",
        sequenceName = "user_tbl_seq",
        initialValue = 1,
        allocationSize = 1000)
public class UserTblEntity {
    private Long id;
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user-seq")
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 64)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTblEntity that = (UserTblEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
