//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.01.23 a las 02:00:59 AM CET 
//


package modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nombreCompleto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="afiliacion" type="{}tipoAfiliacion" minOccurs="0"/&gt;
 *         &lt;element name="premio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="paginas" type="{}paginasAsociadas" minOccurs="0"/&gt;
 *         &lt;element name="paginasPremios" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="articulosAutor" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="articulosEditor" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="libros" type="{}libro" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="informacionPersonal" type="{}informacionPersonal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="idAutor" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nombreCompleto",
    "afiliacion",
    "premio",
    "paginas",
    "paginasPremios",
    "articulosAutor",
    "articulosEditor",
    "libros",
    "informacionPersonal"
})
@XmlRootElement(name = "InformacionAutor")
public class InformacionAutor {

    @XmlElement(required = true)
    protected String nombreCompleto;
    protected TipoAfiliacion afiliacion;
    protected List<String> premio;
    protected PaginasAsociadas paginas;
    protected List<String> paginasPremios;
    protected List<String> articulosAutor;
    protected List<String> articulosEditor;
    protected List<Libro> libros;
    protected InformacionPersonal informacionPersonal;
    @XmlAttribute(name = "idAutor", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger idAutor;

    /**
     * Obtiene el valor de la propiedad nombreCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Define el valor de la propiedad nombreCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad afiliacion.
     * 
     * @return
     *     possible object is
     *     {@link TipoAfiliacion }
     *     
     */
    public TipoAfiliacion getAfiliacion() {
        return afiliacion;
    }

    /**
     * Define el valor de la propiedad afiliacion.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAfiliacion }
     *     
     */
    public void setAfiliacion(TipoAfiliacion value) {
        this.afiliacion = value;
    }

    /**
     * Gets the value of the premio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPremio() {
        if (premio == null) {
            premio = new ArrayList<String>();
        }
        return this.premio;
    }

    /**
     * Obtiene el valor de la propiedad paginas.
     * 
     * @return
     *     possible object is
     *     {@link PaginasAsociadas }
     *     
     */
    public PaginasAsociadas getPaginas() {
        return paginas;
    }

    /**
     * Define el valor de la propiedad paginas.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginasAsociadas }
     *     
     */
    public void setPaginas(PaginasAsociadas value) {
        this.paginas = value;
    }

    /**
     * Gets the value of the paginasPremios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paginasPremios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaginasPremios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPaginasPremios() {
        if (paginasPremios == null) {
            paginasPremios = new ArrayList<String>();
        }
        return this.paginasPremios;
    }

    /**
     * Gets the value of the articulosAutor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articulosAutor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticulosAutor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArticulosAutor() {
        if (articulosAutor == null) {
            articulosAutor = new ArrayList<String>();
        }
        return this.articulosAutor;
    }

    /**
     * Gets the value of the articulosEditor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the articulosEditor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArticulosEditor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getArticulosEditor() {
        if (articulosEditor == null) {
            articulosEditor = new ArrayList<String>();
        }
        return this.articulosEditor;
    }

    /**
     * Gets the value of the libros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Libro }
     * 
     * 
     */
    public List<Libro> getLibros() {
        if (libros == null) {
            libros = new ArrayList<Libro>();
        }
        return this.libros;
    }

    /**
     * Obtiene el valor de la propiedad informacionPersonal.
     * 
     * @return
     *     possible object is
     *     {@link InformacionPersonal }
     *     
     */
    public InformacionPersonal getInformacionPersonal() {
        return informacionPersonal;
    }

    /**
     * Define el valor de la propiedad informacionPersonal.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionPersonal }
     *     
     */
    public void setInformacionPersonal(InformacionPersonal value) {
        this.informacionPersonal = value;
    }

    /**
     * Obtiene el valor de la propiedad idAutor.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdAutor() {
        return idAutor;
    }

    /**
     * Define el valor de la propiedad idAutor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdAutor(BigInteger value) {
        this.idAutor = value;
    }

}
