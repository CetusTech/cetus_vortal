package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Class GeneralSearchDTO.
 *
 * @author Jose David Salcedo M. - Cetus Technology
 * @version cetus-vortal-dto (25/04/2016)
 */
public class GeneralSearchDTO implements Serializable {
  private static final long    serialVersionUID = 1L;
                                                
  private int                  id;
  private AplicationServletDTO appSerId;
  private Date                 dateCreation;
  private Date                 dateUpdate;
  private String               description;
  private int                  optionsearch;
  private int                  status;
  private String               userCreation;
  private String               userUpdate;
  private List<FilterSearchDTO> filters;
                               
  public GeneralSearchDTO () {
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
  
  public AplicationServletDTO getAppSerId () {
    return appSerId;
  }
  
  public void setAppSerId ( AplicationServletDTO appSerId ) {
    this.appSerId = appSerId;
  }
  
  public int getOptionsearch () {
    return optionsearch;
  }
  
  public void setOptionsearch ( int optionsearch ) {
    this.optionsearch = optionsearch;
  }

  public List< FilterSearchDTO > getFilters () {
    return filters;
  }

  public void setFilters ( List< FilterSearchDTO > filters ) {
    this.filters = filters;
  }
 
  
  
}