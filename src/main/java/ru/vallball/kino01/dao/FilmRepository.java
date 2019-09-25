package ru.vallball.kino01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{

}
