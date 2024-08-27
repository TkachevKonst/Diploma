package ru.gd.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
