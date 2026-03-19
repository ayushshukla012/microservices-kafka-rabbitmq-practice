package net.javaguides.springboot.repository;

import net.javaguides.springboot.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
