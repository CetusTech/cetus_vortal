
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findRolByApplication complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findRolByApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findRolsByApplicationRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}findRolsByApplicationRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findRolByApplication", propOrder = {
    "findRolsByApplicationRequestDTO"
})
public class FindRolByApplication {

    protected FindRolsByApplicationRequestDTO findRolsByApplicationRequestDTO;

    /**
     * Obtiene el valor de la propiedad findRolsByApplicationRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindRolsByApplicationRequestDTO }
     *     
     */
    public FindRolsByApplicationRequestDTO getFindRolsByApplicationRequestDTO() {
        return findRolsByApplicationRequestDTO;
    }

    /**
     * Define el valor de la propiedad findRolsByApplicationRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindRolsByApplicationRequestDTO }
     *     
     */
    public void setFindRolsByApplicationRequestDTO(FindRolsByApplicationRequestDTO value) {
        this.findRolsByApplicationRequestDTO = value;
    }

}
