package ru.msu.cmc.web_prac.video_rental;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.msu.cmc.web_prac.video_rental.DAO.FilmDAO;
import ru.msu.cmc.web_prac.video_rental.tables.Film;

@SpringBootApplication
public class VideoRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalApplication.class, args);
	}

}
