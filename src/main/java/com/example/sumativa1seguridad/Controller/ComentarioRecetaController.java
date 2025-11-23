package com.example.sumativa1seguridad.Controller;

import com.example.sumativa1seguridad.Entity.ComentarioReceta;
import com.example.sumativa1seguridad.Repository.ComentarioRecetaRepository;
import com.example.sumativa1seguridad.Servicio.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping("/comentarios")
public class ComentarioRecetaController {

    private final ComentarioRecetaRepository comentarioRepo;
    private final RecipeService recipeService;

    public ComentarioRecetaController(ComentarioRecetaRepository comentarioRepo,
                                      RecipeService recipeService) {
        this.comentarioRepo = comentarioRepo;
        this.recipeService = recipeService;
    }

    @PostMapping("/receta/{id}")
    public String guardarComentario(@PathVariable Long id,
                                    @ModelAttribute("nuevoComentario") ComentarioReceta form,
                                    @RequestParam("foto") MultipartFile foto,
                                    RedirectAttributes ra) throws IOException {

        var receta = recipeService.findById(id);
        if (receta == null) {
            ra.addFlashAttribute("error", "Receta no encontrada");
            return "redirect:/recetas";
        }

        ComentarioReceta c = new ComentarioReceta();
        c.setNombreReceta(receta.getNombre());
        c.setAutor(form.getAutor());
        c.setEstrellas(form.getEstrellas());
        c.setTexto(form.getTexto());

        if (foto != null && !foto.isEmpty()) {
            String nombreFoto = UUID.randomUUID() + "-" + foto.getOriginalFilename();
            Path carpeta = Paths.get("src/main/resources/static/img/comentarios");
            Files.createDirectories(carpeta);
            Path destino = carpeta.resolve(nombreFoto);
            Files.copy(foto.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);
            c.setFotoFile(nombreFoto);
        }

        comentarioRepo.save(c);
        ra.addFlashAttribute("msg", "¡Comentario agregado!");
        return "redirect:/detalle/" + id;
    }
}
