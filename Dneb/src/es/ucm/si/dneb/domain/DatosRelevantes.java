package es.ucm.si.dneb.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "infoRelevante"
})
@XmlRootElement(name = "datosRelevantes")
public class DatosRelevantes {

	 @XmlElement(required = true)
	    protected List<InformacionRelevante> infoRelevante;

	public List<InformacionRelevante> getInfoRelevante() {
		if(infoRelevante!=null){
			return infoRelevante;
		}else{
			return new ArrayList<InformacionRelevante>();
		}
	}

	public void setInfoRelevante(List<InformacionRelevante> infoRelevante) {
		this.infoRelevante = infoRelevante;
	}
	 
}

