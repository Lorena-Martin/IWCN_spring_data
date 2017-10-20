package com.iwcn.master.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwcn.master.model.Producto;
import com.iwcn.master.repositories.ProductoRepository;

@Controller
public class AppController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@RequestMapping("/")
	public ModelAndView index(Model model) {
		return new ModelAndView("index_template");
	}
	
    @RequestMapping("/nuevoProducto")
    public ModelAndView nuevoProducto(Model model, Producto prod) {
    	model.addAttribute(prod);
        return new ModelAndView("nuevoProducto_template");
    }
    
    @RequestMapping("/guardado")
    public ModelAndView guardado(Model model, Producto prod) {
    	productoRepository.save(prod);
    	return new ModelAndView("guardado_template").addObject("nombre", prod.getNombre());
    }
    
    @RequestMapping("/listaProductos")
    public ModelAndView listaProductos() {
    	return new ModelAndView("listaProductos_template").addObject("lista", productoRepository.findAll());
    }
    
    @RequestMapping("/info/{option}")
    public ModelAndView info(@PathVariable String option) {
    	int indice = Integer.parseInt(option);
    	Producto pr = productoRepository.findById(indice);
    	return new ModelAndView("info_template").addObject("codigo", pr.getCodigo()).addObject("nombre", pr.getNombre()).addObject("descripcion", pr.getDescripcion()).addObject("precio", pr.getPrecio());
    }
    
    @RequestMapping("/eliminado/{option}")
    public ModelAndView eliminado(@PathVariable String option) {
    	int indice = Integer.parseInt(option);
    	Producto pr = productoRepository.findById(indice);
    	productoRepository.delete(pr);
    	return new ModelAndView("eliminado_template").addObject("nombre", pr.getNombre());
    }
    
    @RequestMapping("/editar/{option}")
    public ModelAndView editar(Model model, @PathVariable String option) {
    	int indice = Integer.parseInt(option);
    	Producto pr = productoRepository.findById(indice);
    	model.addAttribute(pr);
    	return new ModelAndView("editar_template").addObject("id", pr.getId());
    }
    
    @RequestMapping("/editado")
    public ModelAndView editado(Producto prod) {
    	productoRepository.save(prod);
    	return new ModelAndView("editado_template").addObject("nombre", prod.getNombre());
    }
    

}
