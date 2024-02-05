package br.com.alura.screenmatch.Dto;

import br.com.alura.screenmatch.model.Categoria;

public record SerieDto(Long id, String titulo, Integer totalTemporadas, Double avaliacao, Categoria genero,
		String atores, String poster, String sinopse) {

}
