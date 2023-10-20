package br.com.running.shoes.http.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.running.shoes.entity.RunningShoes;
import br.com.running.shoes.service.ShoesService;

@RestController
@RequestMapping("/shoes") //determinando a rota de cliente
public class ShoesController {
	
	//para criar o método precisamos do ShoesService
	
	@Autowired
	private ShoesService shoesService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//criamos o método com o verbo http
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RunningShoes salvar(RunningShoes runningShoes) {
		return shoesService.salvar(runningShoes);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<RunningShoes> listaShoes(){
		return shoesService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RunningShoes buscarShoesPorId(@PathVariable("id") Long id) {
		return shoesService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoe not found"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerShoe(@PathVariable("id") Long id) {
		shoesService.buscarPorId(id)
			.map(runningShoes -> {
				shoesService.removerPorId(id); //deveria ser getId()
				return Void.TYPE;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoe not found"));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarShoes(@PathVariable("id") Long id, @RequestBody RunningShoes runningShoes) {
		shoesService.buscarPorId(id)
		.map(shoeBase -> {
			modelMapper.map(runningShoes, shoeBase);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shoe not found"));
	}
}
