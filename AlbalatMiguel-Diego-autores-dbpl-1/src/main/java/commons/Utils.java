package commons;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class Utils {
	
    public static Document convertStringToXMLDocument(String xmlString) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
	public static XMLGregorianCalendar createFecha(Date fecha) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		XMLGregorianCalendar fechaXML = null;

		try {
			fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		fechaXML.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		fechaXML.setMonth(calendar.get(Calendar.MONTH) + 1);
		fechaXML.setYear(calendar.get(Calendar.YEAR));

		return fechaXML;
	}

	
	public static Date dateFromString(String fechaString) {
		
		DateFormat formateador = new SimpleDateFormat();
		
		try {
			switch (fechaString.length()) {
			case 4:
				formateador = new SimpleDateFormat("yyyy");
				break;
			case 7:
				formateador = new SimpleDateFormat("yyyy-MM");
				break;
			default:
				formateador = new SimpleDateFormat("yyyy-MM-dd");
				break;
			}
			return formateador.parse(fechaString);
		} 
		catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String createId() {	
		Random r = new Random();
		Integer id = r.nextInt((99999 - 0) + 1) + 0;
		return id.toString();
	}
}
