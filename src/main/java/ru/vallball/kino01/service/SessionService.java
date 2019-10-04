package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.vallball.kino01.model.Session;

public interface SessionService {

	void save(Session session);

	List<Session> list();

	void delete(Long id);

	void update(Session session);

	Session findSessionById(Long id);

	Page<Session> findAll(Pageable pageable);

}
