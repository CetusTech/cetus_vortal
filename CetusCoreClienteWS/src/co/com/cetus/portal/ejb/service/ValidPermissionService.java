
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validPermissionService complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validPermissionService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://service.ejb.portal.cetus.com.co/}validPermServiceRequestDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validPermissionService", propOrder = {
    "validPermServiceRequestDTO"
})
public class ValidPermissionService {

    @XmlElement(namespace = "http://service.ejb.portal.cetus.com.co/")
    protected ValidPermServiceRequestDTO validPermServiceRequestDTO;

    /**
     * Obtiene el valor de la propiedad validPermServiceRequestDTO.
     * 
     * @return
     *     possible object is
     *     {@link ValidPermServiceRequestDTO }
     *     
     */
    public ValidPermServiceRequestDTO getValidPermServiceRequestDTO() {
        return validPermServiceRequestDTO;
    }

    /**
     * Define el valor de la propiedad validPermServiceRequestDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidPermServiceRequestDTO }
     *     
     */
    public void setValidPermServiceRequestDTO(ValidPermServiceRequestDTO value) {
        this.validPermServiceRequestDTO = value;
    }

}
