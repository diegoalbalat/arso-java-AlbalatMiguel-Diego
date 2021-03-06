//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.01.24 a las 02:13:49 PM CET 
//


package modelo.atom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  The Atom feed construct is defined in section
 * 				4.1.1 of the format spec.
 * 			
 * 
 * <p>Clase Java para feedType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="feedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="3"&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2005/Atom}idType"/&gt;
 *         &lt;element name="link" type="{http://www.w3.org/2005/Atom}linkType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2005/Atom}textType"/&gt;
 *         &lt;element name="updated" type="{http://www.w3.org/2005/Atom}dateTimeType"/&gt;
 *         &lt;element name="entry" type="{http://www.w3.org/2005/Atom}entryType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="feed")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "feedType", propOrder = {
    "idOrLinkOrTitle"
})
public class FeedType {

    @XmlElements({
        @XmlElement(name = "id", type = IdType.class),
        @XmlElement(name = "link", type = LinkType.class),
        @XmlElement(name = "title", type = TextType.class),
        @XmlElement(name = "updated", type = DateTimeType.class),
        @XmlElement(name = "entry", type = EntryType.class)
    })
    protected List<Object> idOrLinkOrTitle;

    /**
     * Gets the value of the idOrLinkOrTitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idOrLinkOrTitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdOrLinkOrTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdType }
     * {@link LinkType }
     * {@link TextType }
     * {@link DateTimeType }
     * {@link EntryType }
     * 
     * 
     */
    public List<Object> getIdOrLinkOrTitle() {
        if (idOrLinkOrTitle == null) {
            idOrLinkOrTitle = new ArrayList<Object>();
        }
        return this.idOrLinkOrTitle;
    }

}
