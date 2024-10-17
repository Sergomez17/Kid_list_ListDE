package co.edu.umanizales.kids_list.controller;

import co.edu.umanizales.kids_list.model.Kid;
import co.edu.umanizales.kids_list.model.Node;
import co.edu.umanizales.kids_list.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public Node getListChildren() {

        return listSEService.showKids();
    }

    @PostMapping
    public String addKidToFinal(@RequestBody Kid kid) {
        listSEService.getListSE().add(kid);
        return "Adicionado exitosamente";
    }

    @PostMapping("/tostart")
    public String addKidToStart(@RequestBody Kid kid) {
        listSEService.getListSE().addToStart(kid);
        return "Adicionado exitosamente";
    }

    @PostMapping("/inposition")
    public String addKidInPosition(@RequestBody Kid kid, @RequestParam int position) {
        // Validar si la posición es válida antes de llamar al método de la lista
        if (position < 1 || position > listSEService.getListSE().getSize() + 1) {
            return "Error: La posición " + position + " no es válida.";
        }

        listSEService.getListSE().addInPosition(kid, position);
        return "Adicionado exitosamente en la posición " + position;
    }


    @PostMapping("/invert")
    public String invertList() {
        listSEService.getListSE().invert();
        return "Lista invertida exitosamente";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        listSEService.getListSE().deleteById(id);
        return "El niño con ID " + id + " ha sido eliminado";
    }


    @DeleteMapping("/deleteByPosition")
    public String deleteByPosition(@RequestBody Kid kid, @RequestParam int position) {
        try {
            listSEService.getListSE().deleteByPosition(position);
            return "El niño en la posición " + position + " ha sido eliminado";
        } catch (IndexOutOfBoundsException e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/intercalaresgenero")
    public String intercalarPorGenero() {
        listSEService.getListSE().intercalarxGenero();
        return "Niños intercalados por género exitosamente";
    }

    @PostMapping("/intercambiarextremos")
    public String intercambiarExtremos() {
        listSEService.getListSE().intercambiarExtremos();
        return "Extremos intercambiados exitosamente";
    }
}