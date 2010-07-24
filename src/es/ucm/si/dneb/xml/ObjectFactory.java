package es.ucm.si.dneb.xml;


import java.util.Date;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import es.ucm.si.dneb.domain.DatosRelevantes;
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.domain.InformacionRelevante;
import es.ucm.si.dneb.domain.TipoInformacionRelevante;



	@XmlRegistry
	public class ObjectFactory {

	    private final static QName _Description_QNAME = new QName("", "description");
	    private final static QName _Fecha_QNAME = new QName("", "fecha");
	    private final static QName _Id_QNAME = new QName("", "id");
	    private final static QName _Alias_QNAME = new QName("", "alias");
	   
	    private final static QName _IdDescarga_QNAME = new QName("","idDescarga");
	    private final static QName _AscensionRecta_QNAME = new QName("", "ascensionRecta");
	    private final static QName _Declinacion_QNAME = new QName("", "declinacion");

	    /**
	     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: registro
	     *
	     */
	    public ObjectFactory() {
	    }
	    
	    public Imagen createImagen(){
	    	
	    	return new Imagen();
	    }

	    public DatosRelevantes createDatosRelevantes(){
	    	
	    	return new DatosRelevantes();
	    }
	    
	    public InformacionRelevante createInformacionRelevante(){
	    	return new InformacionRelevante();
	    }
	    
	    public TipoInformacionRelevante createTipoInformacionRelevante(){
	    	return new TipoInformacionRelevante();
	    }
	 

	   
	    @XmlElementDecl(namespace = "", name = "description")
	    public JAXBElement<String> createDescription(String value) {
	        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
	    }

	
	    @XmlElementDecl(namespace = "", name = "letra")
	    public JAXBElement<Date> createFecha(Date value) {
	        return new JAXBElement<Date>(_Fecha_QNAME, Date.class, null, value);
	    }

	 
	    @XmlElementDecl(namespace = "", name = "id")
	    public JAXBElement<Long> createId(Long value) {
	        return new JAXBElement<Long>(_Id_QNAME, Long.class, null, value);
	    }


	    @XmlElementDecl(namespace = "", name = "alias")
	    public JAXBElement<String> createAlias(String value) {
	        return new JAXBElement<String>(_Alias_QNAME, String.class, null, value);
	    }


	    @XmlElementDecl(namespace = "", name = "idDescarga")
	    public JAXBElement<Long> createIdDescarga(Long value) {
	        return new JAXBElement<Long>(_IdDescarga_QNAME, Long.class, null, value);
	    }

	   
	    @XmlElementDecl(namespace = "", name = "ascensionRecta")
	    public JAXBElement<String> createAscensionRecta(String value) {
	        return new JAXBElement<String>(_AscensionRecta_QNAME, String.class, null, value);
	    }


	    @XmlElementDecl(namespace = "", name = "declinacion")
	    public JAXBElement<String> createDeclinacion(String value) {
	        return new JAXBElement<String>(_Declinacion_QNAME, String.class, null, value);
	    }

	 

}
