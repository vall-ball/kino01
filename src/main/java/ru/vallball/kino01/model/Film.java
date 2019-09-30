package ru.vallball.kino01.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "films")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 100)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres;

	private String picture;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Long getId() {
		return id;
	}

	public boolean containsGenre(Genre genre) {
		for (Genre g : this.genres) {
			if (g.equals(genre))
				return true;
		}
		return false;
	}

}
