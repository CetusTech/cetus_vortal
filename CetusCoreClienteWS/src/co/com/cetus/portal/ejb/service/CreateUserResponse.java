
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createUserResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createUserResponseDTO" type="{http://service.ejb.portal.cetus.com.co/}createUserResponseDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUserResponse", propOrder = {
    "createUserResponseDTO"
})
public class CreateUserResponse {

    protected CreateUserResponseDTO createUserResponseDTO;

    /**
     * Obtiene el valor de la propiedad createUserResponseDTO.
     * 
     * @return
     *     possible object is
     *     {@link CreateUserResponseDTO }
     *     
     */
    public CreateUserResponseDTO getCreateUserResponseDTO() {
        return createUserResponseDTO;
    }

    /**
     * Define el valor de la propiedad createUserResponseDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateUserResponseDTO }
     *     
     */
    public void setCreateUserResponseDTO(CreateUserResponseDTO value) {
        this.createUserResponseDTO = value;
    }

}
