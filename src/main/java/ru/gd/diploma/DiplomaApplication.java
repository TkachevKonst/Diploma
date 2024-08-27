package ru.gd.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gd.diploma.model.Plane;
import ru.gd.diploma.model.Ticket;
import ru.gd.diploma.model.User;
import ru.gd.diploma.repository.PlaneRepository;
import ru.gd.diploma.repository.TicketRepository;
import ru.gd.diploma.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class DiplomaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiplomaApplication.class, args);
		PlaneRepository planeRepository = ctx.getBean(PlaneRepository.class);
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		TicketRepository ticketRepository = ctx.getBean(TicketRepository.class);
		Random random = new Random();
		LocalDate createdAt = LocalDate.now();


		for (int i = 0; i < 20; i++) {
			Ticket ticket = new Ticket();
			ticket.setPlace(String.valueOf(i+1));
			ticketRepository.save(ticket);
		}


		List<Long> idTikets1 = new ArrayList<>();
		List<Long> idTikets2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			idTikets1.add((long) (i+1));
		}
		for (int i = 11; i < 20; i++) {
			idTikets2.add((long) (i+1));
		}
		Plane plane1 = new Plane();
		plane1.setTicketList(idTikets1);
		plane1.setDeparturePoint("Москва");
		plane1.setDestination("Калининград");
		plane1.setFlightNumber("F123");
		plane1.setDepartureTime(LocalDate.of(2024,5,30));
		plane1.setArrivalTime(LocalDate.of(2024,5,30));
		planeRepository.save(plane1);


		Plane plane2 = new Plane();
		plane2.setTicketList(idTikets2);
		plane2.setDeparturePoint("Самара");
		plane2.setDestination("Волгоград");
		plane2.setFlightNumber("A001");
		plane2.setDepartureTime(LocalDate.of(2024,4,02));
		plane2.setArrivalTime(LocalDate.of(2024,4,02));
		planeRepository.save(plane2);


		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setName("user "+ 1);
			userRepository.save(user);
		}
	}

}
