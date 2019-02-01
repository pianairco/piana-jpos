package ir.piana.jpos.test.judicature.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
@Entity
@Table(name = "USER_ROLE_TBL", schema = "JUDICATURE", catalog = "")
public class UserRoleTblEntity {
    private long id;
    private long userId;
    private String roles;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ROLES", nullable = false, length = 1024)
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleTblEntity that = (UserRoleTblEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roles);
    }
}
