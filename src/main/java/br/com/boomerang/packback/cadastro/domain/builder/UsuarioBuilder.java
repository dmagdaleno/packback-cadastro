package br.com.boomerang.packback.cadastro.domain.builder;

import java.util.Collection;
import java.util.Collections;

import br.com.boomerang.packback.cadastro.domain.Endereco;
import br.com.boomerang.packback.cadastro.domain.Usuario;

public class UsuarioBuilder {
	
	private String nome = "Indefinido";
	private String email = "Indefinido";
	private String cpf = null;
	private String razaoSocial = null;
	private String cnpj = null;
	private Collection<Endereco> enderecos = Collections.emptyList();
	
	public UsuarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public UsuarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UsuarioBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public UsuarioBuilder comRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		return this;
	}
	
	public UsuarioBuilder comCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}
	
	public UsuarioBuilder comEnderecos(Collection<Endereco> enderecos) {
		this.enderecos = enderecos;
		return this;
	}
	
	public Usuario constroi() {
		Usuario usuario = new Usuario(nome, email, cpf, razaoSocial, cnpj);
		usuario.setEnderecos(enderecos);
		return usuario;
	}
	
}
