package ru.vallball.kino01.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Place;
import ru.vallball.kino01.model.Session;

public interface PlaceRepository extends JpaRepository<Place, Long> {
	public List<Place> findAllBySession(Session session);
}
