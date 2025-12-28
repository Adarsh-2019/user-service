package com.user.controller;

import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Save the user")
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.ok().body(created);
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<?> listUsers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "update user")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody User user) {
        if (!userService.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get user by email")
    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        Optional<User> u = userService.getUserByEmail(email);
        return u.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
