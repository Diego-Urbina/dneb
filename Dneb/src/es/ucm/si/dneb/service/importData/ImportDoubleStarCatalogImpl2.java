package es.ucm.si.dneb.service.importData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ucm.si.dneb.domain.DoubleStarCatalog;
import es.ucm.si.dneb.service.math.CoordinateConverter;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;

@Service("importDoubleStarCatalog")
public class ImportDoubleStarCatalogImpl2 implements ImportDoubleStarCatalog {

	@PersistenceContext
	EntityManager manager;

	private static final Log LOG = LogFactory
			.getLog(ImportDoubleStarCatalogImpl.class);

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCatalog() {

		manager.createQuery("delete from DoubleStarCatalog").executeUpdate();
		LOG.info("Se han elmininado todos los datos del catálogo");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ArrayList<String> loadCatalogToString(String fileName) {

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void loadCatalog(String path) {
		if ((path == null) || (path == ""))
			throw new IllegalArgumentException();

		String line;
		// ArrayList<String> file = new ArrayList<String> ();

		try {
			BufferedReader in = new BufferedReader(new FileReader(path));

			if (!in.ready())
				throw new IOException();

			int i = 0;

			while ((line = in.readLine()) != null) {
				// file.add(line);
				String doubleStar = line;

				DoubleStarCatalog doubleStarCatalog = new DoubleStarCatalog();

				doubleStarCatalog.setCoordinates(doubleStar.substring(0, 10));
				doubleStarCatalog.setDiscovererAndNumber(doubleStar.substring(
						10, 17));
				doubleStarCatalog.setComponents(doubleStar.substring(17, 22));
				try {
					doubleStarCatalog.setFirstObservation(Date
							.valueOf(doubleStar.substring(23, 27).trim()
									+ "-01-01"));
				} catch (Exception e) {
					doubleStarCatalog.setFirstObservation(null);
				}
				try {
					doubleStarCatalog.setLastObservation(Date
							.valueOf(doubleStar.substring(28, 32).trim()
									+ "-01-01"));

				} catch (Exception e) {
					doubleStarCatalog.setLastObservation(null);
				}

				doubleStarCatalog.setNumObservations(Integer.decode(doubleStar
						.substring(33, 37).trim()));
				try {
					doubleStarCatalog.setFirstPosAngle(Double
							.parseDouble(doubleStar.substring(38, 41).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setFirstPosAngle(null);
				}

				try {
					doubleStarCatalog.setLastPosAnges(Double
							.parseDouble(doubleStar.substring(42, 45).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setLastPosAnges(null);
				}

				try {
					doubleStarCatalog.setFirstSeparation(Double
							.parseDouble(doubleStar.substring(46, 51).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setFirstSeparation(null);
				}

				try {
					doubleStarCatalog.setLastSeparation(Double
							.parseDouble(doubleStar.substring(52, 57).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setLastSeparation(null);
				}

				try {
					doubleStarCatalog.setFirstStarMagnitude(Double
							.parseDouble(doubleStar.substring(58, 63).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setFirstStarMagnitude(null);
				}

				try {
					doubleStarCatalog.setSecondStarMagnitude(Double
							.parseDouble(doubleStar.substring(64, 69).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setSecondStarMagnitude(null);
				}

				doubleStarCatalog.setSpectralType(doubleStar.substring(70, 79));

				try {
					doubleStarCatalog.setPrimaryProperMotionRa(Double
							.parseDouble(doubleStar.substring(80, 84).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setPrimaryProperMotionRa(null);
				}

				try {
					doubleStarCatalog.setPrimaryProperMotionDec(Double
							.parseDouble(doubleStar.substring(84, 88).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setPrimaryProperMotionDec(null);
				}

				try {
					doubleStarCatalog.setSecondaryProperMotionRa(Double
							.parseDouble(doubleStar.substring(89, 93).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setSecondaryProperMotionRa(null);
				}

				try {
					doubleStarCatalog.setSecondaryProperMotionDec(Double
							.parseDouble(doubleStar.substring(93, 97).trim()));
				} catch (Exception e) {
					doubleStarCatalog.setSecondaryProperMotionDec(null);
				}

				doubleStarCatalog.setDurchmusterungNumber(doubleStar.substring(
						98, 106));

				doubleStarCatalog.setNotes(doubleStar.substring(107, 111));

				String a2000rchsec = doubleStar.substring(112, 130);

				if (a2000rchsec.equals("      .         . ")) {
					doubleStarCatalog.setArcsecondCoordinates2000(null);
				} else {

					// TODO
					doubleStarCatalog.setArcsecondCoordinates2000(a2000rchsec);

					SexagesimalCoordinate sc = new SexagesimalCoordinate();

					sc.setArh(Integer.parseInt(doubleStarCatalog
							.getArcsecondCoordinates2000().substring(0, 2)));
					sc.setArmin(Integer.parseInt(doubleStarCatalog
							.getArcsecondCoordinates2000().substring(2, 4)));
					sc.setArsec(Double.parseDouble(doubleStarCatalog
							.getArcsecondCoordinates2000().substring(4, 9)));

					if (doubleStarCatalog.getArcsecondCoordinates2000()
							.substring(9, 10).equals("+")) {
						sc.setDech(Integer
								.parseInt(doubleStarCatalog
										.getArcsecondCoordinates2000()
										.substring(10, 12)));
					} else {
						sc.setDech(Integer.parseInt(doubleStarCatalog
								.getArcsecondCoordinates2000()
								.substring(9, 12)));
					}

					sc.setDecmin(Integer.parseInt(doubleStarCatalog
							.getArcsecondCoordinates2000().substring(12, 14)));
					sc.setDecsec(Double.parseDouble(doubleStarCatalog
							.getArcsecondCoordinates2000().substring(14, 18)));

					DecimalCoordinate dc = CoordinateConverter
							.sexagesimalToDecimalConverter(sc);

					doubleStarCatalog.setAscRecGrados(dc.getAr());
					doubleStarCatalog.setDecGrados(dc.getDec());

				}

				/* TODO REALIZAR CONVERSIÓN DE UNIDADES */

				manager.persist(doubleStarCatalog);

				i++;

			}
			in.close();
		} catch (IOException e) {
			LOG.debug(e);

		}

		/*
		 * 
		 * 
		 * ArrayList<String> dsc = this.loadCatalogToString(path);
		 * 
		 * 
		 * 
		 * for(String doubleStar : dsc ){
		 * 
		 * 
		 * //TODO DoubleStarCatalog doubleStarCatalog= new DoubleStarCatalog();
		 * 
		 * doubleStarCatalog.setCoordinates(doubleStar.substring(0, 10));
		 * doubleStarCatalog.setDiscovererAndNumber(doubleStar.substring(10,
		 * 17)); doubleStarCatalog.setComponents(doubleStar.substring(17,22));
		 * try{
		 * doubleStarCatalog.setFirstObservation(Date.valueOf(doubleStar.substring
		 * (23,27).trim()+"-01-01")); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(23-27) : "
		 * + doubleStar); } try{
		 * doubleStarCatalog.setLastObservation(Date.valueOf
		 * (doubleStar.substring(28,32).trim()+"-01-01"));
		 * 
		 * }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(28-32) :" +
		 * doubleStar); }
		 * 
		 * 
		 * 
		 * doubleStarCatalog.setNumObservations(Integer.decode(doubleStar.substring
		 * (33,37).trim())); try{
		 * doubleStarCatalog.setFirstPosAngle(Double.parseDouble
		 * (doubleStar.substring(38,41).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(38-41) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setLastPosAnges(Double.parseDouble(doubleStar.substring
		 * (42,45).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(42-45) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setFirstSeparation(Double.parseDouble(doubleStar
		 * .substring(46,51).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(46-51) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setLastSeparation(Double.parseDouble(doubleStar
		 * .substring(52,57).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(52-57) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setFirstStarMagnitude(Double.parseDouble(doubleStar
		 * .substring(58,63).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(58-63) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setSecondStarMagnitude(Double.parseDouble(doubleStar
		 * .substring(64,69).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(64-69) :" +
		 * doubleStar); }
		 * 
		 * doubleStarCatalog.setSpectralType(doubleStar.substring(70,79));
		 * 
		 * try{
		 * doubleStarCatalog.setPrimaryProperMotionRa(Double.parseDouble(doubleStar
		 * .substring(80,84).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(80-84) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setPrimaryProperMotionDec(Double.parseDouble(doubleStar
		 * .substring(84,88).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(84-88) :" +
		 * doubleStar); }
		 * 
		 * 
		 * try{doubleStarCatalog.setSecondaryProperMotionRa(Double.parseDouble(
		 * doubleStar.substring(89,93).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(89-93) :" +
		 * doubleStar); }
		 * 
		 * try{
		 * doubleStarCatalog.setSecondaryProperMotionDec(Double.parseDouble(
		 * doubleStar.substring(93,97).trim())); }catch(Exception e){
		 * LOG.debug("PROBLEM IMPORTING DATA OF DOUBLE STAR CATALOG:(93-97) :" +
		 * doubleStar); }
		 * 
		 * 
		 * 
		 * 
		 * doubleStarCatalog.setDurchmusterungNumber(doubleStar.substring(98,106)
		 * );
		 * 
		 * doubleStarCatalog.setNotes(doubleStar.substring(107,111));
		 * 
		 * 
		 * 
		 * doubleStarCatalog.setArcsecondCoordinates2000(doubleStar.substring(112
		 * ,130));
		 * 
		 * manager.persist(doubleStarCatalog);
		 * 
		 * 
		 * }
		 */

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCatalog(String filePath) {
		// TODO Auto-generated method stub

	}

	public static Log getLog() {
		return LOG;
	}

}
