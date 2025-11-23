package com.example.sumativa1seguridad.Servicio;

import com.example.sumativa1seguridad.Model.Recipe;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {

  private final List<Recipe> data = List.of(
    new Recipe(1L,"Pasta al Pesto","Italiana","Fácil","Italia",
      List.of("pasta","albahaca","ajo","parmesano","piñones","aceite"),
      20,true, LocalDate.now().minusDays(2),
      "pastaalpesto.jpg",
      "Cocina la pasta. Procesa albahaca, ajo, piñones, parmesano y aceite hasta emulsionar. Mezcla con la pasta y sirve."),
    new Recipe(2L,"Empanada de Pino","Chilena","Media","Chile",
      List.of("carne","cebolla","huevo","aceitunas","pasas","harina"),
      45,true, LocalDate.now().minusDays(10),
      "empanada.jpg",
      "Prepara el pino sofriendo cebolla y carne; sazona. Arma la masa, rellena con pino, huevo y aceituna; hornea."),
    new Recipe(3L,"Ceviche Clásico","Peruana","Media","Perú",
      List.of("pescado","limón","cebolla morada","cilantro","ají"),
      25,false, LocalDate.now().minusDays(1),
      "ceviche.jpg",
      "Corta el pescado en cubos, cubre con jugo de limón, agrega cebolla, cilantro y ají. Reposa y sirve frío."),
    new Recipe(4L,"Sopaipillas","Chilena","Fácil","Chile",
      List.of("zapallo","harina","manteca","sal"),
      30,false, LocalDate.now().minusDays(5),
      "sopaipillas.jpg",
      "Haz un puré de zapallo, mezcla con harina y grasa, amasa, estira y fríe hasta dorar."),
    new Recipe(5L,"Ramen Casero","Japonesa","Difícil","Japón",
      List.of("caldo","fideos","huevo","chashu","nori"),
      90,true, LocalDate.now().minusDays(3),
      "ramen.jpg",
      "Prepara caldo concentrado, cocina fideos, arma el bowl con chashu, huevo marinado y nori."),
    new Recipe(6L,"Tacos al Pastor","Mexicana","Media","México",
      List.of("cerdo","achiote","piña","cebolla","cilantro"),
      40,true, LocalDate.now().minusDays(8),
      "tacos.jpg",
      "Marina cerdo en achiote, asa, pica. Sirve en tortillas con piña, cebolla y cilantro."),
    new Recipe(7L,"Paella","Española","Difícil","España",
      List.of("arroz","mariscos","azafrán","caldo","pimentón","pollo"),
      80,false, LocalDate.now().minusDays(12),
      "paella.jpg",
      "Sofríe carnes y verduras, agrega arroz y caldo con azafrán. Cocina sin remover hasta punto."),
    new Recipe(8L,"Falafel","Árabe","Media","Líbano",
      List.of("garbanzos","cilantro","perejil","comino","ajo"),
      35,false, LocalDate.now().minusDays(4),
      "falafel.jpg",
      "Procesa garbanzos remojados con hierbas y especias, forma bolitas y fríe."),
    new Recipe(9L,"Sushi Maki","Japonesa","Difícil","Japón",
      List.of("arroz de sushi","nori","salmón","pepino"),
      60,false, LocalDate.now().minusDays(6),
      "sushimaki.jpg",
      "Cuece arroz sazonado, arma rollos con nori y relleno, corta con cuchillo húmedo.")
  );

  public List<Recipe> recientes(int limit) {
    return data.stream()
      .sorted(Comparator.comparing(Recipe::getFechaPublicacion).reversed())
      .limit(limit).toList();
  }
  public List<Recipe> populares(int limit) {
    return data.stream().filter(Recipe::isPopular).limit(limit).toList();
  }

  public List<Recipe> buscar(String nombre, String tipo, String ingrediente, String pais, String dificultad) {
    return data.stream().filter(r ->
      (nombre == null || r.getNombre().toLowerCase().contains(nombre.toLowerCase())) &&
      (tipo == null || tipo.isBlank() || r.getTipoCocina().equalsIgnoreCase(tipo)) &&
      (ingrediente == null || ingrediente.isBlank() ||
         r.getIngredientes().stream().anyMatch(i -> i.toLowerCase().contains(ingrediente.toLowerCase()))) &&
      (pais == null || pais.isBlank() || r.getPaisOrigen().equalsIgnoreCase(pais)) &&
      (dificultad == null || dificultad.isBlank() || r.getDificultad().equalsIgnoreCase(dificultad))
    ).collect(Collectors.toList());
  }

  public Set<String> tipos() { return new TreeSet<>(data.stream().map(Recipe::getTipoCocina).collect(Collectors.toSet())); }
  public Set<String> paises() { return new TreeSet<>(data.stream().map(Recipe::getPaisOrigen).collect(Collectors.toSet())); }
  public Set<String> dificultades() { return new TreeSet<>(data.stream().map(Recipe::getDificultad).collect(Collectors.toSet())); }

  public Recipe findById(Long id) {
    return data.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
  }
}
