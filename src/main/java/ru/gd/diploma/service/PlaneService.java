package ru.gd.diploma.service;

import org.springframework.stereotype.Service;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;
import ru.gd.diploma.repository.PlaneRepository;
import ru.gd.diploma.repository.TicketRepository;
import ru.gd.diploma.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PlaneService {


    private TicketRepository ticketRepository;
    private PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository, TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.planeRepository = planeRepository;
    }

    public Optional<Plane> getByID(Long id) {
        return planeRepository.findById(id);
    }



    public List<Plane> getALl() {
        return planeRepository.findAll();
    }

    public Plane create(Plane plane) {
        return planeRepository.save(plane);
    }

    public void delete(Long id) {
        planeRepository.deleteById(id);
    }

    public List<Plane> aircraftSearch (String departurePoint, String destination, String arrivalTime){
        LocalDate localDate = LocalDate.parse(arrivalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return planeRepository.findByDeparturePointAndDestinationAndArrivalTime(departurePoint, destination, localDate);
    }

    public List<Ticket> getAllTiketsPlane(Long id){
        List<Long> idTikets = planeRepository.findById(id).get().getTicketList();
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < idTikets.size(); i++) {
            ticketList.add(ticketRepository.findById(idTikets.get(i)).get());
        }
        return ticketList;
    }



}
