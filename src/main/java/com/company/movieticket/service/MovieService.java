package com.company.movieticket.service;

import com.company.movieticket.dao.MovieDao;
import com.company.movieticket.dto.CreateMovieRequest;
import com.company.movieticket.dto.CreateMovieResponse;
import com.company.movieticket.dto.GetMovieResponse;
import com.company.movieticket.dto.ListMovieRequest;
import com.company.movieticket.dto.UpdateMovieRequest;
import com.company.movieticket.dto.converter.CreateMovieRequestConverter;
import com.company.movieticket.dto.converter.CreateMovieResponseConverter;
import com.company.movieticket.dto.converter.GetMovieResponseConverter;
import com.company.movieticket.dto.converter.UpdateMovieRequestConverter;
import com.company.movieticket.dto.converter.list_movie.ListMovieResponseConverter;
import com.company.movieticket.dto.list_movie.ListMovieResponse;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.UserRole;
import com.company.movieticket.security.SecurityHelper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    private MovieDao movieDao;
    private SecurityHelper securityHelper;
    public MovieService(MovieDao movieDao, SecurityHelper securityHelper) {
        this.movieDao = movieDao;
        this.securityHelper = securityHelper;
    }

    @Transactional
    public CreateMovieResponse createMovie(CreateMovieRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);

        CreateMovieRequestConverter createMovieRequestConverter = new CreateMovieRequestConverter();
        MovieEntity entity = createMovieRequestConverter.convertTo(dto);

        entity = movieDao.save(entity);

        CreateMovieResponseConverter createMovieResponseConverter =
                new CreateMovieResponseConverter();
        return createMovieResponseConverter.convertTo(entity);
    }

    public GetMovieResponse getMovie(Integer movieId) {
        securityHelper.currentUserHasAnyRole(UserRole.USER, UserRole.ADMIN);
        MovieEntity entity = movieDao.findById(movieId);

        GetMovieResponseConverter converter = new GetMovieResponseConverter();
        return converter.convertTo(entity);
    }

    @Transactional
    public void updateMovie(Integer movieId, UpdateMovieRequest dto) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        MovieEntity entity = movieDao.findById(movieId);

        UpdateMovieRequestConverter converter = new UpdateMovieRequestConverter();
        converter.convertTo(entity, dto);

        movieDao.save(entity);
    }

    @Transactional
    public void deleteMovie(Integer movieId) {
        securityHelper.currentUserHasAnyRole(UserRole.ADMIN);
        MovieEntity entity = movieDao.findById(movieId);

        movieDao.delete(entity);
    }

    public Page<ListMovieResponse> listMovie(ListMovieRequest request) {
        Page<MovieEntity> entities = movieDao.listMovie(request);
        ListMovieResponseConverter converter = new ListMovieResponseConverter();
        return entities.map(converter::convertTo);
    }
}
