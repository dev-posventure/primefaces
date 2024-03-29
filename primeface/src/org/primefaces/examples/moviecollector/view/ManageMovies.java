package org.primefaces.examples.moviecollector.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.ActionEvent;

import lombok.Data;

import org.primefaces.examples.moviecollector.domain.Movie;
import org.primefaces.examples.moviecollector.domain.MyLazyDataModel;
import org.primefaces.examples.moviecollector.service.MovieService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("manageMovies")
@Scope("session")
@Data
public class ManageMovies implements Serializable {

	private static final long serialVersionUID = -9152174745606043512L;

	private LazyDataModel<Movie> movies;
	private MovieService movieService;
	private String title;
	private Movie[] selectedMovies;

	@Autowired
	public ManageMovies(MovieService movieService) {
		this.movieService = movieService;
		movies = new MyLazyDataModel(movieService);
	}

	public List<String> getMoviesByTitle(String title) {
		List<Movie> foundMovies = movieService.findByTitle(title);
		List<String> titles = new ArrayList<String>();

		for (Movie m : foundMovies) {
			titles.add(m.getTitle());
		}

		return titles;
	}

    public void search(ActionEvent actionEvent) {
		movies.load(0, 10, null, SortOrder.UNSORTED, new HashMap<String, String>() {
			private static final long serialVersionUID = 190024534693853104L;
		{
			put("title", getTitle());
		}});
	}
}