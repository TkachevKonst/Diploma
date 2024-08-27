package ru.gd.diploma.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gd.diploma.model.Ticket;
import ru.gd.diploma.model.User;
import ru.gd.diploma.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        Optional<User> user = service.getByID(id);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getALl());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        Optional<User> userOptional = Optional.ofNullable(service.create(user));
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getBooking(@PathVariable Long id) {
        Optional<User> user = service.getByID(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(service.getBooking(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/planes/{planeId}/tickets")
    public ResponseEntity<User> bookingTicket(@PathVariable Long id, @PathVariable Long planeId, @RequestParam String place) {
        Optional<User> userOptional = Optional.ofNullable(service.bookingTicket(id, planeId, place));
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


}
