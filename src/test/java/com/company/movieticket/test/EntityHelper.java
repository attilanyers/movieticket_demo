package com.company.movieticket.test;

import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.CinemaRepository;
import com.company.movieticket.repository.MovieRepository;
import com.company.movieticket.repository.ReservationRepository;
import com.company.movieticket.repository.ScreeningRepository;
import com.company.movieticket.repository.UserAuthorityRepository;
import com.company.movieticket.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EntityHelper {
    @Autowired MovieRepository movieRepository;
    @Autowired CinemaRepository cinemaRepository;
    @Autowired UserRepository userRepository;
    @Autowired ScreeningRepository screeningRepository;
    @Autowired ReservationRepository reservationRepository;
    @Autowired UserAuthorityRepository userAuthorityRepository;

    @Transactional
    public MovieEntity createMovie() {
        MovieEntity movie = new MovieEntity();

        movie.setTitle("title-834");

        movie.setDirector("director-653");

        movie.setGenre("genre-214");

        movie.setDuration(655);

        movie.setLength((short)704);

        movie = movieRepository.save(movie);
        return movie;
    }

    @Transactional
    public CinemaEntity createCinema() {
        CinemaEntity cinema = new CinemaEntity();

        cinema.setName("name-916");

        cinema.setLocation("location-872");

        cinema = cinemaRepository.save(cinema);
        return cinema;
    }

    @Transactional
    public UserEntity createUser() {
        UserEntity user = new UserEntity();

        user.setEmail("nancie-294@email.com");

        user.setPassword("$2a$10$QV7ZuB2F1cnu.L/P0QCVFugEkHqUg7MBvyl8dSimYQzmf3TnyusWe");

        user.setPasswordResetToken("passwordResetToken-70");

        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public ScreeningEntity createScreening() {
        CinemaEntity cinema = createCinema();
        MovieEntity movie = createMovie();
        ScreeningEntity screening = new ScreeningEntity();

        screening.setCinema(cinema);

        screening.setMovie(movie);

        screening.setStartTime(LocalDateTime.now());

        screening = screeningRepository.save(screening);
        return screening;
    }

    @Transactional
    public ReservationEntity createReservation() {
        ScreeningEntity screening = createScreening();
        UserEntity user = createUser();
        ReservationEntity reservation = new ReservationEntity();

        reservation.setScreening(screening);

        reservation.setUser(user);

        reservation.setSeatNumber("seatNumber-346");

        reservation = reservationRepository.save(reservation);
        return reservation;
    }

    @Transactional
    public UserAuthorityEntity createUserAuthority() {
        UserEntity user = createUser();
        UserAuthorityEntity userAuthority = new UserAuthorityEntity();

        userAuthority.setUser(user);

        userAuthority.setAuthority("authority-785");

        userAuthority = userAuthorityRepository.save(userAuthority);
        return userAuthority;
    }
}
