package ru.vallball.kino01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
