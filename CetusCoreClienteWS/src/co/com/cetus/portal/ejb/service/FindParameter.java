
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findParameter complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findParameterRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}findParameterRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findParameter", propOrder = {
    "findParameterRequestDTO"
})
public class FindParameter {

    protected FindParameterRequestDTO findParameterRequestDTO;

    /**
     * Obtiene el valor de la propiedad findParameterRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindParameterRequestDTO }
     *     
     */
    public FindParameterRequestDTO getFindParameterRequestDTO() {
        return findParameterRequestDTO;
    }

    /**
     * Define el valor de la propiedad findParameterRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindParameterRequestDTO }
     *     
     */
    public void setFindParameterRequestDTO(FindParameterRequestDTO value) {
        this.findParameterRequestDTO = value;
    }

}
