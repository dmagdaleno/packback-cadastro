package br.com.boomerang.packback.cadastro.dao;

import javax.persistence.EntityManager;

import br.com.boomerang.packback.cadastro.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

}
