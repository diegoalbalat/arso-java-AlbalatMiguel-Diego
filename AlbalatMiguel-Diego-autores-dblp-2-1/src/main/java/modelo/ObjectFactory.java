//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.01.24 a las 02:13:49 PM CET 
//

package modelo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import modelo.atom.DateTimeType;
import modelo.atom.EntryType;
import modelo.atom.FeedType;
import modelo.atom.IdType;
import modelo.atom.LinkType;
import modelo.atom.PersonType;
import modelo.atom.TextType;
import modelo.atom.UriType;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.w3._2005.atom package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Feed_QNAME = new QName("http://www.w3.org/2005/Atom", "feed");
	private final static QName _Entry_QNAME = new QName("http://www.w3.org/2005/Atom", "entry");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: org.w3._2005.atom
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link FeedType }
	 * 
	 */
	public FeedType createFeedType() {
		return new FeedType();
	}

	/**
	 * Create an instance of {@link EntryType }
	 * 
	 */
	public EntryType createEntryType() {
		return new EntryType();
	}

	/**
	 * Create an instance of {@link TextType }
	 * 
	 */
	public TextType createTextType() {
		return new TextType();
	}

	/**
	 * Create an instance of {@link PersonType }
	 * 
	 */
	public PersonType createPersonType() {
		return new PersonType();
	}

	/**
	 * Create an instance of {@link IdType }
	 * 
	 */
	public IdType createIdType() {
		return new IdType();
	}

	/**
	 * Create an instance of {@link LinkType }
	 * 
	 */
	public LinkType createLinkType() {
		return new LinkType();
	}

	/**
	 * Create an instance of {@link UriType }
	 * 
	 */
	public UriType createUriType() {
		return new UriType();
	}

	/**
	 * Create an instance of {@link DateTimeType }
	 * 
	 */
	public DateTimeType createDateTimeType() {
		return new DateTimeType();
	}

	/**
	 * Create an instance of {@link Autores }
	 * 
	 */
	public Autores createAutores() {
		return new Autores();
	}

	/**
	 * Create an instance of {@link Autor }
	 * 
	 */
	public Autor createAutor() {
		return new Autor();
	}

	/**
	 * Create an instance of {@link InformacionAutor }
	 * 
	 */
	public InformacionAutor createInformacionAutor() {
		return new InformacionAutor();
	}

	/**
	 * Create an instance of {@link TipoAfiliacion }
	 * 
	 */
	public TipoAfiliacion createTipoAfiliacion() {
		return new TipoAfiliacion();
	}

	/**
	 * Create an instance of {@link PaginasAsociadas }
	 * 
	 */
	public PaginasAsociadas createPaginasAsociadas() {
		return new PaginasAsociadas();
	}

	/**
	 * Create an instance of {@link Libro }
	 * 
	 */
	public Libro createLibro() {
		return new Libro();
	}

	/**
	 * Create an instance of {@link InformacionPersonal }
	 * 
	 */
	public InformacionPersonal createInformacionPersonal() {
		return new InformacionPersonal();
	}

	/**
	 * Create an instance of {@link Favoritos }
	 * 
	 */
	public Favoritos createFavoritos() {
		return new Favoritos();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FeedType }{@code >}
	 * 
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link FeedType
	 *         }{@code >}
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "feed")
	public JAXBElement<FeedType> createFeed(FeedType value) {
		return new JAXBElement<FeedType>(_Feed_QNAME, FeedType.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link EntryType
	 * }{@code >}
	 * 
	 * @param value Java instance representing xml element's value.
	 * @return the new instance of {@link JAXBElement }{@code <}{@link EntryType
	 *         }{@code >}
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/2005/Atom", name = "entry")
	public JAXBElement<EntryType> createEntry(EntryType value) {
		return new JAXBElement<EntryType>(_Entry_QNAME, EntryType.class, null, value);
	}

}
