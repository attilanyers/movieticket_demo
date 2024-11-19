package com.company.movieticket.service;

import com.company.movieticket.dao.CinemaDao;
import com.company.movieticket.dto.CreateCinemaRequest;
import com.company.movieticket.dto.CreateCinemaResponse;
import com.company.movieticket.dto.GetCinemaResponse;
import com.company.movieticket.dto.ListCinemaRequest;
import com.company.movieticket.dto.UpdateCinemaRequest;
import com.company.movieticket.dto.converter.CreateCinemaRequestConverter;
import com.company.movieticket.dto.converter.CreateCinemaResponseConverter;
import com.company.movieticket.dto.converter.GetCinemaResponseConverter;
import com.company.movieticket.dto.converter.UpdateCinemaRequestConverter;
import com.company.movieticket.dto.converter.list_cinema.ListCinemaResponseConverter;
import com.company.movieticket.dto.list_cinema.ListCinemaResponse;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaService {
    private CinemaDao cinemaDao;
    private SecurityHelper securityHelper;

    public CinemaService(CinemaDao cinemaDao, SecurityHelper securityHelper) {
        this.cinemaDao = cinemaDao;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public CreateCinemaResponse createCinema(CreateCinemaRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);

        CreateCinemaRequestConverter createCinemaRequestConverter =
                new CreateCinemaRequestConverter();
        CinemaEntity entity = createCinemaRequestConverter.convertTo(dto);

        entity = cinemaDao.save(entity);

        CreateCinemaResponseConverter createCinemaResponseConverter =
                new CreateCinemaResponseConverter();
        return createCinemaResponseConverter.convertTo(entity);
    }

    public GetCinemaResponse getCinema(Integer cinemaId) {
        securityHelper.currentUserHasAnyRole(UserRole.USER, UserRole.ADMIN);
        CinemaEntity entity = cinemaDao.findById(cinemaId);

        GetCinemaResponseConverter converter = new GetCinemaResponseConverter();
        return converter.convertTo(entity);
    }

    @Transactional
    public void updateCinema(Integer cinemaId, UpdateCinemaRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        CinemaEntity entity = cinemaDao.findById(cinemaId);

        UpdateCinemaRequestConverter converter = new UpdateCinemaRequestConverter();
        converter.convertTo(entity, dto);

        cinemaDao.save(entity);
    }

    @Transactional
    public void deleteCinema(Integer cinemaId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        CinemaEntity entity = cinemaDao.findById(cinemaId);

        cinemaDao.delete(entity);
    }

    public Page<ListCinemaResponse> listCinema(ListCinemaRequest request) {
        Page<CinemaEntity> entities = cinemaDao.listCinema(request);
        ListCinemaResponseConverter converter = new ListCinemaResponseConverter();
        return entities.map(converter::convertTo);
    }
}
