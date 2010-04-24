
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
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.List;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.util.ArrayFuncs;
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
import es.ucm.si.dneb.service.math.SexagesimalCoordinate;
import es.ucm.si.dneb.util.FiltreExtensible;

public class FitsCoordinateViewerPanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -7389267318471409502L;
	private static final int porcentajeZoom = 10;
	
	private int scale;
	private List<Point> listaPuntos;
	
	private JLabel infoPixelLabel, dLabel, sLabel;
	private JScrollPane jsp;
	private String file;
	
	private LectorImageHDU l;
	private PlanarImage input, im, scaledIm;
	private DisplayImageWithRegions display;
	private Imagen imagen;
	
	private ServicioGestionTareas servicioGestionTareas;
	private ServiceBusquedaDobles serviceBusquedaDobles;

	public FitsCoordinateViewerPanel() {
		servicioGestionTareas =(ServicioGestionTareas) ContextoAplicacion.getApplicationContext().getBean("servicioGestionTareas");
		serviceBusquedaDobles=(ServiceBusquedaDobles)ContextoAplicacion.getApplicationContext().getBean("serviceBusquedaDobles");
		scale = 100;
		listaPuntos = null;
		initComponents();
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
		buttonAbrir.setIcon(new ImageIcon("images/abrir.gif"));
		buttonAbrir.setToolTipText("Abrir imagenes");
		buttonAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAbrirActionPerformed(e);
			}
		});
		
		JButton buttonZoomMas = new JButton();
	    buttonZoomMas.setIcon(new ImageIcon("images/zoom_in_icon.gif"));
	    buttonZoomMas.setToolTipText("Aumentar la imagen");
	    buttonZoomMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMasActionPerformed(e);
			}
		});
	    
	    JButton buttonZoomMenos = new JButton();
	    buttonZoomMenos.setIcon(new ImageIcon("images/zoom_out_icon.gif"));
	    buttonZoomMenos.setToolTipText("Disminuir la imagen");
	    buttonZoomMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMenosActionPerformed(e);
			}
		});
	    
	    JButton buttonRestaurar = new JButton();
	    buttonRestaurar.setIcon(new ImageIcon("images/restaurar.gif"));
	    buttonRestaurar.setToolTipText("Restaurar la imagen");
	    buttonRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonRestaurarActionPerformed(e);
			}
		});
	    
	    JButton buttonProcesar = new JButton();
	    buttonProcesar.setIcon(new ImageIcon("images/buscar.gif"));
	    buttonProcesar.setToolTipText("Procesar puntos en la imagen");
	    buttonProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonProcesarActionPerformed(e);
			}
		});
		
		layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	    		  .addComponent(buttonAbrir)
			      .addComponent(buttonZoomMas)
			      .addComponent(buttonZoomMenos)
			      .addComponent(buttonRestaurar)
			      .addComponent(buttonProcesar))
		);
	    
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		      .addComponent(buttonAbrir)
		      .addComponent(buttonZoomMas)
		      .addComponent(buttonZoomMenos)
		      .addComponent(buttonRestaurar)
		      .addComponent(buttonProcesar)
		);
		
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.NONE;
	    c.weightx = 0.0;
	    add(panel, c);
		
		setVisible(true);
	}
	
	private void buttonProcesarActionPerformed(ActionEvent e) {
		try {
			if (l == null)
				throw new Exception("Debe cargar primero una imagen");
			
			ServiceCalculoPosicion serviceCalculoPosicion= (ServiceCalculoPosicion) ContextoAplicacion.getApplicationContext().getBean("serviceCalculoPosicion");
			
			listaPuntos = serviceCalculoPosicion.calcularPosicion(imagen, 30000, 20000);
			
			scale = 100;
			display.deleteROIs();
			scaledIm = input;
			display.set(input);
			
			for(Point punt : listaPuntos){
				listaPuntos.add(punt);
				Shape s = new Ellipse2D.Float(punt.getX().floatValue(), punt.getY().floatValue(), 10.0f * (scale / 100), 10.0f * (scale / 100));
			    ImageRegion ir = new ImageRegion(scaledIm,new ROIShape(s));
			    ir.setBorderColor(new Color(255,0,0));
			    display.addImageRegion(ir);
			}
			jsp.repaint();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
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
				im = createPlanarImage(l);
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
	
	private PlanarImage createPlanarImage(LectorImageHDU l) {
		int[] arrayDataAplanado = (int[]) ArrayFuncs.flatten(l.getArrayData());
		
		DataBufferInt dBuffer = new DataBufferInt(arrayDataAplanado, l.getWidth()*l.getHeight());
		SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_SHORT, l.getWidth(), l.getHeight(), 1);
		ColorModel cm = PlanarImage.createColorModel(sm);
		Raster raster = RasterFactory.createWritableRaster(sm, dBuffer, new java.awt.Point(0,0));
		TiledImage tiledImage = new TiledImage(0,0,l.getWidth(),l.getHeight(),0,0,sm,cm);
		tiledImage.setData(raster);
		
		return tiledImage;
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
		
		DecimalCoordinate dc = serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, im.getWidth(), im.getHeight(), e.getX(), e.getY());
		SexagesimalCoordinate sc = es.ucm.si.dneb.service.math.CoordinateConverter.decimalToSexagesimalConverter(dc);
		
		String pos = "(" + e.getX() + "," + e.getY() + ") ";
		String s1 = "", s2 = "";
		
		if (e.getX() < scaledIm.getWidth() && e.getY() < scaledIm.getHeight()) {
			s1 = dc.toString();
			s2 = sc.toString();
		}
		
		infoPixelLabel.setText(pos + display.getPixelInfo());
		dLabel.setText(s1);
		sLabel.setText(s2);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, e.getX() + " " + e.getY());
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