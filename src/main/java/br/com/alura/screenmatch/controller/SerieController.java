package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.Dto.EpisodioDto;
import br.com.alura.screenmatch.Dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

	@Autowired
	private SerieService serieService;
	
	@GetMapping
	public List<SerieDto> obterSeries() {
		return serieService.obterTodasAsSeries();
	}
	
	@GetMapping("/top5")
	public List<SerieDto> obterTop5Series(){
		return serieService.obterTop5Series();
	}
	
	@GetMapping("/lancamentos")
	public List<SerieDto> obterLancamento(){
		return serieService.obterLancamentos();
	}
	
	@GetMapping("/{id}")
	public SerieDto obterPorId(@PathVariable Long id){
		return serieService.obterPorId(id);
	}
	
	@GetMapping("/{id}/temporadas/todas")
	public List<EpisodioDto> obterTodasTemporadas(@PathVariable Long id){
		return serieService.obterTodasTemporadas(id);
	}
	
	@GetMapping("/{id}/temporadas/{numero}")
	public List<EpisodioDto> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
		return serieService.obterTemporadasPorNumero(id, numero);
	}
	
	
}
