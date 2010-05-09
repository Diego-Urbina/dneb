
package es.ucm.si.dneb.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;
import es.ucm.si.dneb.service.calculoPosicion.ServiceCalculoPosicion;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.image.app.DisplayImageWithRegions;
import es.ucm.si.dneb.service.image.app.ImageRegion;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.service.math.Distance;
import es.ucm.si.dneb.service.math.MathService;
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;
import es.ucm.si.dneb.util.FiltreExtensible;

public class FitsCoordinateViewerPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -7389267318471409502L;
	private static final int porcentajeZoom = 10;
	
	private int scale;
	private boolean calculoDistancia, estado;
	private List<Point> listaPuntos;
	private String file;
	
	private JLabel infoPixelLabel, dLabel, sLabel;
	private JButton buttonDistancia;
	private JScrollPane jsp;
	
	private LectorImageHDU l;
	private PlanarImage input, im, scaledIm;
	private DisplayImageWithRegions display;
	private Imagen imagen;
	private DecimalCoordinate dc;
	
	private MathService mathService;
	
	private ServicioGestionTareas servicioGestionTareas;
	private ServiceBusquedaDobles serviceBusquedaDobles;
	
	
	/*Dialog components*/
	private JDialog dialog1;
	private JLabel label1;
	private JTextField brillo;
	private JLabel label5;
	private JTextField margDist;
	private JLabel label2;
	private JTextField umbral;
	private JLabel label6;
	private JTextField margAngulo;
	private JLabel label3;
	private JTextField maxCandidatos;
	private JLabel label7;
	private JTextField distMin;
	private JLabel label4;
	private JTextField maxEstrellas;
	private JButton procesar;
	

	public FitsCoordinateViewerPanel() {
		servicioGestionTareas =(ServicioGestionTareas) ContextoAplicacion.getApplicationContext().getBean("servicioGestionTareas");
		serviceBusquedaDobles=(ServiceBusquedaDobles)ContextoAplicacion.getApplicationContext().getBean("serviceBusquedaDobles");
		scale = 100;
		listaPuntos = null;
		calculoDistancia = false;
		estado = false;
		initComponents();
		this.initDialogComponents();
		
		mathService= (MathService) ContextoAplicacion.getApplicationContext().getBean("mathService");
		
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    jsp = new JScrollPane();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.BOTH;
	    c.weighty = 1.0;
	    c.weightx = 1.0;
	    add(jsp, c);
	    
	    infoPixelLabel = new JLabel("???");
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 2;
	    c.weighty = 0.0;
	    c.weightx = 0.0;
	    add(infoPixelLabel, c);
	    
	    dLabel = new JLabel();
	    c.gridx = 0;
	    c.gridy = 2;
	    add(dLabel, c);
	    
	    sLabel = new JLabel();
	    c.gridx = 0;
	    c.gridy = 3;
	    add(sLabel, c);
	    
	    JPanel panel = new JPanel();
	    GroupLayout layout = new GroupLayout(panel);
	    panel.setLayout(layout);
	    
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
		JButton buttonAbrir = new JButton();
		buttonAbrir.setIcon(new ImageIcon("images/iconos-diego/load.png"));
		buttonAbrir.setToolTipText("Abrir imagenes");
		buttonAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAbrirActionPerformed(e);
			}
		});
		
		JButton buttonZoomMas = new JButton();
	    buttonZoomMas.setIcon(new ImageIcon("images/iconos-diego/zoomin.png"));
	    buttonZoomMas.setToolTipText("Aumentar la imagen");
	    buttonZoomMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMasActionPerformed(e);
			}
		});
	    
	    JButton buttonZoomMenos = new JButton();
	    buttonZoomMenos.setIcon(new ImageIcon("images/iconos-diego/zoomout.png"));
	    buttonZoomMenos.setToolTipText("Disminuir la imagen");
	    buttonZoomMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMenosActionPerformed(e);
			}
		});
	    
	    JButton buttonRestaurar = new JButton();
	    buttonRestaurar.setIcon(new ImageIcon("images/iconos-diego/restaurar.png"));
	    buttonRestaurar.setToolTipText("Restaurar la imagen");
	    buttonRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonRestaurarActionPerformed(e);
			}
		});
	    
	    JButton buttonProcesar = new JButton();
	    buttonProcesar.setIcon(new ImageIcon("images/iconos-diego/buscar.png"));
	    buttonProcesar.setToolTipText("Procesar puntos en la imagen");
	    buttonProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonProcesarActionPerformed(e);
			}
		});
	    
	    buttonDistancia = new JButton();
	    buttonDistancia.setIcon(new ImageIcon("images/iconos-diego/regla.png"));
	    buttonDistancia.setToolTipText("Medir distancia entre dos puntos en la imagen");
	    buttonDistancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonDistanciaActionPerformed(e);
			}
		});
		
		layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	    		  .addComponent(buttonAbrir)
			      .addComponent(buttonZoomMas)
			      .addComponent(buttonZoomMenos)
			      .addComponent(buttonRestaurar)
			      .addComponent(buttonProcesar)
			      .addComponent(buttonDistancia))
		);
	    
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		      .addComponent(buttonAbrir)
		      .addComponent(buttonZoomMas)
		      .addComponent(buttonZoomMenos)
		      .addComponent(buttonRestaurar)
		      .addComponent(buttonProcesar)
		      .addComponent(buttonDistancia)
		);
		
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 4;
	    c.fill = GridBagConstraints.NONE;
	    add(panel, c);
		
		setVisible(true);
	}
	
	private void buttonDistanciaActionPerformed(ActionEvent e) {
		try {
			if (l == null)
				throw new Exception("Debe cargar primero una imagen");
			
			calculoDistancia = !calculoDistancia;
			
			if (!calculoDistancia)
				buttonDistancia.setIcon(new ImageIcon("images/distance2.jpg"));
			else
				buttonDistancia.setIcon(new ImageIcon("images/stop_icon2.png"));
			
			scale = 100;
			display.deleteROIs();
			scaledIm = input;
			display.set(input);
			jsp.repaint();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonProcesarActionPerformed(ActionEvent e) {
		/*TODO**/
		
		dialog1.setVisible(true);
	}
	
	private void buttonRestaurarActionPerformed(ActionEvent e) {
		scale = 100;
		listaPuntos = null;
		display.deleteROIs();
		scaledIm = input;
		display.set(input);
		jsp.repaint();
	}

	private void buttonZoomMasActionPerformed(ActionEvent e) {
		try {
			if (l == null)
				throw new Exception("Debe cargar primero una imagen");
			
			if (scale >= 10 && scale < 1000) {
				
				scale += porcentajeZoom;
				ParameterBlock pb = new ParameterBlock();
				InterpolationNearest in = new InterpolationNearest();
				pb.addSource(input);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm = JAI.create("scale", pb);
				display.set(scaledIm);
				
				display.deleteROIs();
				escalarYTrasladarCirculos();
				jsp.repaint();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonZoomMenosActionPerformed(ActionEvent e) {
		try {
			if (l == null)
				throw new Exception("Debe cargar primero una imagen");
			
			if (scale > 10 && scale <= 1000) {

				scale -= porcentajeZoom;
				ParameterBlock pb = new ParameterBlock();
				InterpolationNearest in = new InterpolationNearest();
				pb.addSource(input);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm = JAI.create("scale", pb);
				display.set(scaledIm);
				
				display.deleteROIs();
				escalarYTrasladarCirculos();
				jsp.repaint();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonAbrirActionPerformed(ActionEvent e) {
		
		FiltreExtensible filtre = new FiltreExtensible("Imagenes");
		filtre.addExtension(".fits");
		JFileChooser fc = new JFileChooser(".");
		fc.addChoosableFileFilter(filtre);
		
		
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile().toString();
				File dir = new File("Temp");
				dir.mkdir();
				
				scale = 100;
				listaPuntos = null;
				
				Fits imagenFITS = new Fits(new File(file));
				BasicHDU imageHDU = imagenFITS.getHDU(0);
				l = new LectorImageHDU(imageHDU, file);
				im = serviceBusquedaDobles.createPlanarImage(l);
				JAI.create("filestore", im, "Temp/im3.png", "PNG");
				input = JAI.create("fileload", "Temp/im3.png");
				scaledIm = input;
				
				display = new DisplayImageWithRegions(input);
				display.addMouseMotionListener(this);
			    display.addMouseListener(this);
				jsp.setViewportView(display);
				
				imagen = servicioGestionTareas.getImagenByPath(file);
				
				setVisible(true);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void escalarYTrasladarCirculos() {
		if (listaPuntos == null)
			return;
		
		for (Point p : listaPuntos) {
			Shape s = new Ellipse2D.Float(p.getX().floatValue() * (scale / 100.0f), p.getY().floatValue() * (scale / 100.0f),
					10.0f * (scale / 100.0f), 10.0f * (scale / 100.0f));
		    ImageRegion ir = new ImageRegion(scaledIm,new ROIShape(s));
		    ir.setBorderColor(new Color(255,0,0));
		    display.addImageRegion(ir);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		DecimalCoordinate dc = null;
		SexagesimalCoordinate sc = null;
		
		if (scale == 100) {
			dc = serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, im.getWidth(), im.getHeight(), e.getX(), e.getY());
			sc = es.ucm.si.dneb.service.math.CoordinateConverter.decimalToSexagesimalConverter(dc);
		}
		
		String pos = "(" + e.getX() + "," + e.getY() + ") ";
		String s1 = "", s2 = "";
		
		if (scale == 100 && e.getX() < scaledIm.getWidth() && e.getY() < scaledIm.getHeight()) {
			s1 = dc.toString();
			s2 = sc.toString();
		}
		
		infoPixelLabel.setText(pos + display.getPixelInfo());
		dLabel.setText(s1);
		sLabel.setText(s2);
	}
	
	private void procesarActionPerformed(ActionEvent e) {
		// TODO add your code here
		
		try {
			if (l == null)
				throw new Exception("Debe cargar primero una imagen");
			

			ServiceCalculoPosicion serviceCalculoPosicion= (ServiceCalculoPosicion) ContextoAplicacion.getApplicationContext().getBean("serviceCalculoPosicion");
			
			double brillo = 32000, umbral = 30000, margenAngulo = 0.03, margenDistancia = 0.10, distMinima = 4;
			int limCandidatos = 3, maxEstrellas = 120;

			
			if(!this.brillo.getText().equals("")){
				brillo=Double.parseDouble( this.brillo.getText());
			}
			
			if(!this.margDist.getText().equals("")){
				margenDistancia=Double.parseDouble( this.margDist.getText());
			}
			
			if(!this.umbral.getText().equals("")){
				umbral=Double.parseDouble( this.umbral.getText());
			}
			
			if(!this.margAngulo.getText().equals("")){
				margenAngulo=Double.parseDouble( this.margAngulo.getText());
			}
			
			if(!this.maxCandidatos.getText().equals("")){
				limCandidatos=Integer.parseInt( this.maxCandidatos.getText());
			}
			
			if(!this.distMin.getText().equals("")){
				distMinima=Double.parseDouble( this.distMin.getText());
			}
			if(!this.maxEstrellas.getText().equals("")){
				maxEstrellas=Integer.parseInt( this.maxEstrellas.getText());
			}

			
			List<Point> listaPuntis = serviceCalculoPosicion.algotirmoCalculoPosicion(brillo, umbral,limCandidatos,maxEstrellas,margenAngulo,margenDistancia,distMinima ,imagen);
			
			// obtengo la lista de puntos
			listaPuntos = new ArrayList<Point>();
			
			for(Point punt:listaPuntis){
				listaPuntos.add(new Point(punt.getX().intValue(), punt.getY().intValue()));
			}
			
			scale = 100;
			display.deleteROIs();
			scaledIm = input;
			display.set(input);
			
			for(Point punt : listaPuntos){
				Shape s = new Ellipse2D.Float(punt.getX().floatValue() - 5.0f, punt.getY().floatValue() - 5.0f, 10.0f, 10.0f);
			    ImageRegion ir = new ImageRegion(scaledIm,new ROIShape(s));
			    ir.setBorderColor(new Color(255,0,0));
			    display.addImageRegion(ir);
			}
			
			dialog1.setVisible(false);
			
			jsp.repaint();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (calculoDistancia) {
			if (e.getX() >= scaledIm.getWidth() || e.getY() >= scaledIm.getHeight()) {
				JOptionPane.showMessageDialog(null, "Elegir un punto dentro de la imagen", "Error", JOptionPane.ERROR_MESSAGE);
				estado = false;
				return;
			}
			
			estado = !estado;
			
			if (estado) {
				dc = serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, im.getWidth(), im.getHeight(), e.getX(), e.getY());
				
				display.deleteROIs();
				jsp.repaint();
				
				Shape s = new Ellipse2D.Float(e.getX() - 5.0f, e.getY() - 5.0f, 10.0f, 10.0f);
			    ImageRegion ir = new ImageRegion(scaledIm,new ROIShape(s));
			    ir.setBorderColor(new Color(0,255,0));
			    display.addImageRegion(ir);
				jsp.repaint();
			} else {
				DecimalCoordinate dcAux = serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, im.getWidth(), im.getHeight(), e.getX(), e.getY());
				
				// Calcular distancia entre dc y dcAux
				Distance d = mathService.calculateDecimalDistance(dc.getAr(), dc.getDec(), dcAux.getAr(), dcAux.getDec());
				
				Shape s = new Ellipse2D.Float(e.getX() - 5.0f, e.getY() - 5.0f, 10.0f, 10.0f);
			    ImageRegion ir = new ImageRegion(scaledIm,new ROIShape(s));
			    ir.setBorderColor(new Color(0,255,0));
			    display.addImageRegion(ir);
			    jsp.repaint();
				JOptionPane.showMessageDialog(null, "Distancia: " + d.getDistanceSeconds() + " arcosegundos" + "\nÁngulo: " + d.getAngle() + " grados", "Medición", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void initDialogComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialog1 = new JDialog();
		label1 = new JLabel();
		brillo = new JTextField();
		label5 = new JLabel();
		margDist = new JTextField();
		label2 = new JLabel();
		umbral = new JTextField();
		label6 = new JLabel();
		margAngulo = new JTextField();
		label3 = new JLabel();
		maxCandidatos = new JTextField();
		label7 = new JLabel();
		distMin = new JTextField();
		label4 = new JLabel();
		maxEstrellas = new JTextField();
		procesar = new JButton();

		//======== dialog1 ========
		{
			dialog1.setTitle("Procesamiento de c\u00e1lculo de distancia");
			Container dialog1ContentPane = dialog1.getContentPane();
			dialog1ContentPane.setLayout(new GridLayoutManager(10, 5, new Insets(0, 0, 0, 0), -1, -1, true, false));

			//---- label1 ----
			label1.setText("Brillo");
			dialog1ContentPane.add(label1, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(brillo, new GridConstraints(1, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label5 ----
			label5.setText("Margen distancia");
			dialog1ContentPane.add(label5, new GridConstraints(1, 2, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(margDist, new GridConstraints(1, 3, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label2 ----
			label2.setText("Umbral");
			dialog1ContentPane.add(label2, new GridConstraints(2, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(umbral, new GridConstraints(2, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label6 ----
			label6.setText("Margen angulo");
			dialog1ContentPane.add(label6, new GridConstraints(2, 2, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(margAngulo, new GridConstraints(2, 3, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label3 ----
			label3.setText("Max Candidatos");
			dialog1ContentPane.add(label3, new GridConstraints(3, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(maxCandidatos, new GridConstraints(3, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label7 ----
			label7.setText("Distancia m\u00ednima");
			dialog1ContentPane.add(label7, new GridConstraints(3, 2, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(distMin, new GridConstraints(3, 3, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- label4 ----
			label4.setText("Max Estrellas");
			dialog1ContentPane.add(label4, new GridConstraints(4, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1ContentPane.add(maxEstrellas, new GridConstraints(4, 1, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));

			//---- procesar ----
			procesar.setText("Procesar");
			procesar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					procesarActionPerformed(e);
				}
			});
			dialog1ContentPane.add(procesar, new GridConstraints(9, 2, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				null, null, null));
			dialog1.pack();
			dialog1.setLocationRelativeTo(dialog1.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}