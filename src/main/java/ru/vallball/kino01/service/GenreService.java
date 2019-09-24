package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.vallball.kino01.model.Genre;

public interface GenreService {
	
	void save(Genre genre);
	List<Genre> list();
	void delete(Long id);
	void update(Genre genre);
	Genre findGenreById(Long id);
	Page<Genre> findAll(Pageable pageable);

}


