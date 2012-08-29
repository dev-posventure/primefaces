/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.examples.moviecollector.domain;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.primefaces.examples.moviecollector.service.MovieService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author levent
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MyLazyDataModel extends LazyDataModel<Movie> {

    
    private static final long serialVersionUID = 9092164453564928208L;

	private MovieService service;
    
    private Map<String, String> filters;


    @Override
    public int getRowCount() {
        return service.getRowCount(filters).intValue();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder, @SuppressWarnings("rawtypes") Map filters) {
        this.filters = filters;
        return getService().loadLazy(first, pageSize, sortField, sortOrder, filters);
    }
    
    public MyLazyDataModel( MovieService service){
        setService(service);
    }
    
    @Override
    public Object getRowKey(Movie m) {
        return m.getId();
    }
    
    @Override
    public Movie getRowData(String rowKey) {
        return service.findById(Long.parseLong(rowKey));
    }
}
