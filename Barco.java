//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Barco{
	private int posX,
				posY,
				carril;
	private Image barco;
	
	public Barco(){
		this.carril = 1;
		this.posX = carril*200 + 25;
		this.posY = 600;
		this.barco = new ImageIcon("titanic.png").getImage();
	}
	 
	public void mover(int direccion){
		if(this.carril == 0){
			if(direccion == 1){ //se mueve derecha
				this.carril += direccion;
				this.posX = carril*200 + 25;
			}
		}
		else if(this.carril == 2){
			if(direccion == -1){ //se mueve izquierda
				this.carril += direccion;
				this.posX = carril*200 + 25;
			}
		}
		else{
			if(direccion == -1){ //se mueve izquierda
				this.carril += direccion;
				this.posX = carril*200 + 25;
			}
			if(direccion == 1){ //se mueve derecha
				this.carril += direccion;
				this.posX = carril*200 + 25;
			}
		}
		
	}
	
	public Image getBarco(){
		return this.barco;
	}
	
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
	
	public int getCarril(){
		return this.carril;
	}
}
