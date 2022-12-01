package com.example.project_c.repository.home;

import com.example.project_c.model.home.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    List<MyUser> findAllByName(String name);

    List<MyUser> findAllByCode(String userCode);

}