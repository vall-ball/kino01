package ru.vallball.kino01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
