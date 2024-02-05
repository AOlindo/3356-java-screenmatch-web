package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.Dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;

@RestController
public class SerieController {

	@Autowired
	private SerieService serieService;
	
	@GetMapping("/series")
	public List<SerieDto> obterSeries() {
		return serieService.obterTodasAsSeries();
	}
}
