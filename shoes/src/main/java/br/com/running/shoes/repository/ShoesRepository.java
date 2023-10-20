package br.com.running.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.running.shoes.entity.RunningShoes;

public interface ShoesRepository extends JpaRepository<RunningShoes, Long>{

}
