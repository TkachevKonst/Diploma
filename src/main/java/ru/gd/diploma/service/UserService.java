package ru.gd.diploma.service;

import org.springframework.stereotype.Service;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;
import ru.gd.diploma.model.User;
import ru.gd.diploma.repository.PlaneRepository;
import ru.gd.diploma.repository.TicketRepository;
import ru.gd.diploma.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private PlaneRepository planeRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    public UserService(UserRepository userRepository, PlaneRepository planeRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.planeRepository = planeRepository;
        this.ticketRepository = ticketRepository;
    }

    public Optional<User> getByID(Long id) {
        return userRepository.findById(id);
    }


    public List<User> getALl() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public List<Ticket> getBooking(Long id) {
        List<Ticket> ticketList = new ArrayList<>();
        List<Long> idTikets = userRepository.findById(id).get().getReservations();

        for (int i = 0; i < idTikets.size(); i++) {
            ticketList.add(ticketRepository.findById(idTikets.get(i)).get());
        }
        return ticketList;
    }


    public User bookingTicket(Long userId, Long planeId, String place) {
        User user = getByID(userId).get();
        List<Long> idTikets = planeRepository.findById(planeId).get().getTicketList();
        Ticket ticket = new Ticket();
        for (int i = 0; i < idTikets.size(); i++) {
            if (ticketRepository.findById(idTikets.get(i)).get().getPlace().equals(place)) {
                ticket = ticketRepository.findById(idTikets.get(i)).get();
                if (!ticket.isBooking()) {
                    user.bookingTicket(ticket.getId());
                    userRepository.save(user);
                    ticket.setBooking(true);
                    ticketRepository.save(ticket);
                    return user;
                }else user = null;
            }
        }
        return user;
    }

}
