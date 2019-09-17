package ru.vallball.kino01.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.kino01.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String username);

}
