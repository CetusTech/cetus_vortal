package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the tb_permission database table.
 * 
 */
@Entity
@Table(name="TB_PERMISSION")
@NamedQueries ( {
  @NamedQuery ( name = "Permission.validPermissionService", query = "select p from Permission p where p.tbUserW.user = :user and " +
                                  "p.tbUserW.password = :password and p.tbService.name = :service and p.tbUserW.status = 1 " +
                                  "and p.tbService.status = 1 and p.tbService.tbAplicacion.id = :application" ) } )
public class Permission implements Serializable {
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

	@Column(name="USER_CREATION")
	private String userCreation;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	//bi-directional many-to-one association to UserW
	@ManyToOne
	@JoinColumn(name="USER_WS_ID")
	private UserW tbUserW;

	//bi-directional many-to-one association to Service
	@ManyToOne
	@JoinColumn(name="SERVICE_ID")
	private Service tbService;

	public Permission() {
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

	public UserW getTbUserW() {
		return this.tbUserW;
	}

	public void setTbUserW(UserW tbUserW) {
		this.tbUserW = tbUserW;
	}

	public Service getTbService() {
		return this.tbService;
	}

	public void setTbService(Service tbService) {
		this.tbService = tbService;
	}

}