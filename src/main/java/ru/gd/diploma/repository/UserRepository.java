package ru.gd.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gd.diploma.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
