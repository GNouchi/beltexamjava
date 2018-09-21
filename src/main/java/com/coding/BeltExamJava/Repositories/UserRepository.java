package com.coding.BeltExamJava.Repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.coding.BeltExamJava.Models.User;
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
}
