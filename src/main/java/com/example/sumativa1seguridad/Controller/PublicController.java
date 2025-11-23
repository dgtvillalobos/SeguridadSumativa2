package com.example.sumativa1seguridad.Controller;

import com.example.sumativa1seguridad.Entity.ComentarioReceta;
import com.example.sumativa1seguridad.Repository.ComentarioRecetaRepository;
import com.example.sumativa1seguridad.Servicio.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PublicController {

  private final RecipeService service;
  private final ComentarioRecetaRepository comentarioRepo;


  public PublicController(RecipeService service, ComentarioRecetaRepository comentarioRepo) 
  { 
    this.service = service; 
    this.comentarioRepo = comentarioRepo;
  }

  @GetMapping({"/", "/recetas"})
  public String home(@RequestParam(required = false) String nombre,
                     @RequestParam(required = false) String tipo,
                     @RequestParam(required = false) String ingrediente,
                     @RequestParam(required = false) String pais,
                     @RequestParam(required = false) String dificultad,
                     Model model) {

    boolean hasFilters =
        (nombre != null && !nombre.isBlank()) ||
        (tipo != null && !tipo.isBlank()) ||
        (ingrediente != null && !ingrediente.isBlank()) ||
        (pais != null && !pais.isBlank()) ||
        (dificultad != null && !dificultad.isBlank());

    var recetas = hasFilters
        ? service.buscar(nombre, tipo, ingrediente, pais, dificultad)
        : service.recientes(9);

    model.addAttribute("title", "Inicio");
    model.addAttribute("recetas", recetas);
    model.addAttribute("tipos", service.tipos());
    model.addAttribute("paises", service.paises());
    model.addAttribute("dificultades", service.dificultades());
    model.addAttribute("nombre", nombre);
    model.addAttribute("tipo", tipo);
    model.addAttribute("ingrediente", ingrediente);
    model.addAttribute("pais", pais);
    model.addAttribute("dificultad", dificultad);
    model.addAttribute("hasFilters", hasFilters);
    model.addAttribute("count", recetas.size());
    return "home";
  }

  @GetMapping("/buscar")
  public String buscar(@RequestParam(required = false) String nombre,
                       @RequestParam(required = false) String tipo,
                       @RequestParam(required = false) String ingrediente,
                       @RequestParam(required = false) String pais,
                       @RequestParam(required = false) String dificultad,
                       Model model) {
    model.addAttribute("title", "Buscar recetas");
    model.addAttribute("tipos", service.tipos());
    model.addAttribute("paises", service.paises());
    model.addAttribute("dificultades", service.dificultades());
    model.addAttribute("resultados", service.buscar(nombre, tipo, ingrediente, pais, dificultad));
    model.addAttribute("nombre", nombre);
    model.addAttribute("tipo", tipo);
    model.addAttribute("ingrediente", ingrediente);
    model.addAttribute("pais", pais);
    model.addAttribute("dificultad", dificultad);
    return "buscar";
  }

  // @GetMapping("/detalle/{id}")
  // public String detalle(@PathVariable Long id, Model model) {
  //   var receta = service.findById(id);
  //   if (receta == null) return "redirect:/recetas";
  //   model.addAttribute("title", receta.getNombre());
  //   model.addAttribute("r", receta);
  //   return "detalle";
  // }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("title", "Login");
    return "login";
  }

@GetMapping("/detalle/{id}")
  public String detalle(@PathVariable Long id, Model model) {
    var receta = service.findById(id);
    if (receta == null) return "redirect:/recetas";

    model.addAttribute("title", receta.getNombre());
    model.addAttribute("r", receta);

    // comentarios por nombre de receta
    model.addAttribute("comentarios",
        comentarioRepo.findByNombreRecetaOrderByFechaCreacionDesc(receta.getNombre()));

    // objeto vacío para el formulario de comentario
    model.addAttribute("nuevoComentario", new ComentarioReceta());

    return "detalle";
  }

}


