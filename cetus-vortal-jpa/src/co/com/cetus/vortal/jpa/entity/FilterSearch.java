package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the tb_filter_search database table.
 * 
 */
@Entity
@Table ( name = "tb_filter_search" )
@NamedQueries ( {
                  @NamedQuery ( name = "FilterSearch.findAll", query = "SELECT f FROM FilterSearch f" ),
                  @NamedQuery ( name = "FilterSearch.findFilterByGenSearch", query = "SELECT f FROM FilterSearch f where f.generalSearch.id = :idGeneralSearch ORDER BY f.filter" )
})
public class FilterSearch implements Serializable {
  private static final long serialVersionUID = 1L;
                                             
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
                            
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_CREATION" )
  private Date              dateCreation;
                            
  private String            filter;
                            
  @ManyToOne
  @JoinColumn ( name = "GENERAL_SEARCH_ID" )
  private GeneralSearch     generalSearch;
                            
  @Column ( name = "`LABEL`" )
  private String            label;
                            
  @Column ( name = "USER_CREATION" )
  private String            userCreation;
                            
  public FilterSearch () {
  }
  
  public FilterSearch ( String filter ) {
    this.filter = filter;
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
  
  public String getFilter () {
    return this.filter;
  }
  
  public void setFilter ( String filter ) {
    this.filter = filter;
  }
  
  public String getLabel () {
    return this.label;
  }
  
  public void setLabel ( String label ) {
    this.label = label;
  }
  
  public String getUserCreation () {
    return this.userCreation;
  }
  
  public void setUserCreation ( String userCreation ) {
    this.userCreation = userCreation;
  }
  
  public GeneralSearch getGeneralSearch () {
    return generalSearch;
  }
  
  public void setGeneralSearch ( GeneralSearch generalSearch ) {
    this.generalSearch = generalSearch;
  }
  
}