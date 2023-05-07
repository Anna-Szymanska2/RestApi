package com.example.restapi.jpa;


import com.example.restapi.user.Post;
import com.example.restapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
