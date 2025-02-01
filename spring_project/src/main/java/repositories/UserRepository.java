package repositories;

import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides all basic CRUD operations (e.g., save, findAll, findById, deleteById)
}