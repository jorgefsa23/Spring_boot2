package br.com.running.shoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.running.shoes.entity.RunningShoes;
import br.com.running.shoes.repository.ShoesRepository;

@Service
public class ShoesService {
	
	@Autowired
	private ShoesRepository shoesrepository;
	
	public RunningShoes salvar(RunningShoes runningshoes) {
		return shoesrepository.save(runningshoes);
	}
	
	public List<RunningShoes> listar(){
		return shoesrepository.findAll();
	}
	
	public Optional<RunningShoes> buscarPorId(Long id){
		return shoesrepository.findById(id);
	}
	
	public void removerPorId(Long id) {
		shoesrepository.deleteById(id);
	}
}
