
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findRolByLoginResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findRolByLoginResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findRolsByLoginResponseDTO" type="{http://service.ejb.portal.cetus.com.co/}findRolsByLoginResponseDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findRolByLoginResponse", propOrder = {
    "findRolsByLoginResponseDTO"
})
public class FindRolByLoginResponse {

    protected FindRolsByLoginResponseDTO findRolsByLoginResponseDTO;

    /**
     * Obtiene el valor de la propiedad findRolsByLoginResponseDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindRolsByLoginResponseDTO }
     *     
     */
    public FindRolsByLoginResponseDTO getFindRolsByLoginResponseDTO() {
        return findRolsByLoginResponseDTO;
    }

    /**
     * Define el valor de la propiedad findRolsByLoginResponseDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindRolsByLoginResponseDTO }
     *     
     */
    public void setFindRolsByLoginResponseDTO(FindRolsByLoginResponseDTO value) {
        this.findRolsByLoginResponseDTO = value;
    }

}
