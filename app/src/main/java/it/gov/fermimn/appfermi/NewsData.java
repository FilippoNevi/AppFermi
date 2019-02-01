package it.gov.fermimn.appfermi;


import java.io.Serializable;

import android.graphics.Bitmap;

class NewsData implements Serializable{
	
	private static final long SERIAL_VERSION_UID = 1L;
		
	private String data;
	private String titolo;
	private int larghezzaImg;
	private int altezzaImg;
	
	private Bitmap.Config configImmagine;
	private int [] immagine;
	private String testo;
	
	
	//setter methods
	public void setData(String data){
		this.data = data;
	}
	public void setTitolo(String titolo){
		this.titolo = titolo;
	}
	public void setTesto(String testo){
		this.testo = testo;
	}
	
	//getter methods
	public String getData(){ return data; }
	public String getTitolo(){ return titolo; }
	public String getTesto(){ return testo; }
	
	public Bitmap getImmagine(){
		if (immagine != null){
			return Bitmap.createBitmap(immagine, larghezzaImg, altezzaImg, configImmagine);
		}
		return null;
	}
	
	
	public void setImmagine(Bitmap img){
		larghezzaImg = img.getWidth();
		altezzaImg = img.getHeight();
		configImmagine = img.getConfig();
		
		immagine = new int [larghezzaImg * altezzaImg];
		img.getPixels(immagine, 0, larghezzaImg, 0, 0, larghezzaImg, altezzaImg);
	}
}