package vitfed.kata_spr_boot_security_pp_3_1_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vitfed.kata_spr_boot_security_pp_3_1_3.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   // @Query("select u from User u join fetch u.roles where u.username = :username")
    //@Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    User findByUsername(String username);
}
