//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.1 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.01.23 a las 02:02:15 AM CET 
//


package modelo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
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

}