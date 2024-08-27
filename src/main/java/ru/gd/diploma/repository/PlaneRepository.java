package ru.gd.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {


    List<Plane> findByDeparturePointAndDestinationAndArrivalTime(String departurePoint,String destination, LocalDate arrivalTime);





}
