package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The Class GeneralSearch.
 *
 * @author Jose David Salcedo M. - Cetus Technology
 * @version cetus-vortal-jpa (25/04/2016)
 */
@Entity
@Table ( name = "TB_GENERAL_SEARCH" )
@NamedQueries ( {
  @NamedQuery ( name = "GeneralSearch.findAll", query = "SELECT g FROM GeneralSearch g" ),
  @NamedQuery ( name = "GeneralSearch.findGenSearchByApp", query = "SELECT g FROM GeneralSearch g where g.appSerId.tbAplicacion.id = :idApp" )
})
public class GeneralSearch implements Serializable {
  private static final long serialVersionUID = 1L;
                                             
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
                            
  @ManyToOne
  @JoinColumn ( name = "APP_SER_ID" )
  private AplicationServlet appSerId;
                            
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_CREATION" )
  private Date              dateCreation;
                            
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_UPDATE" )
  private Date              dateUpdate;
                            
  private String            description;
                            
  @Column ( name = "OPTION_SEARCH" )
  private int               optionSearch;
                            
  private int               status;
                            
  @Column ( name = "USER_CREATION" )
  private String            userCreation;
                            
  @Column ( name = "USER_UPDATE" )
  private String            userUpdate;
  
  @OneToMany(mappedBy="generalSearch")
  private List<FilterSearch> filters;
                            
  public GeneralSearch () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public Date getDateCreation () {
    return this.dateCreation;
  }
  
  public void setDateCreation ( Date dateCreation ) {
    this.dateCreation = dateCreation;
  }
  
  public Date getDateUpdate () {
    return this.dateUpdate;
  }
  
  public void setDateUpdate ( Date dateUpdate ) {
    this.dateUpdate = dateUpdate;
  }
  
  public String getDescription () {
    return this.description;
  }
  
  public void setDescription ( String description ) {
    this.description = description;
  }
  
  public int getStatus () {
    return this.status;
  }
  
  public void setStatus ( int status ) {
    this.status = status;
  }
  
  public String getUserCreation () {
    return this.userCreation;
  }
  
  public void setUserCreation ( String userCreation ) {
    this.userCreation = userCreation;
  }
  
  public String getUserUpdate () {
    return this.userUpdate;
  }
  
  public void setUserUpdate ( String userUpdate ) {
    this.userUpdate = userUpdate;
  }
  
  public AplicationServlet getAppSerId () {
    return appSerId;
  }
  
  public void setAppSerId ( AplicationServlet appSerId ) {
    this.appSerId = appSerId;
  }
  
  public int getOptionSearch () {
    return optionSearch;
  }
  
  public void setOptionSearch ( int optionSearch ) {
    this.optionSearch = optionSearch;
  }

  public List< FilterSearch > getFilters () {
    return filters;
  }

  public void setFilters ( List< FilterSearch > filters ) {
    this.filters = filters;
  }
  
  
  
}