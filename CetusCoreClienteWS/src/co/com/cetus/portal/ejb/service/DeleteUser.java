
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para deleteUser complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="deleteUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteUserRequestDTO" type="{http://service.ejb.portal.cetus.com.co/}deleteUserRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteUser", propOrder = {
    "deleteUserRequestDTO"
})
public class DeleteUser {

    protected DeleteUserRequestDTO deleteUserRequestDTO;

    /**
     * Obtiene el valor de la propiedad deleteUserRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link DeleteUserRequestDTO }
     *     
     */
    public DeleteUserRequestDTO getDeleteUserRequestDTO() {
        return deleteUserRequestDTO;
    }

    /**
     * Define el valor de la propiedad deleteUserRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteUserRequestDTO }
     *     
     */
    public void setDeleteUserRequestDTO(DeleteUserRequestDTO value) {
        this.deleteUserRequestDTO = value;
    }

}
