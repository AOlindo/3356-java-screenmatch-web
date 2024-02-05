package br.com.alura.screenmatch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.Dto.SerieDto;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;

	public List<SerieDto> obterTodasAsSeries() {
		return serieRepository
				.findAll().stream().map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(),
						s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
				.collect(Collectors.toList());
	}

}
