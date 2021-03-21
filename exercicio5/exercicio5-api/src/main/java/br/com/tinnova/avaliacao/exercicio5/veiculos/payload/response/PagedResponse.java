package br.com.tinnova.avaliacao.exercicio5.veiculos.payload.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 6879396317722281665L;

	private List<T> content;
	private int page;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
