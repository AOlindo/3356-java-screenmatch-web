package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.Dto.EpisodioDto;
import br.com.alura.screenmatch.Dto.SerieDto;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;

	public List<SerieDto> obterTodasAsSeries() {
		return converteDados(serieRepository.findAll());
	}

	public List<SerieDto> obterTop5Series() {
		return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
	}

	private List<SerieDto> converteDados(List<Serie> series) {
		return series.stream().map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
				s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse())).collect(Collectors.toList());
	}

	public List<SerieDto> obterLancamentos() {
		return converteDados(serieRepository.encontrarEpisodiosMaisRecentes());
	}

	public SerieDto obterPorId(Long id) {
		Optional<Serie> serie = serieRepository.findById(id);
		if (serie.isPresent()) {
			Serie s = serie.get();
			return new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(),
					s.getAtores(), s.getPoster(), s.getSinopse());
		}

		return null;
	}

	public List<EpisodioDto> obterTodasTemporadas(Long id) {
		Optional<Serie> serie = serieRepository.findById(id);
		if (serie.isPresent()) {
			Serie s = serie.get();
			return s.getEpisodios().stream()
					.map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
					.collect(Collectors.toList());
		}

		return null;
	}

	public List<EpisodioDto> obterTemporadasPorNumero(Long id, Long numero) {
		return serieRepository.obterEpisodiosPorTemporada(id, numero).stream()
				.map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
				.collect(Collectors.toList());
	}

	public List<SerieDto> obterSeriesPorCategoria(String nomeGenero) {
		Categoria categoria = Categoria.fromPortugues(nomeGenero);
		return converteDados(serieRepository.findByGenero(categoria));
				
	}
}
