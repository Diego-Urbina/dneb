package es.ucm.si.dneb.service.image.histograma;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.media.jai.Histogram;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.TiledImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.media.jai.codecimpl.util.RasterFactory;

import es.ucm.si.dneb.service.image.segmentation.LectorImageHDU;

import nom.tam.fits.BasicHDU;
import nom.tam.fits.Fits;
import nom.tam.util.ArrayFuncs;

/**
 * This class uses the DisplayHistogram component in an application.
 */
public class DisplayHistogramApp extends JPanel {

	private static final long serialVersionUID = -1999727268428404190L;
	private JButton jbutton;
	private LectorImageHDU l1;
	private PlanarImage im;
	private DisplayHistogram dh;

	/**
	 * The constructor for the class, which will create the user interface and
	 * register the mouse motion events.
	 */
	public DisplayHistogramApp() {
		
		this.setLayout(new BorderLayout());

		// Set the closing operation so the application is finished.
		jbutton = new JButton("CARGAR IMAGEN");
		jbutton.setIcon(new ImageIcon("images/load.png"));
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
				JAI.create("filestore", im, "Temp/im78.png", "PNG");

				this.removeAll();

				this.setLayout(new BorderLayout());

				jbutton = new JButton("CARGAR IMAGEN");
				jbutton.setIcon(new ImageIcon("images/abrir.gif"));
				jbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadImage(e);
					}
				});
				this.add(jbutton, BorderLayout.NORTH);
				
				PlanarImage image = JAI.create("fileload", "Temp/im78.png");
			    ParameterBlock pb = new ParameterBlock();
			    pb.addSource(image);
			    pb.add(null); // The ROI.
			    pb.add(1); // Samplings.
			    pb.add(1);
			    pb.add(new int[]{5000}); // Num. bins.
			    pb.add(new double[]{0}); // Min value to be considered.
			    pb.add(new double[]{50000}); // Max value to be considered.
			    // Creates the histogram.
			    PlanarImage temp = JAI.create("histogram", pb);
			    Histogram h = (Histogram)temp.getProperty("histogram");
				
				dh = new DisplayHistogram(h, "Histogram of " + retval);
				this.add(dh, BorderLayout.CENTER);
				setVisible(true);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private PlanarImage createPlanarImage(LectorImageHDU l) {
		int[] arrayDataAplanado = (int[]) ArrayFuncs.flatten(l.getArrayData());

		DataBufferInt dBuffer = new DataBufferInt(arrayDataAplanado, l
				.getWidth()
				* l.getHeight());
		SampleModel sm = RasterFactory.createBandedSampleModel(
				DataBuffer.TYPE_SHORT, l.getWidth(), l.getHeight(), 1);
		ColorModel cm = PlanarImage.createColorModel(sm);
		Raster raster = RasterFactory.createWritableRaster(sm, dBuffer,
				new Point(0, 0));
		TiledImage tiledImage = new TiledImage(0, 0, l.getWidth(), l
				.getHeight(), 0, 0, sm, cm);
		tiledImage.setData(raster);

		return tiledImage;
	}
}