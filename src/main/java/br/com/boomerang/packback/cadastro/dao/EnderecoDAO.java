package br.com.boomerang.packback.cadastro.dao;

import javax.persistence.EntityManager;

import br.com.boomerang.packback.cadastro.domain.Endereco;

public class EnderecoDAO extends GenericDAO<Endereco, Long> {

	public EnderecoDAO(EntityManager em) {
		super(em);
	}

}
