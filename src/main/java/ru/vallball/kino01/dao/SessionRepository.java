package ru.vallball.kino01.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	
//	List<Session> findAllByFilm(Film film);
	Page<Session> findAllByFilm(Pageable pageable, Film film);

}
