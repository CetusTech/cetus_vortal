
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para updateUser complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="updateUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateUserRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}updateUserRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateUser", propOrder = {
    "updateUserRequestDTO"
})
public class UpdateUser {

    protected UpdateUserRequestDTO updateUserRequestDTO;

    /**
     * Obtiene el valor de la propiedad updateUserRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link UpdateUserRequestDTO }
     *     
     */
    public UpdateUserRequestDTO getUpdateUserRequestDTO() {
        return updateUserRequestDTO;
    }

    /**
     * Define el valor de la propiedad updateUserRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateUserRequestDTO }
     *     
     */
    public void setUpdateUserRequestDTO(UpdateUserRequestDTO value) {
        this.updateUserRequestDTO = value;
    }

}
