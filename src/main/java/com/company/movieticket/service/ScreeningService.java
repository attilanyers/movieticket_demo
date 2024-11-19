package com.company.movieticket.service;

import com.company.movieticket.dao.CinemaDao;
import com.company.movieticket.dao.MovieDao;
import com.company.movieticket.dao.ScreeningDao;
import com.company.movieticket.dto.CreateScreeningRequest;
import com.company.movieticket.dto.CreateScreeningResponse;
import com.company.movieticket.dto.GetScreeningResponse;
import com.company.movieticket.dto.ListScreeningRequest;
import com.company.movieticket.dto.UpdateScreeningRequest;
import com.company.movieticket.dto.converter.CreateScreeningRequestConverter;
import com.company.movieticket.dto.converter.CreateScreeningResponseConverter;
import com.company.movieticket.dto.converter.GetScreeningResponseConverter;
import com.company.movieticket.dto.converter.UpdateScreeningRequestConverter;
import com.company.movieticket.dto.converter.list_screening.ListScreeningResponseConverter;
import com.company.movieticket.dto.list_screening.ListScreeningResponse;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScreeningService {
    private ScreeningDao screeningDao;
    private CinemaDao cinemaDao;
    private MovieDao movieDao;
    private SecurityHelper securityHelper;

    public ScreeningService(ScreeningDao screeningDao, CinemaDao cinemaDao, MovieDao movieDao,
                            SecurityHelper securityHelper) {
        this.screeningDao = screeningDao;
        this.cinemaDao = cinemaDao;
        this.movieDao = movieDao;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public CreateScreeningResponse createScreening(CreateScreeningRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        CinemaEntity cinema =
                dto.getCinemaId() != null ? cinemaDao.findById(dto.getCinemaId()) : null;

        MovieEntity movie = dto.getMovieId() != null ? movieDao.findById(dto.getMovieId()) : null;

        CreateScreeningRequestConverter createScreeningRequestConverter =
                new CreateScreeningRequestConverter();
        ScreeningEntity entity = createScreeningRequestConverter.convertTo(dto, cinema, movie);

        entity = screeningDao.save(entity);

        CreateScreeningResponseConverter createScreeningResponseConverter =
                new CreateScreeningResponseConverter();
        return createScreeningResponseConverter.convertTo(entity);
    }

    public GetScreeningResponse getScreening(Integer screeningId) {
        securityHelper.currentUserHasAnyRole(UserRole.USER, UserRole.ADMIN);
        ScreeningEntity entity = screeningDao.findById(screeningId);

        GetScreeningResponseConverter converter = new GetScreeningResponseConverter();
        return converter.convertTo(entity);
    }

    @Transactional
    public void updateScreening(Integer screeningId, UpdateScreeningRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        ScreeningEntity entity = screeningDao.findById(screeningId);

        CinemaEntity cinema =
                dto.getCinemaId() != null ? cinemaDao.findById(dto.getCinemaId()) : null;

        MovieEntity movie = dto.getMovieId() != null ? movieDao.findById(dto.getMovieId()) : null;

        UpdateScreeningRequestConverter converter = new UpdateScreeningRequestConverter();
        converter.convertTo(entity, dto, cinema, movie);

        screeningDao.save(entity);
    }

    @Transactional
    public void deleteScreening(Integer screeningId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        ScreeningEntity entity = screeningDao.findById(screeningId);

        screeningDao.delete(entity);
    }

    public Page<ListScreeningResponse> listScreening(ListScreeningRequest request) {
        Page<ScreeningEntity> entities = screeningDao.listScreening(request);
        ListScreeningResponseConverter converter = new ListScreeningResponseConverter();
        return entities.map(converter::convertTo);
    }
}
