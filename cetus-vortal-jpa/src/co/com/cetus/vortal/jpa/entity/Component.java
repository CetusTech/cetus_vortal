package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_component database table.
 * 
 */
@Entity
@Table(name="TB_COMPONENT")

@NamedQueries ( { @NamedQuery ( name = "Component.findAllComponentByApplication", query = "select c from Component c where c.tbAplicacion.id = :idApp" ) } )
public class Component implements Serializable {
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

	private String name;

	@Column(name="USER_CREATION")
	private String userCreation;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	//bi-directional many-to-one association to Aplicacion
	@ManyToOne
	@JoinColumn(name="APLICACION_ID")
	private Aplicacion tbAplicacion;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="tbComponent")
	private List<Parametro> tbParametros;

	public Component() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Aplicacion getTbAplicacion() {
		return this.tbAplicacion;
	}

	public void setTbAplicacion(Aplicacion tbAplicacion) {
		this.tbAplicacion = tbAplicacion;
	}

	public List<Parametro> getTbParametros() {
		return this.tbParametros;
	}

	public void setTbParametros(List<Parametro> tbParametros) {
		this.tbParametros = tbParametros;
	}

	public Parametro addTbParametro(Parametro tbParametro) {
		getTbParametros().add(tbParametro);
		tbParametro.setTbComponent(this);

		return tbParametro;
	}

	public Parametro removeTbParametro(Parametro tbParametro) {
		getTbParametros().remove(tbParametro);
		tbParametro.setTbComponent(null);

		return tbParametro;
	}

}