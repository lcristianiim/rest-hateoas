package com.rest.rest;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);

	}
}
@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@RestResource
	Collection<Reservation> findByReservationName(@Param("rn") String rn);
}

@RepositoryRestResource()
interface CIRepository extends JpaRepository<Reservation, Long> {
}

@Component
class DummyData {
	@Autowired
	ReservationRepository reservationRepository;

	@PostConstruct
	public void saveData() {
		Reservation reservation = new Reservation("John");
		CI ci = new CI("132132114333", "xh", "123456");
		reservation.setCi(ci);

		Reservation reservation2 = new Reservation("Christine");
		CI ci2 = new CI("234243243223", "xh", "654321");
		reservation2.setCi(ci2);

		Reservation reservation3 = new Reservation("Jack");
		CI ci3 = new CI("132433434234", "xh", "343432");
		reservation3.setCi(ci3);

		reservationRepository.save(reservation);
		reservationRepository.save(reservation2);
		reservationRepository.save(reservation3);

		System.out.println("HELLO");
		System.out.println(reservationRepository.findAll().toString());
	}
};

@Entity
class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;
	@OneToOne(cascade = CascadeType.ALL)
	private CI ci;

	


	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	public Reservation(String reservationName, CI ci) {
		this.reservationName = reservationName;
		this.ci = ci;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public CI getCi() {
		return ci;
	}

	public void setCi(CI ci) {
		this.ci = ci;
	}

	public Reservation() {}

	public Long getId() {
		return id;
	}

	public String getReservationName() {
		return reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}
}