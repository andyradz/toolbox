package com.codigo.aplios.toolbox.core.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Charts2D extends JFrame {

	public Charts2D() {

		super("2D Charts");
		this.setSize(720, 280);
		this.getContentPane()
			.setLayout(new GridLayout(1, 3, 10, 0));
		this.getContentPane()
			.setBackground(Color.white);

		int[] xData = new int[8];
		int[] yData = new int[8];
		for (int i = 0; i < xData.length; i++) {
			xData[i] = i;
			yData[i] = (int) (Math.random() * 100);
			if (i > 0)
				yData[i] = (yData[i - 1] + yData[i]) / 2;
		}

		JChart2D chart = new JChart2D(JChart2D.LineChart, xData.length, xData, yData, "Line Chart");
		chart.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
		chart.setLineColor(new Color(0, 28, 28));
		this.getContentPane()
			.add(chart);

		chart = new JChart2D(JChart2D.ColumnChart, xData.length, xData, yData, "Column Chart");
		GradientPaint gp = new GradientPaint(0, 100, Color.white, 0, 300, Color.blue, true);
		chart.setGradient(gp);
		chart.setEffectIndex(JChart2D.Gradientffect);
		chart.setDrawShadow(true);
		this.getContentPane()
			.add(chart);

		chart = new JChart2D(JChart2D.PieChart, xData.length, xData, yData, "Pie Chart");
		ImageIcon icon = new ImageIcon("largeJava2slogo.GIF");
		chart.setForegroundImage(icon.getImage());
		chart.setEffectIndex(JChart2D.ImageEffect);
		chart.setDrawShadow(true);
		this.getContentPane()
			.add(chart);

		WindowListener wndCloser = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}
		};
		this.addWindowListener(wndCloser);

		this.setVisible(true);
	}

	public static void main(String argv[]) {

		new Charts2D();
	}

}

class JChart2D extends JPanel {
	public static final int LineChart = 0;

	public static final int ColumnChart = 1;

	public static final int PieChart = 2;

	public static final int PLainEffect = 0;

	public static final int Gradientffect = 1;

	public static final int ImageEffect = 2;

	protected int m_chartType = JChart2D.LineChart;

	protected JLabel titleLabel;

	protected ChartPanel chartPanel;

	protected int dataLength;

	protected int[] xData;

	protected int[] yData;

	protected int xMin;

	protected int xMax;

	protected int yMin;

	protected int yMax;

	protected double[] pieData;

	protected int m_effectIndex = JChart2D.PLainEffect;

	protected Stroke stroke;

	protected GradientPaint gradient;

	protected Image foregroundImage;

	protected Color lineColor = Color.black;

	protected Color columnColor = Color.blue;

	protected int columnWidth = 12;

	protected boolean drawShadow = false;

	public JChart2D(int type, int nData, int[] yData, String text) {

		this(type, nData, null, yData, text);
	}

	public JChart2D(int type, int nData, int[] xD, int[] yD, String text) {

		super(new BorderLayout());
		this.setBackground(Color.white);
		this.titleLabel = new JLabel(text, SwingConstants.CENTER);
		this.add(this.titleLabel, BorderLayout.NORTH);

		this.m_chartType = type;

		if (this.xData == null) {
			this.xData = new int[nData];
			for (int k = 0; k < nData; k++)
				this.xData[k] = k;
		}
		if (yD == null)
			throw new IllegalArgumentException("yData can't be null");
		if (nData > yD.length)
			throw new IllegalArgumentException("Insufficient yData length");
		if (nData > xD.length)
			throw new IllegalArgumentException("Insufficient xData length");
		this.dataLength = nData;
		this.xData = xD;
		this.yData = yD;

		this.xMin = this.xMax = 0; // To include 0 into the interval
		this.yMin = this.yMax = 0;
		for (int k = 0; k < this.dataLength; k++) {
			this.xMin = Math.min(this.xMin, this.xData[k]);
			this.xMax = Math.max(this.xMax, this.xData[k]);
			this.yMin = Math.min(this.yMin, this.yData[k]);
			this.yMax = Math.max(this.yMax, this.yData[k]);
		}
		if (this.xMin == this.xMax)
			this.xMax++;
		if (this.yMin == this.yMax)
			this.yMax++;

		if (this.m_chartType == JChart2D.PieChart) {
			double sum = 0;
			for (int k = 0; k < this.dataLength; k++) {
				this.yData[k] = Math.max(this.yData[k], 0);
				sum += this.yData[k];
			}
			this.pieData = new double[this.dataLength];
			for (int k = 0; k < this.dataLength; k++)
				this.pieData[k] = (this.yData[k] * 360.0) / sum;
		}

		this.chartPanel = new ChartPanel();
		this.add(this.chartPanel, BorderLayout.CENTER);
	}

	public void setEffectIndex(int effectIndex) {

		this.m_effectIndex = effectIndex;
		this.repaint();
	}

	public int getEffectIndex() {

		return this.m_effectIndex;
	}

	public void setStroke(Stroke s) {

		this.stroke = s;
		this.chartPanel.repaint();
	}

	public void setForegroundImage(Image img) {

		this.foregroundImage = img;
		this.repaint();
	}

	public Image getForegroundImage() {

		return this.foregroundImage;
	}

	public Stroke getStroke() {

		return this.stroke;
	}

	public void setGradient(GradientPaint g) {

		this.gradient = g;
		this.repaint();
	}

	public GradientPaint getGradient() {

		return this.gradient;
	}

	public void setColumnWidth(int c) {

		this.columnWidth = c;
		this.chartPanel.calcDimensions();
		this.chartPanel.repaint();
	}

	public int setColumnWidth() {

		return this.columnWidth;
	}

	public void setColumnColor(Color c) {

		this.columnColor = c;
		this.chartPanel.repaint();
	}

	public Color getColumnColor() {

		return this.columnColor;
	}

	public void setLineColor(Color c) {

		this.lineColor = c;
		this.chartPanel.repaint();
	}

	public Color getLineColor() {

		return this.lineColor;
	}

	public void setDrawShadow(boolean d) {

		this.drawShadow = d;
		this.chartPanel.repaint();
	}

	public boolean getDrawShadow() {

		return this.drawShadow;
	}

	class ChartPanel extends JComponent {
		int xMargin = 5;

		int yMargin = 5;

		int pieGap = 10;

		int m_x;

		int m_y;

		int m_w;

		int m_h;

		ChartPanel() {

			this.enableEvents(ComponentEvent.COMPONENT_RESIZED);
		}

		@Override
		protected void processComponentEvent(ComponentEvent e) {

			this.calcDimensions();
		}

		public void calcDimensions() {

			Dimension d = this.getSize();
			this.m_x = this.xMargin;
			this.m_y = this.yMargin;
			this.m_w = d.width - (2 * this.xMargin);
			this.m_h = d.height - (2 * this.yMargin);
			if (JChart2D.this.m_chartType == JChart2D.ColumnChart) {
				this.m_x += JChart2D.this.columnWidth / 2;
				this.m_w -= JChart2D.this.columnWidth;
			}
		}

		public int xChartToScreen(int x) {

			return this.m_x + (((x - JChart2D.this.xMin) * this.m_w) / (JChart2D.this.xMax - JChart2D.this.xMin));
		}

		public int yChartToScreen(int y) {

			return this.m_y + (((JChart2D.this.yMax - y) * this.m_h) / (JChart2D.this.yMax - JChart2D.this.yMin));
		}

		@Override
		public void paintComponent(Graphics g) {

			int x0 = 0;
			int y0 = 0;
			if (JChart2D.this.m_chartType != JChart2D.PieChart) {
				g.setColor(Color.black);
				x0 = this.xChartToScreen(0);
				g.drawLine(x0, this.m_y, x0, this.m_y + this.m_h);
				y0 = this.yChartToScreen(0);
				g.drawLine(this.m_x, y0, this.m_x + this.m_w, y0);
			}

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			if (JChart2D.this.stroke != null)
				g2.setStroke(JChart2D.this.stroke);

			GeneralPath path = new GeneralPath();
			switch (JChart2D.this.m_chartType) {
			case LineChart:
				g2.setColor(JChart2D.this.lineColor);
				path.moveTo(this.xChartToScreen(JChart2D.this.xData[0]), this.yChartToScreen(JChart2D.this.yData[0]));
				for (int k = 1; k < JChart2D.this.dataLength; k++)
					path.lineTo(this.xChartToScreen(JChart2D.this.xData[k]),
							this.yChartToScreen(JChart2D.this.yData[k]));
				g2.draw(path);
				break;

			case ColumnChart:
				for (int k = 0; k < JChart2D.this.dataLength; k++) {
					JChart2D.this.xMax++;
					int x = this.xChartToScreen(JChart2D.this.xData[k]);
					int w = JChart2D.this.columnWidth;
					int y1 = this.yChartToScreen(JChart2D.this.yData[k]);
					int y = Math.min(y0, y1);
					int h = Math.abs(y1 - y0);
					Shape rc = new Rectangle2D.Double(x, y, w, h);
					path.append(rc, false);
					JChart2D.this.xMax--;
				}

				if (JChart2D.this.drawShadow) {
					AffineTransform s0 = new AffineTransform(1.0, 0.0, 0.0, -1.0, x0, y0);
					s0.concatenate(AffineTransform.getScaleInstance(1.0, 0.5));
					s0.concatenate(AffineTransform.getShearInstance(0.5, 0.0));
					s0.concatenate(new AffineTransform(1.0, 0.0, 0.0, -1.0, -x0, y0));
					g2.setColor(Color.gray);
					Shape shadow = s0.createTransformedShape(path);
					g2.fill(shadow);
				}

				if ((JChart2D.this.m_effectIndex == JChart2D.Gradientffect) && (JChart2D.this.gradient != null)) {
					g2.setPaint(JChart2D.this.gradient);
					g2.fill(path);
				} else if ((JChart2D.this.m_effectIndex == JChart2D.ImageEffect)
						&& (JChart2D.this.foregroundImage != null))
					this.fillByImage(g2, path, 0);
				else {
					g2.setColor(JChart2D.this.columnColor);
					g2.fill(path);
				}
				g2.setColor(JChart2D.this.lineColor);
				g2.draw(path);
				break;

			case PieChart:
				double start = 0.0;
				double finish = 0.0;
				int ww = this.m_w - (2 * this.pieGap);
				int hh = this.m_h - (2 * this.pieGap);
				if (JChart2D.this.drawShadow) {
					ww -= this.pieGap;
					hh -= this.pieGap;
				}

				for (int i = 0; i < JChart2D.this.dataLength; i++) {
					finish = start + JChart2D.this.pieData[i];
					double f1 = Math.min(90 - start, 90 - finish);
					double f2 = Math.max(90 - start, 90 - finish);
					Shape shp = new Arc2D.Double(this.m_x, this.m_y, ww, hh, f1, f2 - f1, Arc2D.PIE);
					double f = (((f1 + f2) / 2) * Math.PI) / 180;
					AffineTransform s1 =
							AffineTransform.getTranslateInstance(this.pieGap * Math.cos(f), -this.pieGap * Math.sin(f));
					s1.translate(this.pieGap, this.pieGap);
					Shape piece = s1.createTransformedShape(shp);
					path.append(piece, false);
					start = finish;
				}

				if (JChart2D.this.drawShadow) {
					AffineTransform s0 = AffineTransform.getTranslateInstance(this.pieGap, this.pieGap);
					g2.setColor(Color.gray);
					Shape shadow = s0.createTransformedShape(path);
					g2.fill(shadow);
				}

				if ((JChart2D.this.m_effectIndex == JChart2D.Gradientffect) && (JChart2D.this.gradient != null)) {
					g2.setPaint(JChart2D.this.gradient);
					g2.fill(path);
				} else if ((JChart2D.this.m_effectIndex == JChart2D.ImageEffect)
						&& (JChart2D.this.foregroundImage != null))
					this.fillByImage(g2, path, 0);
				else {
					g2.setColor(JChart2D.this.columnColor);
					g2.fill(path);
				}

				g2.setColor(JChart2D.this.lineColor);
				g2.draw(path);
				break;
			}
		}

		protected void fillByImage(Graphics2D g2, Shape shape, int xOffset) {

			if (JChart2D.this.foregroundImage == null)
				return;
			int wImg = JChart2D.this.foregroundImage.getWidth(this);
			int hImg = JChart2D.this.foregroundImage.getHeight(this);
			if ((wImg <= 0) || (hImg <= 0))
				return;
			g2.setClip(shape);
			Rectangle bounds = shape.getBounds();
			for (int i = bounds.x + xOffset; i < (bounds.x + bounds.width); i += wImg)
				for (int j = bounds.y; j < (bounds.y + bounds.height); j += hImg)
					g2.drawImage(JChart2D.this.foregroundImage, i, j, this);
		}
	}
}
