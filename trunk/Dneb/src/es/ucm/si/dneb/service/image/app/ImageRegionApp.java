package es.ucm.si.dneb.service.image.app;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;
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
import javax.swing.filechooser.FileFilter;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.util.ArrayFuncs;
import es.ucm.si.dneb.gui.VentanaPcpal;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.image.segmentation.StarFinder;


public class ImageRegionApp extends JPanel implements AdjustmentListener, MouseListener, MouseMotionListener {
	
	private JScrollPane jsp1, jsp2;
	private JLabel labelPosicion;
	private JTextField textFieldUmbral, textFieldBrillo;
	
	private DisplayImageWithRegions display1, display2;
	private PlanarImage input1, input2;
	private LectorImageHDU l1, l2;
	
	private VentanaPcpal principal;
	private int position;
	
	public ImageRegionApp(VentanaPcpal pcpal,int position) {
		principal = pcpal;
		this.position=position;
		initComponents();
	}
	
	public ImageRegionApp(JFrame parent) {
		display1 = null;
		display2 = null;
		input1 = null;
		input2 = null;
		l1 = null;
		l2 = null;
		initComponents();
		
		parent.setSize(500, 650);
		parent.setExtendedState(JFrame.MAXIMIZED_BOTH);  
		parent.setLocationRelativeTo(null);
		parent.setMinimumSize(new Dimension(105, 330));
	    
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
	    
	    
	    labelPosicion = new JLabel();
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridwidth = 3;
	    c.weighty = 0.0;
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
	    
	    JLabel labelUmbral = new JLabel("Umbral");
	    JLabel labelBrillo = new JLabel("Brillo");
	    textFieldUmbral = new JTextField("20000");
	    textFieldBrillo = new JTextField("30000");
	    
	    layout.setHorizontalGroup(
		   layout.createSequentialGroup()
		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
		           .addComponent(buttonAbrir)
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
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.NONE;
	    c.weightx = 0.0;
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
	
	private void buttonAbrirActionPerformed(ActionEvent e) {
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
					PlanarImage im = createPlanarImage(l1);
					JAI.create("filestore",im,"Temp/im1.png","PNG");
					input1 = JAI.create("fileload", "Temp/im1.png");
					display1 = new DisplayImageWithRegions(input1);
					display1.addMouseMotionListener(this);
				    display1.addMouseListener(this);
					jsp1.setViewportView(display1);
					
					imagenFITS = new Fits(new File(file2));
					imageHDU = imagenFITS.getHDU(0);
					l2 = new LectorImageHDU(imageHDU, file2);
					im = createPlanarImage(l2);
					JAI.create("filestore",im,"Temp/im2.png","PNG");
					input2 = JAI.create("fileload", "Temp/im2.png");
					display2 = new DisplayImageWithRegions(input2);
					display2.addMouseMotionListener(this);
					display2.addMouseListener(this);
					jsp2.setViewportView(display2);
					
				}
			}
		} catch(Exception ex) {
        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private PlanarImage createPlanarImage(LectorImageHDU l) {
		int[] arrayDataAplanado = (int[]) ArrayFuncs.flatten(l.getArrayData());
		
		DataBufferInt dBuffer = new DataBufferInt(arrayDataAplanado, l.getWidth()*l.getHeight());
		SampleModel sm = RasterFactory.createBandedSampleModel(DataBuffer.TYPE_SHORT, l.getWidth(), l.getHeight(), 1);
		ColorModel cm = PlanarImage.createColorModel(sm);
		Raster raster = RasterFactory.createWritableRaster(sm, dBuffer, new Point(0,0));
		TiledImage tiledImage = new TiledImage(0,0,l.getWidth(),l.getHeight(),0,0,sm,cm);
		tiledImage.setData(raster);
		
		return tiledImage;
	}
	
	private void buttonBuscarActionPerformed(ActionEvent e) {
		StarFinder sf1 = new StarFinder(), sf2 = new StarFinder();
		float umbral, brilloEstrella;
		try {
			if (l1 == null || l2 == null)
				throw new Exception("Debe cargar primero dos imágenes");
				
			umbral = Float.parseFloat(textFieldUmbral.getText());
			brilloEstrella = Float.parseFloat(textFieldBrillo.getText());
			
			if (umbral <= 0 || brilloEstrella <= 0)
				throw new Exception("Los parámetros deben ser mayores que 0");
			
			sf1.buscarEstrellas(l1, brilloEstrella, umbral);
			display1.deleteROIs();
			sf1.printRectStars(input1, display1);
			sf2.buscarEstrellas(l2, brilloEstrella, umbral);
			display2.deleteROIs();
			sf2.printRectStars(input2, display2);
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


	class FiltreExtensible extends FileFilter{
		
		   // descripción y extensiones aceptadas por el filtro
		   private String description;
		   private List<String> extensions;
		 
		   // constructor a partir de la descripción
		   public FiltreExtensible(String description){
		      this.description = description;
		      this.extensions = new ArrayList<String>();
		   }
		 
		   public String getDescription(){
			  if (description == null)
				  return "No description";
			  
		      StringBuffer buffer = new StringBuffer(description);
		      buffer.append(" (");
		      for(String extension : extensions){
		         buffer.append(extension).append(" ");
		      }
		      return buffer.append(")").toString();
		   }
		 
		   public void setDescription(String description){
		      this.description = description;
		   }
		 
		   public void addExtension(String extension){
		      if(extension == null){
		         throw new RuntimeException("La extensión no puede ser null.");
		      }
		      extensions.add(extension);
		   }
		 
		   public void removeExtension(String extension){
		      extensions.remove(extension);
		   }
		 
		   public void clearExtensions(){
		      extensions.clear();
		   }
		 
		   public List<String> getExtensions(){
		      return extensions;
		   }
		
			@Override
			public boolean accept(File file) {
				if(file.isDirectory() || extensions.size()==0) { 
			         return true; 
			    } 
			    String nombreFichero = file.getName().toLowerCase(); 
			    for(String extension : extensions){
			       if(nombreFichero.endsWith(extension)){
			          return true;
			       }
			    }
			    return false;
			}
	}
}
