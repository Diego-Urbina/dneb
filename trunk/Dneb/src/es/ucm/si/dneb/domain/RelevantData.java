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
public class RelevantData {

	 @XmlElement(required = true)
	    protected List<RelevantInformation> infoRelevante;

	public List<RelevantInformation> getInfoRelevante() {
		if(infoRelevante!=null){
			return infoRelevante;
		}else{
			return new ArrayList<RelevantInformation>();
		}
	}

	public void setInfoRelevante(List<RelevantInformation> infoRelevante) {
		this.infoRelevante = infoRelevante;
	}
	 
}

