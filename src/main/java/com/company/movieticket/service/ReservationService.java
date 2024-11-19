package com.company.movieticket.service;

import com.company.movieticket.dao.ReservationDao;
import com.company.movieticket.dao.ScreeningDao;
import com.company.movieticket.dao.UserDao;
import com.company.movieticket.dto.CreateReservationRequest;
import com.company.movieticket.dto.CreateReservationResponse;
import com.company.movieticket.dto.GetReservationResponse;
import com.company.movieticket.dto.ListReservationRequest;
import com.company.movieticket.dto.UpdateReservationRequest;
import com.company.movieticket.dto.converter.CreateReservationRequestConverter;
import com.company.movieticket.dto.converter.CreateReservationResponseConverter;
import com.company.movieticket.dto.converter.GetReservationResponseConverter;
import com.company.movieticket.dto.converter.UpdateReservationRequestConverter;
import com.company.movieticket.dto.converter.list_reservation.ListReservationResponseConverter;
import com.company.movieticket.dto.list_reservation.ListReservationResponse;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private ScreeningDao screeningDao;
    private ReservationDao reservationDao;
    private UserDao userDao;
    private SecurityHelper securityHelper;

    public ReservationService(
            ScreeningDao screeningDao, ReservationDao reservationDao, UserDao userDao,
            SecurityHelper securityHelper) {
        this.screeningDao = screeningDao;
        this.reservationDao = reservationDao;
        this.userDao = userDao;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public CreateReservationResponse createReservation(CreateReservationRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.USER, UserRole.ADMIN);
        ScreeningEntity screening =
                dto.getScreeningId() != null ? screeningDao.findById(dto.getScreeningId()) : null;

        UserEntity user = dto.getUserId() != null ? userDao.findById(dto.getUserId()) : null;

        CreateReservationRequestConverter createReservationRequestConverter =
                new CreateReservationRequestConverter();
        ReservationEntity entity =
                createReservationRequestConverter.convertTo(dto, screening, user);

        entity = reservationDao.save(entity);

        CreateReservationResponseConverter createReservationResponseConverter =
                new CreateReservationResponseConverter();
        return createReservationResponseConverter.convertTo(entity);
    }

    public GetReservationResponse getReservation(Integer reservationId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN, UserRole.USER);
        ReservationEntity entity = reservationDao.findById(reservationId);

        if (securityHelper.getCurrentUser().hasAnyRole(UserRole.USER)) {
            securityHelper.currentUserEquals(entity.getUser());
        }

        GetReservationResponseConverter converter = new GetReservationResponseConverter();
        return converter.convertTo(entity);
    }

    @Transactional
    public void updateReservation(Integer reservationId, UpdateReservationRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        ReservationEntity entity = reservationDao.findById(reservationId);

        ScreeningEntity screening =
                dto.getScreeningId() != null ? screeningDao.findById(dto.getScreeningId()) : null;

        UserEntity user = dto.getUserId() != null ? userDao.findById(dto.getUserId()) : null;

        UpdateReservationRequestConverter converter = new UpdateReservationRequestConverter();
        converter.convertTo(entity, dto, screening, user);

        reservationDao.save(entity);
    }

    @Transactional
    public void deleteReservation(Integer reservationId) {
        securityHelper.currentUserHasAnyRole(UserRole.USER, UserRole.ADMIN);
        ReservationEntity entity = reservationDao.findById(reservationId);

        reservationDao.delete(entity);
    }

    public Page<ListReservationResponse> listReservation(ListReservationRequest request) {
        Page<ReservationEntity> entities = reservationDao.listReservation(request);
        ListReservationResponseConverter converter = new ListReservationResponseConverter();
        return entities.map(converter::convertTo);
    }
}
