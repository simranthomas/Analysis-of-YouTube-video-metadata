import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart3D extends ApplicationFrame {

	public BarChart3D(final String title,int data[],String names[]) {

		super(title);

		final CategoryDataset dataset = createDataset(data,names);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 500));
		setContentPane(chartPanel);

	}

	private CategoryDataset createDataset(int data[],String names[]) {

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(data[0], names[0], "");
		dataset.addValue(data[1], names[1], "");
		dataset.addValue(data[2], names[2], "");
		
		return dataset;

	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart3D("Graphical Analysis (Bar Chart)", // chart
																					// title
				"Category", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);

		final CategoryPlot plot = chart.getCategoryPlot();
		final CategoryAxis axis = plot.getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0));

		final CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelsVisible(true);
		final BarRenderer r = (BarRenderer) renderer;

		return chart;
	}

	
}