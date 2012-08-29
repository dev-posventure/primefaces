package org.primefaces.examples.moviecollector.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Movie implements Serializable {

    private static final long serialVersionUID = -3576149194670418035L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic
	private String title;

	@Basic
	private Integer discs;

	@Enumerated
	private Format format;
	
	private String description;

	public Movie() {
	}

	public Movie(String title, Integer discs) {
		this.title = title;
		this.discs = discs;
	}
}
