/*
 * Created on May 22, 2005
 * @author Rafael Santos (rafael.santos@lac.inpe.br)
 * 
 * Part of the Java Advanced Imaging Stuff site
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI)
 * 
 * STATUS: Complete.
 * 
 * Redistribution and usage conditions must be done under the
 * Creative Commons license:
 * English: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.en
 * Portuguese: http://creativecommons.org/licenses/by-nc-sa/2.0/br/deed.pt
 * More information on design and applications are on the projects' page
 * (http://www.lac.inpe.br/~rafael.santos/Java/JAI).
 */
package es.ucm.si.dneb.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RasterFactory;
import javax.media.jai.TiledImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.util.ArrayFuncs;
import es.ucm.si.dneb.domain.Imagen;
import es.ucm.si.dneb.service.busquedaDobles.ServiceBusquedaDobles;
import es.ucm.si.dneb.service.gestionTareas.ServicioGestionTareas;
import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;
import es.ucm.si.dneb.service.inicializador.ContextoAplicacion;
import es.ucm.si.dneb.service.math.DecimalCoordinate;
import es.ucm.si.dneb.test.ServicioBusquedaDoblesTest;

/**
 * This class uses an instance of DisplayJAIWithPixelInfo to display an image in
 * an application.
 */
public class FitsCoordinateViewerPanel extends JPanel implements
		MouseInputListener {

	private static final long serialVersionUID = -7389267318471409502L;
	private JLabel label; // a label that will contain information on the pixels
	// of the image being displayed.
	private JButton jbutton;
	private PlanarImage input1;
	private PixelInfoViewer dj; // an instance of the display component.
	private LectorImageHDU l1;
	
	private PlanarImage input;
	private PlanarImage im;
	private double ar;
	private double dec;
	private Imagen imagen;
	private ServicioGestionTareas servicioGestionTareas;
	private ServiceBusquedaDobles serviceBusquedaDobles;
	/**
	 * The constructor of the class, which sets the frame appearance and creates
	 * an instance of the modified display class.
	 * 
	 * @param image
	 */
	public FitsCoordinateViewerPanel() {
		
		servicioGestionTareas =(ServicioGestionTareas) ContextoAplicacion.getApplicationContext().getBean("servicioGestionTareas");
		serviceBusquedaDobles=(ServiceBusquedaDobles)ContextoAplicacion.getApplicationContext().getBean("serviceBusquedaDobles");
		
		// Get the JFrame's ContentPane.
		this.setLayout(new BorderLayout());
		label = new JLabel("???");
		this.add(label, BorderLayout.SOUTH);
		// Set the closing operation so the application is finished.
		jbutton = new JButton("CARGAR IMAGEN");
		jbutton.setIcon(new ImageIcon("images/abrir.gif"));
		jbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadImage(e);
			}
		});
		this.add(jbutton, BorderLayout.NORTH);
		
		setVisible(true); // show the frame.
	}

	private void loadImage(ActionEvent e) {
		
		
		JFileChooser fc = new JFileChooser(".");
				String file1;
		int retval = fc.showOpenDialog(this);
		try {
			if (retval == JFileChooser.APPROVE_OPTION) {
				file1 = fc.getSelectedFile().toString();
				File dir = new File("Temp");
				dir.mkdir();
				
				Fits imagenFITS = new Fits(new File(file1));
				BasicHDU imageHDU = imagenFITS.getHDU(0);
				l1 = new LectorImageHDU(imageHDU, file1);
				im = createPlanarImage(l1);
				JAI.create("filestore", im, "Temp/im3.png", "PNG");
				input1 = JAI.create("fileload", "Temp/im3.png");
				
				
				imagen=this.servicioGestionTareas.getImagenByPath(file1);
				
				
			    float scale = 1000/100f;
			    ParameterBlock pb = new ParameterBlock();
			    pb.addSource(input1);
			    pb.add(scale);
			    pb.add(scale);
			    pb.add(0.0F);
			    pb.add(0.0F);
			    pb.add(new InterpolationNearest());
			    
			    
			    // Creates a new, scaled image and uses it on the DisplayJAI component
			    input = JAI.create("scale", pb);
				
			    this.removeAll();
			    
			    this.setLayout(new BorderLayout());
			   
				dj = new PixelInfoViewer(input);
				this.add(new JScrollPane(dj), BorderLayout.CENTER);
				
				dj.addMouseMotionListener(this);
				
				label = new JLabel("???");
				this.add(label, BorderLayout.SOUTH);
				// Set the closing operation so the application is finished.
				jbutton = new JButton("CARGAR IMAGEN");
				jbutton.setIcon(new ImageIcon("images/abrir.gif"));
				jbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadImage(e);
					}
				});
				this.add(jbutton, BorderLayout.NORTH);
				
				setVisible(true); // show the frame.


				
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
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
	/**
	 * This method will be executed when the mouse is moved over the extended
	 * display class.
	 * 
	 * @param e
	 *            the mouse event
	 */
	public void mouseMoved(MouseEvent e) {
		
		DecimalCoordinate dc=serviceBusquedaDobles.pixelToCoordinatesConverter(imagen, im.getWidth(), im.getHeight(), e.getX(), e.getY());
		
		String pos = "(" + e.getX() + "," + e.getY() + ") " +"("+dc.toString() +")";
		
		
		label.setText(pos + dj.getPixelInfo()); // just update the label with
		// the
		// DisplayJAIWithPixelInfo instance info.
	}

	/**
	 * This method will not do anything - it is here just to keep the
	 * MouseMotionListener interface happy.
	 */
	public void mouseDragged(MouseEvent e) {
	}

	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

} // end main