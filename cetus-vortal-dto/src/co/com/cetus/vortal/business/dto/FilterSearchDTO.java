package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;


public class FilterSearchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Date dateCreation;
	private String filter;
	private int generalSearch;
	private String label;
	private String userCreation;
	

	public FilterSearchDTO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}


	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUserCreation() {
		return this.userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

  public int getGeneralSearch () {
    return generalSearch;
  }

  public void setGeneralSearch ( int generalSearch ) {
    this.generalSearch = generalSearch;
  }

	
	
}