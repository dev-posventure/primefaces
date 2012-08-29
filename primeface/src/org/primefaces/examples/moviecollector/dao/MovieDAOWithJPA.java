package org.primefaces.examples.moviecollector.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.examples.moviecollector.domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAOWithJPA implements MovieDAO {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Movie> findByTitle(String title) {
		Query query = entityManager.createQuery("Select m from Movie m where m.title LIKE :title");
		query.setParameter("title", title + "%");

		return query.getResultList();
	}

	public Movie loadById(Long id) {
		return entityManager.find(Movie.class, id);
	}

	public void persist(Movie movie) {
		entityManager.persist(movie);
	}

	public void update(Movie movie) {
		entityManager.merge(movie);
	}

	public void delete(Movie movie) {
		//entityManager.remove(entityManager.merge(movie));
		//entityManager.remove(movie);
		entityManager.createQuery("DELETE FROM Movie m WHERE m.id=:id").setParameter("id", movie.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Movie> loadAll() {
		return entityManager.createQuery("Select m from Movie m").getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
    @SuppressWarnings("unchecked")
    public List<Movie> loadLazy(int first, int pageSize, String sortField, String sortOrder, Map<String, String> filters){
        
        String where = "";
        for (Map.Entry<String, String> e : filters.entrySet())
            where += (where.length()>0 ? " AND " : "") + e.getKey() + " like '" + e.getValue() + "%'";
            
        Query lazy = entityManager.createQuery("Select m from Movie m" + (where.length() > 0 ? " where " +  where : "") + (sortField != null && sortOrder != null ? " order by " + sortField + " " + sortOrder : "") );
        lazy.setFirstResult(first);
        lazy.setMaxResults(pageSize);
        
        return lazy.getResultList();
    }

    @Override
    public Long getRowCount(Map<String, String> filters) {
        String where = "";
        if(filters != null) {
            for (Map.Entry<String, String> e : filters.entrySet()) {
                where += (where.length()>0 ? " AND " : "") + e.getKey() + " like '" + e.getValue() + "%'";
            } 
        }
        
        return (Long)this.entityManager.createQuery("select count(*) from Movie m" + (where.length() > 0 ? " where " +  where : "")).getSingleResult();
    }
}