//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Image;

import javax.swing.ImageIcon;

//las personas heredan de Objetos Carril porque tienen el mismo funcionamiento que cualquier cosa que salga
public class Personas extends ObjetosCarril{ 
	
	public Personas(int carril){
		super(carril);
		this.imagen = new ImageIcon("persona-01.png").getImage();
	}
	
	public boolean chocar(int carrilBarco){
		if(this.posY==500 && carrilBarco==this.carril){
			this.choque=true;
			//System.out.println(this.choque);
		}
		else{
			this.choque=false;
		}
		
		return choque;
	}
}
