package org.primefaces.examples.moviecollector.service;

import java.util.List;
import java.util.Map;

import org.primefaces.examples.moviecollector.domain.Movie;
import org.primefaces.model.SortOrder;

public interface MovieService {

	public void createNew(Movie movie);
	
	public List<Movie> findAll();
	
	public List<Movie> findByTitle(String title);
	
	public void update(Movie movie);
	
	public void remove(Movie movie);
	
	public Movie findById(Long id);
    
    public List<Movie> loadLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

    public Long getRowCount(Map<String, String> filters);
}
