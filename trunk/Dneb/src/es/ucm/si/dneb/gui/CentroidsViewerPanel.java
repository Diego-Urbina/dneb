
package es.ucm.si.dneb.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.ROIShape;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.image.app.DisplayImageWithRegions;
import es.ucm.si.dneb.service.image.app.ImageRegion;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.util.Point;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.util.FiltreExtensible;

public class CentroidsViewerPanel extends JPanel implements AdjustmentListener {

	private static final long serialVersionUID = -7389267318471409502L;
	
	private boolean hiloParado;
	private HiloAnimacion hilo;
	
	private JScrollPane jsp1, jsp2;
	private JTextField textFieldUmbral, textFieldBrillo;
	private JButton buttonAnimar;
	
	private LectorImageHDU l1, l2;
	private PlanarImage input1, input2;
	private DisplayImageWithRegions display1, display2;
	private Imagen im1, im2;
	
	private ServiceBusquedaDobles serviceBusquedaDobles;
	private ServicioGestionTareas servicioGestionTareas;

	public CentroidsViewerPanel() {
		initComponents();
		serviceBusquedaDobles=(ServiceBusquedaDobles)ContextoAplicacion.getApplicationContext().getBean("serviceBusquedaDobles");
		servicioGestionTareas =(ServicioGestionTareas) ContextoAplicacion.getApplicationContext().getBean("servicioGestionTareas");
		hiloParado = true;
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
	    
	    JPanel panel2 = new JPanel();
	    GroupLayout layout = new GroupLayout(panel2);
	    panel2.setLayout(layout);
	    
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
	    
	    buttonAnimar = new JButton();
	    buttonAnimar.setIcon(new ImageIcon("images/starticon2.jpg"));
	    buttonAnimar.setToolTipText("Animación de imágenes");
	    buttonAnimar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAnimarActionPerformed(e);
			}
		});
	    
	    JLabel labelUmbral = new JLabel("Umbral");
	    JLabel labelBrillo = new JLabel("Brillo");
	    textFieldUmbral = new JTextField("40000");
	    textFieldBrillo = new JTextField("42000");
		
		layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	    		  .addComponent(buttonAbrir)
			      .addComponent(buttonRestaurar)
			      .addComponent(buttonProcesar)
			      .addComponent(buttonAnimar)
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
		      .addComponent(buttonRestaurar)
		      .addComponent(buttonProcesar)
		      .addComponent(buttonAnimar)
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
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.NONE;
	    c.weightx = 0.0;
	    add(panel2, c);
		
	    // Horizontal scroll bar of the first image.
	    jsp1.getHorizontalScrollBar().addAdjustmentListener(this);
	    // Vertical scroll bar of the first image.
	    jsp1.getVerticalScrollBar().addAdjustmentListener(this);
	    // Horizontal scroll bar of the second image.
	    jsp2.getHorizontalScrollBar().addAdjustmentListener(this);
	    // Vertical scroll bar of the second image.
	    jsp2.getVerticalScrollBar().addAdjustmentListener(this);
	    
		this.setVisible(true);
	}
	
	private void buttonRestaurarActionPerformed(ActionEvent e) {
		restaurar();
	}
	
	private void restaurar() {
		display1.deleteROIs();
		display2.deleteROIs();
		display1.set(input1);
		display2.set(input2);
		jsp1.repaint();
		jsp2.repaint();
	}
	
	private void abrirImagenes(String file1, String file2) {
		try {
			File dir = new File("Temp");
			dir.mkdir();
			
			Fits imagenFITS = new Fits(new File(file1));
			BasicHDU imageHDU = imagenFITS.getHDU(0);
			l1 = new LectorImageHDU(imageHDU, file1);
			PlanarImage im = serviceBusquedaDobles.createPlanarImage(l1);
			JAI.create("filestore", im, "Temp/im1.png", "PNG");
			input1 = JAI.create("fileload", "Temp/im1.png");
			display1 = new DisplayImageWithRegions(input1);
			jsp1.setViewportView(display1);
			
			imagenFITS = new Fits(new File(file2));
			imageHDU = imagenFITS.getHDU(0);
			l2 = new LectorImageHDU(imageHDU, file2);
			im = serviceBusquedaDobles.createPlanarImage(l2);
			JAI.create("filestore", im, "Temp/im2.png","PNG");
			input2 = JAI.create("fileload", "Temp/im2.png");
			display2 = new DisplayImageWithRegions(input2);
			jsp2.setViewportView(display2);
			
			im1 = servicioGestionTareas.getImagenByPath(file1);
			im2 = servicioGestionTareas.getImagenByPath(file2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonAbrirActionPerformed(ActionEvent e) {
		
		FiltreExtensible filtre = new FiltreExtensible("Imagenes");
		filtre.addExtension(".fits");
		JFileChooser fc = new JFileChooser(".");
		fc.addChoosableFileFilter(filtre);
		
		int retval = fc.showOpenDialog(this);
		if (retval == JFileChooser.APPROVE_OPTION) {
			String filename1 = fc.getSelectedFile().toString();
			retval = fc.showOpenDialog(this);
			if (retval == JFileChooser.APPROVE_OPTION) {
				String filename2 = fc.getSelectedFile().toString();
				abrirImagenes(filename1, filename2);
			}
		}
	}
	
	private void buttonAnimarActionPerformed(ActionEvent e) {
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
			
			restaurar();
			
			jsp1.setVisible(!hiloParado);
			
			if (hiloParado) {
				PlanarImage[] imagenes = {input1, input2};
				float sW = Math.min(input1.getWidth(), input2.getWidth());
				float sH = Math.min(input1.getHeight(), input2.getHeight());
				hilo = new HiloAnimacion(imagenes, display2, sW, sH);
				hilo.start();
				buttonAnimar.setIcon(new ImageIcon("images/stop_icon2.png"));
			} else {
				hilo.detenerHilo();
				buttonAnimar.setIcon(new ImageIcon("images/starticon2.jpg"));
				display2.set(input2);
			}
			
			hiloParado = !hiloParado;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buttonProcesarActionPerformed(ActionEvent e) {
		
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
			
			restaurar();
			
			double umbral, brillo;
			umbral = Double.parseDouble(textFieldUmbral.getText());
			brillo = Double.parseDouble(textFieldBrillo.getText());
			
			if (umbral <= 0 || brillo <= 0)
				throw new Exception("Los parámetros deben ser mayores que 0");
			
			Point[][] centroides = serviceBusquedaDobles.busquedaEstrellasMovimiento(umbral, brillo, im1, im2);
			
			if (centroides == null || centroides[0][0] == null || centroides[1][0] == null) {
				JOptionPane.showMessageDialog(null, "No se han encontrado estrellas en movimiento", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			display1.deleteROIs();
			display1.set(input1);
			jsp1.repaint();
			display2.deleteROIs();
			display2.set(input2);
			jsp2.repaint();
			
			Point p1, p2;
			for (int i = 0; i < centroides[0].length && centroides[0][i] != null && centroides[1][i] != null; i++) {
				p1 = centroides[0][i];
				Shape s11 = new Ellipse2D.Float(p1.getX().floatValue() - 1.5f, p1.getY().floatValue() - 1.5f, 3.0f, 3.0f);
				Shape s12 = new Ellipse2D.Float(p1.getX().floatValue() - 10.0f, p1.getY().floatValue() - 10.0f, 20.0f, 20.0f);
				p2 = centroides[1][i];
				Shape s21 = new Ellipse2D.Float(p2.getX().floatValue() - 1.5f, p2.getY().floatValue() - 1.5f, 3.0f, 3.0f);
				Shape s22 = new Ellipse2D.Float(p2.getX().floatValue() - 10.0f, p2.getY().floatValue() - 10.0f, 20.0f, 20.0f);
				
				ImageRegion ir11 = new ImageRegion(input1,new ROIShape(s11));
				ir11.setBorderColor(new Color(255,0,0));
				display1.addImageRegion(ir11);
				ImageRegion ir12 = new ImageRegion(input1,new ROIShape(s12));
				ir12.setBorderColor(new Color(0,255,0));
				display1.addImageRegion(ir12);
				ImageRegion ir21 = new ImageRegion(input2,new ROIShape(s21));
				ir21.setBorderColor(new Color(255,0,0));
				display2.addImageRegion(ir21);
				ImageRegion ir22 = new ImageRegion(input2,new ROIShape(s22));
				ir22.setBorderColor(new Color(0,255,0));
				display2.addImageRegion(ir22);
			}
			jsp1.repaint();
			jsp2.repaint();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Los parámetros deben ser numéricos");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	
	class HiloAnimacion extends Thread {
		
		private PlanarImage[] imagenes;
		private DisplayImageWithRegions display;
		private float scaleW, scaleH;
		private boolean continuar;
		
		public HiloAnimacion(PlanarImage[] ims, DisplayImageWithRegions dis, float scW, float scH) {
			imagenes = ims;
			display = dis;
			scaleW = scW;
			scaleH = scH;
			continuar = true;
			
			igualarDimensiones();
		}
		
		@Override
		public void run() {
			int cont = 0;
			while (continuar) {
				try {
					display.set(imagenes[cont]);
					cont = (cont+1)%2;
					Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
		}
		
		private void igualarDimensiones() {			
			float sW, sH;
			PlanarImage auxIm;
			int cont = 0;
			
			for (PlanarImage pi : imagenes) {
				sW = scaleW / imagenes[cont].getWidth();
				sH = scaleH / imagenes[cont].getHeight();
				
				ParameterBlock pb = new ParameterBlock();
				InterpolationNearest in = new InterpolationNearest();
				pb.addSource(pi);
				pb.add(sH);
				pb.add(sW);
				pb.add(0.0F);
				pb.add(0.0F);
				pb.add(in);
				auxIm = JAI.create("scale", pb);
				imagenes[cont] = auxIm;
				
				cont++;
			}
		}
		
		public void detenerHilo() {
			continuar = false;
		}
		
	}
	
	public static void main(String[] args) {
		new CentroidsViewerPanel();
	}

}