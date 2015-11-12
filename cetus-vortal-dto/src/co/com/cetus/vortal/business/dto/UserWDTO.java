package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Class UserWDTO.
 */
public class UserWDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private Date dateCreation;

	private Date dateUpdate;

	private String description;

	private String password;

	private int status;

	private String user;

	private String userCreation;

	private String userUpdate;

	private List<PermissionDTO> tbPermissions;

	public UserWDTO() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public List<PermissionDTO> getTbPermissions() {
		return this.tbPermissions;
	}

	public void setTbPermissions(List<PermissionDTO> tbPermissions) {
		this.tbPermissions = tbPermissions;
	}

	public PermissionDTO addTbPermission(PermissionDTO tbPermission) {
		getTbPermissions().add(tbPermission);
		tbPermission.setTbUserW(this);

		return tbPermission;
	}

	public PermissionDTO removeTbPermission(PermissionDTO tbPermission) {
		getTbPermissions().remove(tbPermission);
		tbPermission.setTbUserW(null);

		return tbPermission;
	}

}