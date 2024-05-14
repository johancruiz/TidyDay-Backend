package com.tidyday.TidyDay.Project.repository;


import com.tidyday.TidyDay.Project.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
        User  findByEmail(String email);
}
