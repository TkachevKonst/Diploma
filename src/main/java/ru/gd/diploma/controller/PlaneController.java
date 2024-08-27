package ru.gd.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;
import ru.gd.diploma.model.User;
import ru.gd.diploma.service.PlaneService;
import ru.gd.diploma.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/planes")
public class PlaneController {
    private final PlaneService service;

    public PlaneController(PlaneService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Plane> get(@PathVariable Long id) {
        Optional<Plane> plane = service.getByID(id);
        if (plane.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(plane.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getTicketsPlane(@PathVariable Long id) {
       return ResponseEntity.ok(service.getAllTiketsPlane(id));
    }

    @GetMapping
    public ResponseEntity<List<Plane>> getAll() {
        return ResponseEntity.ok(service.getALl());
    }

    @PostMapping
    public ResponseEntity<Plane> create(@RequestBody Plane plane) {
        Optional<Plane> planeOptional = Optional.ofNullable(service.create(plane));
        if (planeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(planeOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searches")
    @ResponseBody
    public ResponseEntity<List<Plane>> aircraftSearch (@RequestParam String departurePoint,@RequestParam String destination, @RequestParam String arrivalTime){
        return ResponseEntity.ok(service.aircraftSearch(departurePoint, destination, arrivalTime));
    }

}
