package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.Dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;

	public List<SerieDto> obterTodasAsSeries() {
		return converteDados(serieRepository.findAll());
	}
	
	public List<SerieDto> obterTop5Series(){
		return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
	}
	
	private List<SerieDto> converteDados(List<Serie> series){
		 return series.stream()
		            .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
		            .collect(Collectors.toList());
	}

	public List<SerieDto> obterLancamentos() {
		return converteDados(serieRepository.encontrarEpisodiosMaisRecentes());
	}
	
	public SerieDto obterPorId(Long id) {
		Optional<Serie> serie = serieRepository.findById(id);
		if(serie.isPresent()) {
			Serie s = serie.get();
			return new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
		}
		
		return null;
	}
	
}
