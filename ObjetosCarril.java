//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Image;

public class ObjetosCarril {
	protected int carril,
				posX,
				posY;
	protected boolean puntos,
					choque;
	
	protected Image imagen;
	
	public ObjetosCarril(int carril){
		this.choque = false;
		this.carril = carril;
		this.puntos = false;
		//la posición en x depende del carril en el que se crea.
		//se divide en 3 carriles de 200 de ancho cada uno
		//por esto si se multiplica 0*200 da 0, 1*200 da 200 y 2*200 da 400, son los inicios de cada carril
		this.posX = this.carril*200 + 10;
		this.posY = 20;
	}
	
	public void moverse(){
		if(this.posY >= 470){
			this.posY +=3;
		}
		else{
			this.posY +=2;
		}
		
	}
	
	public boolean chocar(int carrilBarco){
		if(this.posY==470 && carrilBarco==this.carril){
			this.choque=true;
			//System.out.println(this.choque);
		}
		else{
			this.choque=false;
		}
		
		return choque;
	}
	
	public boolean ganarPuntos(int carrilBarco){
		if(this.posY==470 && carrilBarco!=this.carril){
			this.puntos = true;
		}
		else{
			this.puntos = false;
		}
		return this.puntos;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
	
	public Image getImagen(){
		return this.imagen;
	}
}
