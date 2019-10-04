package ru.vallball.kino01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
