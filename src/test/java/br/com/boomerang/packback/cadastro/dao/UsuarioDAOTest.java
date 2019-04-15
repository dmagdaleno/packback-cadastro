package br.com.boomerang.packback.cadastro.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.boomerang.packback.cadastro.domain.Endereco;
import br.com.boomerang.packback.cadastro.domain.TipoUsuario;
import br.com.boomerang.packback.cadastro.domain.Usuario;
import br.com.boomerang.packback.cadastro.domain.builder.UsuarioBuilder;

public class UsuarioDAOTest {
	
	private EntityManager em;
	private UsuarioDAO dao;
	
	@BeforeEach
	public void config() {
		em = Persistence.createEntityManagerFactory("packback-cadastro").createEntityManager();
		dao = new UsuarioDAO(em);
	}
	
	@Test
	public void deveInserirUmConsumidor() throws Exception {
		String nome = "Joao Guimaraes Rosa";
		String email = "joaorosa@gmail.com";
		String cpf = "000.111.222-33";
		
		Usuario usuario = new UsuarioBuilder().comNome(nome).comCpf(cpf).comEmail(email).constroi();
		
		dao.cadastrar(usuario);
		dao.commit();
		
		List<Usuario> usuarios = dao.listar();
		
		assertThat(usuarios.size()).isEqualTo(1);
		assertThat(usuarios.get(0).getTipo()).isEqualTo(TipoUsuario.CONSUMIDOR);
	}
	
	@Test
	public void deveInserirUmProdutor() throws Exception {
		String nome = "Grande Sertão Veredas SA";
		String email = "gsveredas@gmail.com";
		String cpf = "000.111.222-33";
		String cnpj = "000.111.222/0001-33";
		
		Usuario usuario = new UsuarioBuilder().comNome(nome).comCpf(cpf).comCnpj(cnpj).comEmail(email).constroi();
		
		dao.cadastrar(usuario);
		dao.commit();
		
		List<Usuario> usuarios = dao.listar();
		
		assertThat(usuarios.size()).isEqualTo(1);
		assertThat(usuarios.get(0).getTipo()).isEqualTo(TipoUsuario.PRODUTOR);
	}
	
	@Test
	public void deveInserirUmUsuarioComUmEndereco() throws Exception {
		String nome = "Joao Guimaraes Rosa";
		String email = "joaorosa@gmail.com";
		String cpf = "000.111.222-33";
		
		Endereco endereco = novoEndereco();
		
		Usuario usuario = new UsuarioBuilder()
				.comNome(nome)
				.comCpf(cpf)
				.comEmail(email)
				.comEnderecos(Collections.singletonList(endereco))
				.constroi();
		
		dao.cadastrar(usuario);
		dao.commit();
		
		List<Usuario> usuarios = dao.listar();
		
		assertThat(usuarios.size()).isEqualTo(1);
		assertThat(usuarios.get(0).getEnderecos().size()).isEqualTo(1);
		assertThat(usuarios.get(0).getTipo()).isEqualTo(TipoUsuario.CONSUMIDOR);
	}

	private Endereco novoEndereco() {
		Endereco endereco = new Endereco();
		endereco.setRua("Rua das plantas");
		endereco.setNumero(2);
		endereco.setCidade("Cordisburgo");
		endereco.setEstado("Minas Gerais");
		endereco.setPais("BR");
		return endereco;
	}
	
	@AfterEach
	public void finish() {
		em.close();
	}
}
