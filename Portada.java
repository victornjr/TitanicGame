//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Portada extends JPanel {
	private Image fondo;
	
	public Portada(){
		super();
		this.setPreferredSize(new Dimension(600,750));
		this.fondo = new ImageIcon("portada.png").getImage();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(this.fondo, 0, 0,600,750, this);
	}
}
