package com.example.sumativa1seguridad.Model;


import java.time.LocalDate;
import java.util.List;

public class Recipe {
  private Long id;
  private String nombre;
  private String tipoCocina;    
  private String dificultad;   
  private String paisOrigen;    
  private List<String> ingredientes;
  private int tiempoCoccionMin;
  private boolean popular;
  private LocalDate fechaPublicacion;
  private String imagenFile;
  private String instrucciones;  
  
  public Recipe(Long id, String nombre, String tipoCocina, String dificultad, String paisOrigen,
                List<String> ingredientes, int tiempoCoccionMin, boolean popular,
                LocalDate fechaPublicacion, String imagenFile, String instrucciones) {
    this.id = id; this.nombre = nombre; this.tipoCocina = tipoCocina; this.dificultad = dificultad;
    this.paisOrigen = paisOrigen; this.ingredientes = ingredientes; this.tiempoCoccionMin = tiempoCoccionMin;
    this.popular = popular; this.fechaPublicacion = fechaPublicacion; this.imagenFile = imagenFile; this.instrucciones = instrucciones;
  }
  public Long getId() { return id; }
  public String getNombre() { return nombre; }
  public String getTipoCocina() { return tipoCocina; }
  public String getDificultad() { return dificultad; }
  public String getPaisOrigen() { return paisOrigen; }
  public List<String> getIngredientes() { return ingredientes; }
  public int getTiempoCoccionMin() { return tiempoCoccionMin; }
  public boolean isPopular() { return popular; }
  public LocalDate getFechaPublicacion() { return fechaPublicacion; }
  public String getImagenFile() { return imagenFile; }
  public String getInstrucciones() { return instrucciones; }
}
