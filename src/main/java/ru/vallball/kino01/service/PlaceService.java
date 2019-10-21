package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.vallball.kino01.model.Place;
import ru.vallball.kino01.model.Session;

public interface PlaceService {

	void save(Place place);

	List<Place> list();

	void delete(Long id);

	void update(Place place);

	Place findPlaceById(Long id);

	Page<Place> findAll(Pageable pageable);
	
	public List<Place> findAllBySession(Session session);

}
