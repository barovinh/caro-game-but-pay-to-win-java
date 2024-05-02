package CustomComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class ButtonBoard extends JButton{

	private boolean over;
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColorClick() {
		return colorClick;
	}
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private int radius = 0;
	private Color borderColor;
	public ButtonBoard() {
		// TODO Auto-generated constructor stub
		setColor(Color.white);
		colorOver = new Color(179,250,160);
		colorClick =  new Color(152,184,144);
		borderColor = new Color(30,135,56);
		setContentAreaFilled(false);
	}
    private int shadowSize = 4;
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
	//    g2d.fillRect(shadowSize, shadowSize, getWidth() - shadowSize * 2, getHeight() - shadowSize * 2);
	//    g2d.setColor(new Color(0xabc4c4));
	    int shadowOpacity = 50;
        Color shadowColor = new Color(0, 0, 0, shadowOpacity);
        g2d.setColor(shadowColor);
		g2d.fillRoundRect(0,0, getWidth(), getHeight(),15,15);	
		
		super.paintComponent(g);
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
}
