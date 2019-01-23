package ir.piana.dev.jpos.qp.spring.data.dao;

import ir.piana.dev.jpos.qp.spring.data.entity.UserTblEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserTblEntity, Long> {
    UserTblEntity findById(long id);
}
