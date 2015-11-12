
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findRolByApplicationResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findRolByApplicationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findRolsByApplicationResponseDTO" type="{http://service.ejb.portal.cetus.com.co/}findRolsByApplicationResponseDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findRolByApplicationResponse", propOrder = {
    "findRolsByApplicationResponseDTO"
})
public class FindRolByApplicationResponse {

    protected FindRolsByApplicationResponseDTO findRolsByApplicationResponseDTO;

    /**
     * Obtiene el valor de la propiedad findRolsByApplicationResponseDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindRolsByApplicationResponseDTO }
     *     
     */
    public FindRolsByApplicationResponseDTO getFindRolsByApplicationResponseDTO() {
        return findRolsByApplicationResponseDTO;
    }

    /**
     * Define el valor de la propiedad findRolsByApplicationResponseDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindRolsByApplicationResponseDTO }
     *     
     */
    public void setFindRolsByApplicationResponseDTO(FindRolsByApplicationResponseDTO value) {
        this.findRolsByApplicationResponseDTO = value;
    }

}
