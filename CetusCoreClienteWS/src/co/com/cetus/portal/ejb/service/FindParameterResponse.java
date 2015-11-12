
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para findParameterResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="findParameterResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="findParameterResponseDTO" type="{http://service.ejb.portal.cetus.com.co/}findParameterResponseDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findParameterResponse", propOrder = {
    "findParameterResponseDTO"
})
public class FindParameterResponse {

    protected FindParameterResponseDTO findParameterResponseDTO;

    /**
     * Obtiene el valor de la propiedad findParameterResponseDTO.
     * 
     * @return
     *     possible object is
     *     {@link FindParameterResponseDTO }
     *     
     */
    public FindParameterResponseDTO getFindParameterResponseDTO() {
        return findParameterResponseDTO;
    }

    /**
     * Define el valor de la propiedad findParameterResponseDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link FindParameterResponseDTO }
     *     
     */
    public void setFindParameterResponseDTO(FindParameterResponseDTO value) {
        this.findParameterResponseDTO = value;
    }

}
