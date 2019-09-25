package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.kino01.dao.FilmRepository;
import ru.vallball.kino01.model.Film;

@Service
@Transactional
public class FilmServiceImpl implements FilmService{
	
	@Autowired
	FilmRepository filmRepository;

	@Override
	public void save(Film film) {
		filmRepository.save(film);
		
	}

	@Override
	public List<Film> list() {
		return filmRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		filmRepository.deleteById(id);
		
	}

	@Override
	public void update(Film film) {
		filmRepository.save(film);
		
	}

	@Override
	public Film findFilmById(Long id) {
		return filmRepository.findById(id).get();
	}

	@Override
	public Page<Film> findAll(Pageable pageable) {
		return filmRepository.findAll(pageable);
	}

}
