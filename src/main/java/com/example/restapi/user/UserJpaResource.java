package com.example.restapi.user;

import com.example.restapi.jpa.PostRepository;
import com.example.restapi.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {

        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id: " + id);
        }

        return user.get();
    }



    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id: " + id);
        }
        return user.get().getPosts();

    }

    @PostMapping("/jpa/users/{id}/posts")
    public void createUserPost(@PathVariable int id, @RequestBody @Valid Post post){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user.get());
        postRepository.save(post);

    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User savedUser  = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/jpa/users")
//    public List<User> createUser(@RequestBody @Valid User user){
//        User savedUser  = userRepository.save(user);
//        return userRepository.findAll();
//    }
}
