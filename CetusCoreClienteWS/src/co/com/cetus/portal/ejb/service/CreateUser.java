
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para createUser complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="createUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createUserRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}createUserRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUser", propOrder = {
    "createUserRequestDTO"
})
public class CreateUser {

    protected CreateUserRequestDTO createUserRequestDTO;

    /**
     * Obtiene el valor de la propiedad createUserRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link CreateUserRequestDTO }
     *     
     */
    public CreateUserRequestDTO getCreateUserRequestDTO() {
        return createUserRequestDTO;
    }

    /**
     * Define el valor de la propiedad createUserRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateUserRequestDTO }
     *     
     */
    public void setCreateUserRequestDTO(CreateUserRequestDTO value) {
        this.createUserRequestDTO = value;
    }

}
