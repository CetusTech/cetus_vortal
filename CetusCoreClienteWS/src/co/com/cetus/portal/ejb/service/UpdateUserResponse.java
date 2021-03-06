
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para updateUserResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="updateUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseWSDTO" type="{http://service.ejb.portal.cetus.com.co/}responseWSDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateUserResponse", propOrder = {
    "responseWSDTO"
})
public class UpdateUserResponse {

    protected ResponseWSDTO responseWSDTO;

    /**
     * Obtiene el valor de la propiedad responseWSDTO.
     * 
     * @return
     *     possible object is
     *     {@link ResponseWSDTO }
     *     
     */
    public ResponseWSDTO getResponseWSDTO() {
        return responseWSDTO;
    }

    /**
     * Define el valor de la propiedad responseWSDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseWSDTO }
     *     
     */
    public void setResponseWSDTO(ResponseWSDTO value) {
        this.responseWSDTO = value;
    }

}
