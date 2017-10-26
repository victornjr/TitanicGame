//Victor Adair Najar
//Carlos Pedraza
//Proyecto Final POO

import java.awt.Image;

import javax.swing.ImageIcon;

//los icebergs heredan de Objetos Carril porque tienen el mismo funcionamiento que cualquier cosa que salga
public class Iceberg extends ObjetosCarril{
	
	public Iceberg(int carril){
		super(carril);
		this.imagen = new ImageIcon("iceberg-01.png").getImage();
	}
}
