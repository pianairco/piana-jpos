package ir.piana.jpos.test.dao;

import ir.piana.jpos.test.entity.UserTblEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserTblEntity, Long> {
    UserTblEntity findById(long id);
}
