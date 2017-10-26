//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Mar extends JPanel implements Runnable{	
	private Image fondo;
	private ArrayList<Image> vidas;
	//
	private ArrayList<ObjetosCarril > 	carril1,
										carril2,
										carril3;
	private Barco titanic;
	private Random rdm;
	private int sigCarril,
				puntos,
				cuenta;//la variable cuenta dice cada cuando aparecen los Icebergs, personas y vidas
	private boolean pausa,
					finDelJuego,
					dibuja1;
	
	
	public Mar(){
		super();
		this.setPreferredSize(new Dimension(600,750));
		this.fondo = new ImageIcon("mar1.png").getImage();
		this.titanic = new Barco();
		this.carril1 = new ArrayList<ObjetosCarril>();
		this.carril2 = new ArrayList<ObjetosCarril>();
		this.carril3 = new ArrayList<ObjetosCarril>();
		this.cuenta =0;
		this.dibuja1 = true;
		this.sigCarril = 0;
		this.pausa = false;
		this.finDelJuego = false;
		this.vidas = new ArrayList<>();
		for(int i=0;i<3;i++){
			this.vidas.add(new ImageIcon("corazon.png").getImage());
		}
		this.rdm = new Random();
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {
				
			}	
			
			public void keyPressed(KeyEvent e) {
				int tecla= e.getKeyCode();
				if (tecla==KeyEvent.VK_LEFT){
					if(!Mar.this.pausa){
						Mar.this.titanic.mover(-1);
						Mar.this.repaint();
					}
				  }
				  
				if (tecla==KeyEvent.VK_RIGHT){
					if(!Mar.this.pausa){
						Mar.this.titanic.mover(1);
						Mar.this.repaint();
					}
				}
				if(tecla == KeyEvent.VK_P){
					Mar.this.pausar();
					Mar.this.repaint();
				}
			}
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		Thread hilo = new Thread(this);
		hilo.start();
		
	}
	
	public void pausar(){
		this.pausa = !this.pausa;
	}
	
	public void agregarObjeto(){
		//este método agrega los Icebergs a un ArrayList dependiendo del carril que se genere
		if(this.sigCarril == 0 && this.dibuja1){ //la variable dibuja previene que se dibujen muchos icebergs
			if(this.cuenta%17 == 0){
				this.carril1.add(new Personas(0));
				this.dibuja1 = false;
			}
			else if(this.cuenta%67 == 0 && this.vidas.size()<5){
				this.carril1.add(new Vidas(0));
				this.dibuja1 = false;
			}
			else{
				this.carril1.add(new Iceberg(0));
				this.dibuja1 = false;
			}
		}
		if(this.sigCarril == 1 && this.dibuja1){
			if(this.cuenta%17 == 0){
				this.carril2.add(new Personas(1));
				this.dibuja1 = false;
			}
			else if(this.cuenta%67 == 0 && this.vidas.size()<5){
				this.carril2.add(new Vidas(1));
				this.dibuja1 = false;
			}
			else{
				this.carril2.add(new Iceberg(1));
				this.dibuja1 = false;
			}
		}
		if(this.sigCarril == 2 && this.dibuja1){
			if(this.cuenta%17 == 0){
				this.carril3.add(new Personas(2));
				this.dibuja1 = false;
			}
			else if(this.cuenta%67 == 0 && this.vidas.size()<6){
				this.carril3.add(new Vidas(2));
				this.dibuja1 = false;
			}
			else{
				this.carril3.add(new Iceberg(2));
				this.dibuja1 = false;
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//dibuja el fondo del mar
		g.drawImage(this.fondo, 0, 0, 600, 750, this);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 50);
		g.setColor(Color.WHITE);
		g.setFont(new Font("digital-7", Font.PLAIN, 40));
		g.drawString("Vidas:", 20, 40);
		for(int i=0; i<this.vidas.size(); i++){
			g.drawImage(this.vidas.get(i), 130 + i*30, 15, 20, 20, this);
		}
		g.drawString("SCORE: " + this.puntos, 350, 40);
		
		//dibuja los ObjetosCarril
		for(ObjetosCarril a:this.carril1){
			g.drawImage(a.getImagen(), a.getPosX(), a.getPosY(),180,110, this);
		}
		for(ObjetosCarril a:this.carril2){
			g.drawImage(a.getImagen(), a.getPosX(), a.getPosY(),180,110, this);
		}
		for(ObjetosCarril a:this.carril3){
				g.drawImage(a.getImagen(), a.getPosX(), a.getPosY(),180,110, this);
		}
		
		//dibuja al titanic
		g.drawImage(this.titanic.getBarco(), this.titanic.getPosX(), this.titanic.getPosY(), 150, 150, this);
		
		if(this.pausa && !this.finDelJuego){
			g.drawString("Pausa", 260, 370);
		}
		if(this.pausa && this.finDelJuego){
			g.drawString("Fin del Juego", 200, 370);
		}
	}
	
	public void perder(){
		this.finDelJuego = true;
		this.pausa = true;
		JOptionPane.showMessageDialog(null, "Gracias por Jugar\nScore: " + this.puntos + "\nCierra la ventana para salir");
	}

	public void run() {
		int crear=50;
		while(!this.finDelJuego){
			try{
				if(!this.pausa){
					if(crear == 50){
						this.dibuja1 = true;
						this.sigCarril = this.rdm.nextInt(3); // el random da un número de 0 a 3 que da el carril en el que sale el siguiente Iceberg
						this.agregarObjeto();
					}
					if(crear == 100){
						this.dibuja1 = true;
						this.sigCarril = this.rdm.nextInt(3); 
						this.agregarObjeto();
						crear = 0; //crea se reinicia a 0 cuando se crea un elemento nuevo
					}
					crear++; // esta variable se suma al mismo tiempo que la posY de cada Iceberg
					this.cuenta++;
					for(ObjetosCarril a:this.carril1){
						a.moverse();
						if(a.chocar(this.titanic.getCarril())){
							if(a.getClass().getSimpleName().equals("Vidas")){
								this.vidas.add(new ImageIcon("corazon.png").getImage());
							}
							else if(a.getClass().getSimpleName().equals("Personas")){
								this.puntos+=10;
							}
							else{
								if(this.vidas.size() == 1){
									this.perder();
								}
								else{
									this.vidas.remove(1);
								}
							}
						}
						if(a.ganarPuntos(this.titanic.getCarril())){
							this.puntos++;
						}
					}
					for(ObjetosCarril a:this.carril2){
						a.moverse();
						if(a.chocar(this.titanic.getCarril())){
							if(a.getClass().getSimpleName().equals("Vidas")){
								this.vidas.add(new ImageIcon("corazon.png").getImage());
							}
							else if(a.getClass().getSimpleName().equals("Personas")){
								this.puntos+=10;
							}
							else{
								if(this.vidas.size() == 1){
									this.perder();
								}
								else{
									this.vidas.remove(1);
								}
							}
						}
						if(a.ganarPuntos(this.titanic.getCarril())){
							this.puntos++;
						}
					}
					for(ObjetosCarril a:this.carril3){
						a.moverse();
						if(a.chocar(this.titanic.getCarril())){
							if(a.getClass().getSimpleName().equals("Vidas")){
								this.vidas.add(new ImageIcon("corazon.png").getImage());
							}
							else if(a.getClass().getSimpleName().equals("Personas")){
								this.puntos+=10;
							}
							else{
								if(this.vidas.size() == 1){
									this.perder();
								}
								else{
									this.vidas.remove(1);
								}
							}
						}
						if(a.ganarPuntos(this.titanic.getCarril())){
							this.puntos++;
						}
					}
					this.repaint();
					Thread.sleep(10);
				}
				else{
					Thread.sleep(10);
				}
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
	
	public Barco getBarco(){
		return this.titanic;
	}
	public boolean getPausa(){
		return this.pausa;
	}
}
