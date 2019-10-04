package ru.vallball.kino01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.kino01.dao.SessionRepository;
import ru.vallball.kino01.model.Session;

@Service
@Transactional
public class SessionServiceImpl implements SessionService{
	
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public void save(Session session) {
		sessionRepository.save(session);
		
	}

	@Override
	public List<Session> list() {
		return sessionRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		sessionRepository.deleteById(id);
		
	}

	@Override
	public void update(Session session) {
		sessionRepository.save(session);
		
	}

	@Override
	public Session findSessionById(Long id) {
		return sessionRepository.findById(id).get();
	}

	@Override
	public Page<Session> findAll(Pageable pageable) {
		return sessionRepository.findAll(pageable);
	}

}
