import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

public class PieChart3D extends ApplicationFrame {

	public PieChart3D(final String title,int data[],String names[]) {

		super(title);
		final PieDataset dataset = createSampleDataset(data,names);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	private PieDataset createSampleDataset(int data[],String names[]) {

		final DefaultPieDataset dataset = new DefaultPieDataset( );
                dataset.setValue( names[0] , new Double( data[0] ) );  
                dataset.setValue( names[1] , new Double( data[1] ) );   
                dataset.setValue( names[2] , new Double( data[2] ) );   
 
                return dataset; 

	}

	private JFreeChart createChart(final PieDataset dataset) {

		final JFreeChart chart = ChartFactory.createPieChart3D("Graphical Analysis (Pie Chart)", // chart
																						// title
				dataset, // data
				true, // include legend
				true, false);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		return chart;

	}

	
}