//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.01.23 a las 12:39:51 PM CET 
//


package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoAfiliacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoAfiliacion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="afiliacionPrimaria" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="afiliacionSecundaria" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoAfiliacion", propOrder = {
    "afiliacionPrimaria",
    "afiliacionSecundaria"
})
public class TipoAfiliacion {

    @XmlElement(required = true)
    protected String afiliacionPrimaria;
    protected List<String> afiliacionSecundaria;

    /**
     * Obtiene el valor de la propiedad afiliacionPrimaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAfiliacionPrimaria() {
        return afiliacionPrimaria;
    }

    /**
     * Define el valor de la propiedad afiliacionPrimaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAfiliacionPrimaria(String value) {
        this.afiliacionPrimaria = value;
    }

    /**
     * Gets the value of the afiliacionSecundaria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the afiliacionSecundaria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAfiliacionSecundaria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAfiliacionSecundaria() {
        if (afiliacionSecundaria == null) {
            afiliacionSecundaria = new ArrayList<String>();
        }
        return this.afiliacionSecundaria;
    }

}
