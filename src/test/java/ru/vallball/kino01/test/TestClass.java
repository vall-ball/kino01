package ru.vallball.kino01.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.vallball.kino01.model.Film;
import ru.vallball.kino01.model.Genre;

public class TestClass {
	
	@Test
	public void testContainsGenre() {
		Film film = new Film();
		Genre genre = new Genre();
		genre.setName("Ужасы");
		Genre g = new Genre();
		g.setName("Боевик");
		Genre g1 = new Genre();
		g1.setName("Комедия");
		List<Genre> genres = new ArrayList<>();
		genres.add(genre);
		genres.add(g);
		film.setGenres(genres);
		
	//	Assert.assertEquals(true, film.containsGenre(g));
	//	Assert.assertEquals(true, film.containsGenre(g));
		
	}

}
