package org.myftp.ufocult.smotrish.repos;

import org.myftp.ufocult.smotrish.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
