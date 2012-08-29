package org.primefaces.examples.moviecollector.dao;

import java.util.List;
import java.util.Map;

import org.primefaces.examples.moviecollector.domain.Movie;

public interface MovieDAO {

	Movie loadById(Long id);
	
	void persist(Movie movie);
	
	void update(Movie movie);
	
	void delete(Movie movie);
	
	List<Movie> loadAll();
	
	public List<Movie> findByTitle(String title);
    
    public List<Movie> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters);
    
    public Long getRowCount(Map<String, String> filters);
}
