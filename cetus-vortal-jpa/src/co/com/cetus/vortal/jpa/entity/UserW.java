package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_user_ws database table.
 * 
 */
@Entity
@Table(name="TB_USER_WS")
public class UserW implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATION")
	private Date dateCreation;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_UPDATE")
	private Date dateUpdate;

	private String description;

	private String password;

	private int status;

	private String user;

	@Column(name="USER_CREATION")
	private String userCreation;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	//bi-directional many-to-one association to Permission
	@OneToMany(mappedBy="tbUserW")
	private List<Permission> tbPermissions;

	public UserW() {
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

	public List<Permission> getTbPermissions() {
		return this.tbPermissions;
	}

	public void setTbPermissions(List<Permission> tbPermissions) {
		this.tbPermissions = tbPermissions;
	}

	public Permission addTbPermission(Permission tbPermission) {
		getTbPermissions().add(tbPermission);
		tbPermission.setTbUserW(this);

		return tbPermission;
	}

	public Permission removeTbPermission(Permission tbPermission) {
		getTbPermissions().remove(tbPermission);
		tbPermission.setTbUserW(null);

		return tbPermission;
	}

}