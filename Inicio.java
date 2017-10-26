//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Inicio extends JFrame{
	
	public Inicio(){
		super("Titanic");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Portada inicio = new Portada();
		this.add(inicio);
		this.pack();
		int pantallaX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int pantallaY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((pantallaX-this.getWidth())/2,(pantallaY-this.getHeight())/2);
		this.setVisible(true);
		
		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				if((e.getX() > 80 && e.getX() < 520) && (e.getY() > 540 && e.getY() < 640)){
					Inicio.this.dispose();
					Juego a = new Juego();
				}
			}
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseReleased(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				
			}
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
	}
	public static void main(String[] args) {
		Inicio jugar = new Inicio();
	}

}
