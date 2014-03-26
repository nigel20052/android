package com.example.retrofit;

public class Listview {
	private String titulo;
    private String subtitulo;
 
    public Listview(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }
 
    public String getTitulo(){
        return titulo;
    }
 
    public String getSubtitulo(){
        return subtitulo;
    }
}
