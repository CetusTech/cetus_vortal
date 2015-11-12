
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findRolByLogin complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findRolByLogin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findRolsByLoginRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}findRolsByLoginRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findRolByLogin", propOrder = {
    "findRolsByLoginRequestDTO"
})
public class FindRolByLogin {

    protected FindRolsByLoginRequestDTO findRolsByLoginRequestDTO;

    /**
     * Obtiene el valor de la propiedad findRolsByLoginRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindRolsByLoginRequestDTO }
     *     
     */
    public FindRolsByLoginRequestDTO getFindRolsByLoginRequestDTO() {
        return findRolsByLoginRequestDTO;
    }

    /**
     * Define el valor de la propiedad findRolsByLoginRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindRolsByLoginRequestDTO }
     *     
     */
    public void setFindRolsByLoginRequestDTO(FindRolsByLoginRequestDTO value) {
        this.findRolsByLoginRequestDTO = value;
    }

}
