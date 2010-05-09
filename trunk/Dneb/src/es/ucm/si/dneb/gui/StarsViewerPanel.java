package es.ucm.si.dneb.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.ArrayList;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;
import es.ucm.si.dneb.service.image.app.DisplayImageWithRegions;
import es.ucm.si.dneb.service.image.app.ImageRegion;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.RectStar;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.util.FiltreExtensible;

public class StarsViewerPanel extends JPanel implements AdjustmentListener, MouseListener, MouseMotionListener {
		
	private static final long serialVersionUID = -2623868602369156354L;
	private static final int porcentajeZoom = 10;
	
	private JScrollPane jsp1, jsp2;
	private JLabel labelPosicion;
	private JTextField textFieldUmbral, textFieldBrillo;
	
	private DisplayImageWithRegions display1, display2;
	private PlanarImage input1, input2, scaledIm1, scaledIm2;
	private LectorImageHDU l1, l2;
	private StarFinder sf1, sf2;
	
	private VentanaPcpal principal;
	private int position, scale;
	
	public StarsViewerPanel(VentanaPcpal pcpal,int position) {
		principal = pcpal;
		this.position=position;
		initComponents();
	}
	
	public StarsViewerPanel(JFrame parent) {
		display1 = null;
		display2 = null;
		input1 = null;
		input2 = null;
		scaledIm1 = null;
		scaledIm2 = null;
		l1 = null;
		l2 = null;
		sf1 = new StarFinder();
		sf2 = new StarFinder();
		scale = 100;
		initComponents();
	    
		parent.add(this);
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.setVisible(true);
	}
	
	private void initComponents() {
		
		setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    jsp1 = new JScrollPane();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.BOTH;
	    c.weighty = 1.0;
	    c.weightx = 1.0;
	    add(jsp1, c);
	    
	    
	    jsp2 = new JScrollPane();
	    c.gridx = 1;
	    c.gridy = 0;
	    add(jsp2, c);
	    
	    
	    labelPosicion = new JLabel("???");
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 2;
	    c.weighty = 0.0;
	    c.weightx = 0.0;
	    add(labelPosicion, c);
	    
	    
	    JPanel panel = new JPanel();
	    GroupLayout layout = new GroupLayout(panel);
	    panel.setLayout(layout);
	    
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    
	    JButton buttonAbrir = new JButton();
	    Icon icon = new ImageIcon("images/abrir.gif");
	    buttonAbrir.setIcon(icon);
	    buttonAbrir.setToolTipText("Abrir imagenes");
	    buttonAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAbrirActionPerformed(e);
			}
		});
	    
	    JButton buttonBuscar = new JButton();
	    icon = new ImageIcon("images/buscar.gif");
	    buttonBuscar.setIcon(icon);
	    buttonBuscar.setToolTipText("Buscar estrellas en la imagen y marcarlas con cuadrados");
	    buttonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonBuscarActionPerformed(e);
			}
		});
	    
	    JButton buttonZoomMas = new JButton();
	    icon = new ImageIcon("images/zoom_in_icon.gif");
	    buttonZoomMas.setIcon(icon);
	    buttonZoomMas.setToolTipText("Aumentar la imagen");
	    buttonZoomMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMasActionPerformed(e);
			}
		});
	    
	    JButton buttonZoomMenos = new JButton();
	    icon = new ImageIcon("images/zoom_out_icon.gif");
	    buttonZoomMenos.setIcon(icon);
	    buttonZoomMenos.setToolTipText("Disminuir la imagen");
	    buttonZoomMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonZoomMenosActionPerformed(e);
			}
		});
	    
	    JButton buttonRestaurar = new JButton();
	    icon = new ImageIcon("images/restaurar.gif");
	    buttonRestaurar.setIcon(icon);
	    buttonRestaurar.setToolTipText("Restaurar la imagen");
	    buttonRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonRestaurarActionPerformed(e);
			}
		});
	    
	    JLabel labelUmbral = new JLabel("Umbral");
	    JLabel labelBrillo = new JLabel("Brillo");
	    textFieldUmbral = new JTextField("30000");
	    textFieldBrillo = new JTextField("32000");
	    
	    layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		           .addComponent(buttonAbrir)
		           .addComponent(buttonZoomMas)
		           .addComponent(buttonZoomMenos)
		           .addComponent(buttonRestaurar)
		           .addComponent(buttonBuscar)
		           .addGap(20, 20, 20)
		           .addGroup(layout.createSequentialGroup()
		        		   .addComponent(labelUmbral, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
		        		   .addComponent(textFieldUmbral, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
		           .addGroup(layout.createSequentialGroup()
		           .addComponent(labelBrillo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
		           .addComponent(textFieldBrillo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
		);
	    
		layout.setVerticalGroup(
		   layout.createSequentialGroup()
		      .addComponent(buttonAbrir)
		      .addComponent(buttonZoomMas)
		      .addComponent(buttonZoomMenos)
		      .addComponent(buttonRestaurar)
		      .addComponent(buttonBuscar)
		      .addGap(20, 20, 20)
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    		  .addComponent(labelUmbral)
		    		  .addComponent(textFieldUmbral))
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    		  .addComponent(labelBrillo)
		    		  .addComponent(textFieldBrillo))
		);
		
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 2;
	    c.fill = GridBagConstraints.NONE;
	    add(panel, c);
	    
	    
	    // Horizontal scroll bar of the first image.
	    jsp1.getHorizontalScrollBar().addAdjustmentListener(this);
	    // Vertical scroll bar of the first image.
	    jsp1.getVerticalScrollBar().addAdjustmentListener(this);
	    // Horizontal scroll bar of the second image.
	    jsp2.getHorizontalScrollBar().addAdjustmentListener(this);
	    // Vertical scroll bar of the second image.
	    jsp2.getVerticalScrollBar().addAdjustmentListener(this);
	}
	
	private void buttonRestaurarActionPerformed(ActionEvent e) {
		restaurar();
	}
	
	private void restaurar() {
		scale = 100;
		sf1.eliminarRecuadros();
		sf2.eliminarRecuadros();
		display1.deleteROIs();
		display2.deleteROIs();
		display1.set(input1);
		display2.set(input2);
		scaledIm1 = input1;
		scaledIm2 = input2;
		jsp1.repaint();
		jsp2.repaint();
	}
	
	private void buttonZoomMasActionPerformed(ActionEvent e) {
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
			
			if (scale >= 10 && scale < 1000) {
				
				scale += porcentajeZoom;
				ParameterBlock pb = new ParameterBlock();
				InterpolationNearest in = new InterpolationNearest();
				pb.addSource(input1);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm1 = JAI.create("scale", pb);
				display1.set(scaledIm1);
				pb = new ParameterBlock();
				in = new InterpolationNearest();
				pb.addSource(input2);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm2 = JAI.create("scale", pb);
				display2.set(scaledIm2);
				
				sf1.escalarYTrasladarRectangulos(scale, -porcentajeZoom);
				sf2.escalarYTrasladarRectangulos(scale, -porcentajeZoom);
				display1.deleteROIs();
				ArrayList<RectStar> stars = sf1.getRecuadros();
				
				for (RectStar r : stars) {
					Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
				    ImageRegion ir = new ImageRegion(scaledIm1,new ROIShape(s));
				    ir.setBorderColor(new Color(255,255,0));
				    display1.addImageRegion(ir);
				}
				
				display2.deleteROIs();
				stars = sf2.getRecuadros();
				
				for (RectStar r : stars) {
					Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
				    ImageRegion ir = new ImageRegion(scaledIm2,new ROIShape(s));
				    ir.setBorderColor(new Color(255,255,0));
				    display2.addImageRegion(ir);
				}
				
				jsp1.repaint();
				jsp2.repaint();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonZoomMenosActionPerformed(ActionEvent e) {
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
			
			if (scale > 10 && scale <= 1000) {

				scale -= porcentajeZoom;
				ParameterBlock pb = new ParameterBlock();
				InterpolationNearest in = new InterpolationNearest();
				pb.addSource(input1);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm1 = JAI.create("scale", pb);
				display1.set(scaledIm1);
				pb = new ParameterBlock();
				in = new InterpolationNearest();
				pb.addSource(input2);
				pb.add(scale/100f);
				pb.add(scale/100f);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				scaledIm2 = JAI.create("scale", pb);
				display2.set(scaledIm2);
				
				sf1.escalarYTrasladarRectangulos(scale, porcentajeZoom);
				sf2.escalarYTrasladarRectangulos(scale, porcentajeZoom);
				display1.deleteROIs();
				ArrayList<RectStar> stars = sf1.getRecuadros();
				
				for (RectStar r : stars) {
					Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
				    ImageRegion ir = new ImageRegion(scaledIm1,new ROIShape(s));
				    ir.setBorderColor(new Color(255,255,0));
				    display1.addImageRegion(ir);
				}
				
				display2.deleteROIs();
				stars = sf2.getRecuadros();
				
				for (RectStar r : stars) {
					Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
				    ImageRegion ir = new ImageRegion(scaledIm2,new ROIShape(s));
				    ir.setBorderColor(new Color(255,255,0));
				    display2.addImageRegion(ir);
				}
				
				jsp1.repaint();
				jsp2.repaint();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonAbrirActionPerformed(ActionEvent e) {
		ServiceBusquedaDobles serviceBusquedaDobles=(ServiceBusquedaDobles)ContextoAplicacion.getApplicationContext().getBean("serviceBusquedaDobles");
		
		FiltreExtensible filtre = new FiltreExtensible("Imagenes");
		filtre.addExtension(".fits");
		JFileChooser fc = new JFileChooser(".");
		fc.addChoosableFileFilter(filtre);
		
		String file1, file2;
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				file1 = fc.getSelectedFile().toString();
				retval = fc.showOpenDialog(this);
				if (retval == JFileChooser.APPROVE_OPTION) {
					file2 = fc.getSelectedFile().toString();
					File dir = new File("Temp");
					dir.mkdir();
					
					Fits imagenFITS = new Fits(new File(file1));			
					BasicHDU imageHDU = imagenFITS.getHDU(0);
					l1 = new LectorImageHDU(imageHDU, file1);
					PlanarImage im = serviceBusquedaDobles.createPlanarImage(l1);
					JAI.create("filestore",im,"Temp/im1.png","PNG");
					input1 = JAI.create("fileload", "Temp/im1.png");
					scaledIm1 = input1;
					display1 = new DisplayImageWithRegions(input1);
					display1.addMouseMotionListener(this);
				    display1.addMouseListener(this);
					jsp1.setViewportView(display1);
					
					imagenFITS = new Fits(new File(file2));
					imageHDU = imagenFITS.getHDU(0);
					l2 = new LectorImageHDU(imageHDU, file2);
					im = serviceBusquedaDobles.createPlanarImage(l2);
					JAI.create("filestore",im,"Temp/im2.png","PNG");
					input2 = JAI.create("fileload", "Temp/im2.png");
					scaledIm2 = input2;
					display2 = new DisplayImageWithRegions(input2);
					display2.addMouseMotionListener(this);
					display2.addMouseListener(this);
					jsp2.setViewportView(display2);
					
					scale = 100;
					sf1.eliminarRecuadros();
					sf2.eliminarRecuadros();
				}
			}
		} catch(Exception ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void buttonBuscarActionPerformed(ActionEvent e) {
		float umbral, brilloEstrella;
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
				
			umbral = Float.parseFloat(textFieldUmbral.getText());
			brilloEstrella = Float.parseFloat(textFieldBrillo.getText());
			
			if (umbral <= 0 || brilloEstrella <= 0)
				throw new Exception("Los parámetros deben ser mayores que 0");
			
			restaurar();
			
			sf1.buscarEstrellas(l1, brilloEstrella, umbral);
			ArrayList<RectStar> stars = sf1.getRecuadros();
			
			for (RectStar r : stars) {
				Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
			    ImageRegion ir = new ImageRegion(scaledIm1,new ROIShape(s));
			    ir.setBorderColor(new Color(255,255,0));
			    display1.addImageRegion(ir);
			}
			
			sf2.buscarEstrellas(l2, brilloEstrella, umbral);
			stars = sf2.getRecuadros();
			
			for (RectStar r : stars) {
				Shape s = new Rectangle(r.getxLeft(), r.getyTop(), r.getWidth(), r.getHeight());
			    ImageRegion ir = new ImageRegion(scaledIm2,new ROIShape(s));
			    ir.setBorderColor(new Color(255,255,0));
			    display2.addImageRegion(ir);
			}
			
			jsp1.repaint();
			jsp2.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Los parámetros deben ser numéricos");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		
		// If the horizontal bar of the first image was changed...
	    if (e.getSource() == jsp1.getHorizontalScrollBar()) {
	      // We change the position of the horizontal bar of the second image.
	      jsp2.getHorizontalScrollBar().setValue(e.getValue());
	    }
	    
	    // If the vertical bar of the first image was changed...
	    if (e.getSource() == jsp1.getVerticalScrollBar()) {
	      // We change the position of the vertical bar of the second image.
	      jsp2.getVerticalScrollBar().setValue(e.getValue());
	    }
	    
	    // If the horizontal bar of the second image was changed...
	    if (e.getSource() == jsp2.getHorizontalScrollBar()) {
	      // We change the position of the horizontal bar of the first image.
	      jsp1.getHorizontalScrollBar().setValue(e.getValue());
	    }
	    
	    // If the vertical bar of the second image was changed...
	    if (e.getSource() == jsp2.getVerticalScrollBar()) {
	      // We change the position of the vertical bar of the first image.
	      jsp1.getVerticalScrollBar().setValue(e.getValue());
	    }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		String pos = "(" + e.getX() + "," + e.getY() + ") ";
		if (e.getSource() == display1)
			labelPosicion.setText(pos + display1.getPixelInfo());
		if (e.getSource() == display2)
			labelPosicion.setText(pos + display2.getPixelInfo());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
