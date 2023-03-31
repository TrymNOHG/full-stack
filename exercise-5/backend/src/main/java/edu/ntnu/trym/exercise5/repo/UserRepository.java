package edu.ntnu.trym.exercise5.repo;

import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findUserByUsername(String username);
}
