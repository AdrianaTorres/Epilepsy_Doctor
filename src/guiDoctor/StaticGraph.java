package guiDoctor;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class StaticGraph {
	public StaticGraph(List <Double>[] data,String plotType) {

		XYChart chart = new XYChartBuilder().width(400).height(200).title(plotType).build();
		chart.addSeries(plotType, data[0], data[1]);
		setLayout(new BorderLayout());

		XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
		add(chartPane);
	}
}
