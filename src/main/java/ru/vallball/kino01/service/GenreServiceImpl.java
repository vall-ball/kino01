package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.vallball.kino01.dao.GenreRepository;
import ru.vallball.kino01.model.Genre;

public class GenreServiceImpl implements GenreService{

	@Autowired
	GenreRepository genreRepository;
	
	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);
		
	}

	@Override
	public List<Genre> list() {
		return genreRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		genreRepository.deleteById(id);
		
	}

	@Override
	public void update(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public Genre findGenreById(Long id) {
		return genreRepository.findById(id).get();
	}

	@Override
	public Page<Genre> findAll(Pageable pageable) {
		return genreRepository.findAll(pageable);
	}

}
