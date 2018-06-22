import java.awt.*;
import javax.swing.*;

public class BarChartClass extends JPanel {
	private double[] value;
	private String[] Referees;
	private String title;
	private int height,count;
	
	public BarChartClass(double[] val, String[] lang, String t) {
		Referees = lang;
		value = val;
		title = t;
	}
	public void paintComponent(Graphics graphics) {
		 
		super.paintComponent(graphics);
		if (value == null || value.length == 0)
			return;
		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < value.length; i++) {
			if (minValue > value[i])
				minValue = value[i];
			if (maxValue < value[i])
				maxValue = value[i];
		}
		Dimension dim = getSize();
		int clientWidth = dim.width;
		int clientHeight = dim.height;
		int barWidth = clientWidth / value.length;
		Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
		FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
		Font labelFont = new Font("Arial", Font.PLAIN, 13);
		FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);
		int titleWidth = titleFontMetrics.stringWidth(title);
		int q = titleFontMetrics.getAscent();
		int p = (clientWidth - titleWidth) / 2;
		graphics.setFont(titleFont);
		graphics.drawString(title, p, q);
		int top = titleFontMetrics.getHeight()+30;// increase/decrease the height of the bars.
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		q = clientHeight - labelFontMetrics.getDescent();
		graphics.setFont(labelFont);
		for (int j = 0; j < value.length; j++) {
			
			int valueP = j * barWidth +1;
			int valueQ = top;
			height = (int) (value[j] * scale);
			if (value[j] >= 0)
				valueQ += (int) ((maxValue - value[j]) * scale);
			else {
				valueQ += (int) (maxValue * scale);
				height = -height;
			}
		
			graphics.setColor(Color.blue);
			graphics.fillRect(valueP, valueQ, barWidth - 2, height);
			graphics.setColor(Color.black);
			if (graphics!=null)
			graphics.drawRect(valueP, valueQ, barWidth - 2, height);// outside of the rectangle
			int labelWidth = labelFontMetrics.stringWidth(Referees[j]);
			p = j * barWidth + (barWidth - labelWidth) / 2;
			graphics.drawString(Referees[j], p, q);
			
		}
		for (int i = 0; i < value.length; i++) {
			count = count +barWidth;
			int positionX=(count-barWidth)+ barWidth/2;
			
			if (maxValue == value[i]){
				graphics.drawString(String.valueOf(this.value[i]), count-70, (int) ((maxValue) * scale)-360);//for the highest bar. 
			}
			else {
			graphics.drawString(String.valueOf(value[i]), positionX, (int) ((maxValue - value[i]) * scale)+30);//add the numbers above each bar
			}
			}
		
	}

}