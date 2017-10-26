//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Timer;

import javax.swing.JFrame;

public class Juego extends JFrame{
	//Elementos del juego
	private Mar juegoMar;
	
	public Juego(){
		super("Titanic");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.juegoMar = new Mar();
		this.add(juegoMar);
		this.pack();
		int pantallaX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int pantallaY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((pantallaX-this.getWidth())/2,(pantallaY-this.getHeight())/2);
		this.setVisible(true);
	}

}
