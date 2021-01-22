package commons;

import java.util.Arrays;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class EspacioNombresDBLP implements NamespaceContext {
	private static final String PREFIJO = "dblp";
	private static final String URI = "https://dblp.org/rdf/schema-2020-07-01#";
	

	public String getNamespaceURI(String prefix) {
		return prefix.equals(PREFIJO) ? URI : null;
	}

	public String getPrefix(String namespaceURI) {
		return namespaceURI.equals(URI) ? PREFIJO : null;
	}

	public Iterator<String> getPrefixes(String namespaceURI) {
		return namespaceURI.equals(URI) ? Arrays.asList(PREFIJO).iterator() : null;
	}
}

//public class EspacioNombresDBLP implements NamespaceContext {
//	private static final String PREFIJO = "rdf";
//	private static final String URI = "http://www.w3.org/2000/01/rdf-schema#";
//	
//
//	public String getNamespaceURI(String prefix) {
//		return prefix.equals(PREFIJO) ? URI : null;
//	}
//
//	public String getPrefix(String namespaceURI) {
//		return namespaceURI.equals(URI) ? PREFIJO : null;
//	}
//
//	public Iterator<String> getPrefixes(String namespaceURI) {
//		return namespaceURI.equals(URI) ? Arrays.asList(PREFIJO).iterator() : null;
//	}
//}