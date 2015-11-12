package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class PermissionDTO.
 */

public class PermissionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Date dateCreation;

	private Date dateUpdate;

	private String userCreation;

	private String userUpdate;

	private UserWDTO tbUserW;

	private ServiceDTO tbService;

	public PermissionDTO() {
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

	public Date getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getUserCreation() {
		return this.userCreation;
	}

	public void setUserCreation(String userCreation) {
		this.userCreation = userCreation;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

  public UserWDTO getTbUserW () {
    return tbUserW;
  }

  public void setTbUserW ( UserWDTO tbUserW ) {
    this.tbUserW = tbUserW;
  }

  public ServiceDTO getTbService () {
    return tbService;
  }

  public void setTbService ( ServiceDTO tbService ) {
    this.tbService = tbService;
  }

	

}