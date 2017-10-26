//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import javax.swing.ImageIcon;

public class Vidas extends ObjetosCarril {

	public Vidas(int carril) {
		super(carril);
		this.imagen = new ImageIcon("corazon-01.png").getImage();
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
