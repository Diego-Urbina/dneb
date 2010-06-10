package es.ucm.si.dneb.service.exportData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import es.ucm.si.dneb.domain.RelevantData;
import es.ucm.si.dneb.domain.RelevantInformation;

@Service("serviceExportData")
public class ServiceExportDataImpl implements ServiceExportData {
	
	@PersistenceContext
	EntityManager manager;
	
	private RelevantData datosRelevantes = null;
	private JAXBContext jaxbContext = null;
	private Unmarshaller unmarshaller = null;
	private Marshaller marshaller = null;
	
	
	private static final Log LOG = LogFactory
	.getLog(ServiceExportDataImpl.class);
	

	@Override
	public void exportRelevantData(String filename,String path){
		try {
			jaxbContext = JAXBContext.newInstance("es.ucm.si.dneb.xml");
			marshaller = jaxbContext.createMarshaller();
		} catch (JAXBException e) {
			LOG.error("PROBLEM WITH JAXB CONTEXT: "+e+e.getStackTrace());
		}
		
		
		List<RelevantInformation> infRels=manager.createQuery("select ir from RelevantInformation ir").getResultList();
		
		RelevantData datRel= new RelevantData();
		
		datRel.setInfoRelevante(infRels);
		
		try {
			File file = new File(path
					+ "/"+filename+".xml");

			if (!file.exists()) {
				try {
					createNewXMLFile(file);

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
			FileOutputStream fos = null;

			fos = new FileOutputStream(file);

			marshaller.marshal(datRel, fos);
		} catch (JAXBException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	

	private void createNewXMLFile(File file) throws IOException {
		file.createNewFile();
		FileWriter writer = new FileWriter(file);

		writer
				.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><registroActividadesFormativas></registroActividadesFormativas>");
		writer.close();
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setDatosRelevantes(RelevantData datosRelevantes) {
		this.datosRelevantes = datosRelevantes;
	}

	public RelevantData getDatosRelevantes() {
		return datosRelevantes;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

}
