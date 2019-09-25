package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.vallball.kino01.model.Film;


public interface FilmService {
	
	void save(Film film);
	List<Film> list();
	void delete(Long id);
	void update(Film film);
	Film findFilmById(Long id);
	Page<Film> findAll(Pageable pageable);
}
