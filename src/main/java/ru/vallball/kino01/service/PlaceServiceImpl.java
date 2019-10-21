package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.kino01.dao.PlaceRepository;
import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Place;
import ru.vallball.kino01.model.Session;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceRepository placeRepository;

	@Override
	public void save(Place place) {
		placeRepository.save(place);
	}

	@Override
	public List<Place> list() {
		return placeRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		placeRepository.deleteById(id);
	}

	@Override
	public void update(Place place) {
		placeRepository.save(place);
	}

	@Override
	public Place findPlaceById(Long id) {
		return placeRepository.findById(id).get();
	}

	@Override
	public Page<Place> findAll(Pageable pageable) {
		return placeRepository.findAll(pageable);
	}
	
	@Override
	public List<Place> findAllBySession(Session session) {
		return placeRepository.findAllBySession(session);
	}

}
