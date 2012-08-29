package org.primefaces.examples.moviecollector.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import lombok.Data;

import org.primefaces.examples.moviecollector.domain.Movie;
import org.primefaces.examples.moviecollector.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("createMovie")
@Scope("request")
@Data
public class CreateMovie implements Serializable{
 
    private static final long serialVersionUID = 1L;
    
	private Movie movie = new Movie();
	
	public CreateMovie() {}
	
	private MovieService movieService;
	
	@Autowired
	public CreateMovie(MovieService movieService) {
		this.movieService = movieService;
	}
	public void save(ActionEvent actionEvent) {
		movieService.createNew(movie);
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Movie is saved");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		movie = new Movie();
	}
}