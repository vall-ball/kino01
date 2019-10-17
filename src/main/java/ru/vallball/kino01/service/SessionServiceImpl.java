package ru.vallball.kino01.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.kino01.controller.SessionController;
import ru.vallball.kino01.dao.PlaceRepository;
import ru.vallball.kino01.dao.SessionRepository;
import ru.vallball.kino01.model.Category;
import ru.vallball.kino01.model.Place;
import ru.vallball.kino01.model.Session;
import ru.vallball.kino01.model.Status;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	PlaceRepository placeRepository;

	private static final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

	@Override
	public void save(Session session) {
		sessionRepository.save(session);
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				Place place = new Place();
				place.setLine(i);
				place.setNumber(j);
				place.setSession(session);
				place.setStatus(Status.FREE);
				if (j == 1 || j == 5) {
					place.setCategory(Category.SIDE);
					place.setPrice(100);
				} else if (i == 1 || i == 2 || i == 5) {
					place.setCategory(Category.CENTRAL);
					place.setPrice(150);
				} else {
					place.setCategory(Category.VIP);
					place.setPrice(200);
				}
				placeRepository.save(place);
			}
		}

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

	@Override
	public List<Session> findByDate(LocalDate date) {
		List<Session> list = sessionRepository.findAll();
		List<Session> listByDate = new ArrayList<>();
		boolean control = false;
		for (Session s : list) {
			if (s.getDate().equals(date)) {
				listByDate.add(s);
				control = true;
			}
		}
		if (!control) {
			return new ArrayList<Session>();

		}
		return listByDate;
	}

}
