package com.brunno.desafio.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunno.desafio.domain.Cliente;
import com.brunno.desafio.domain.Empresa;
import com.brunno.desafio.domain.Permissao;
import com.brunno.desafio.domain.Servico;
import com.brunno.desafio.domain.TipoCliente;
import com.brunno.desafio.domain.Usuario;
import com.brunno.desafio.repositories.ClienteRepository;
import com.brunno.desafio.repositories.EmpresaRepository;
import com.brunno.desafio.repositories.PermissaoRepository;
import com.brunno.desafio.repositories.ServicoRepository;
import com.brunno.desafio.repositories.TipoClienteRepository;
import com.brunno.desafio.repositories.UsuarioRepository;

@Service
public class DbService {

	@Autowired
	private TipoClienteRepository tipoClienteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private ServicoRepository servicoRepository;

		
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public void instDevDB() throws ParseException {
		TipoCliente ouro = new TipoCliente(null, "OURO", 10.0);
		TipoCliente prata = new TipoCliente(null, "PRATA", 5.0);

		Cliente jon = new Cliente(null, "Jon Snow", "37131916897", "brunnorb@gmail.com", ouro);
		Cliente arya = new Cliente(null, "Arya Stark", "81254671064", "brunno.rc@gmail.com", prata);

		tipoClienteRepository.saveAll(Arrays.asList(ouro, prata));
		clienteRepository.saveAll(Arrays.asList(jon, arya));

		Empresa empresa = new Empresa(null, "Empresa Desafio");
		empresaRepository.saveAll(Arrays.asList(empresa));

		Servico serv = new Servico(null, empresa, "Suporte tecnico", "Suporte a area tecnica", 1000.00);
		Servico serv1 = new Servico(null, empresa, "Treinamento ", "Treinamento pessoal e profissional", 3000.00);
		Servico serv2 = new Servico(null, empresa, "Help Desk ", "Duvidas e sugestoes sistema", 2000.00);
		servicoRepository.saveAll(Arrays.asList(serv, serv1, serv2));

		Permissao perm1 = new Permissao(null,"ROLE_SUPERVISOR");
		Permissao perm2 = new Permissao(null,"ROLE_USUARIO");
		
   	    permissaoRepository.saveAll(Arrays.asList(perm1,perm2));
		
   	    Usuario user1 = new Usuario(null,"adm@adm.com","$2a$10$Mr5YaTqXYFcKIDCjtlvHW.w9HlgovegE5eEEii6VLGl5f9yjnQ2jS",empresa);
   	    Usuario user2 = new Usuario(null,"jon@adm.com","$2a$10$2kwBqAEhYG3TP4j9pe3.1eMvNNRwum2xNp4Qhb4y1.zKxmJWfGJM2",empresa);
   	    
   	    user1.setPermissoes(Arrays.asList(perm1,perm2));
   	    user2.setPermissoes(Arrays.asList(perm2));
   	    
   	    usuarioRepository.saveAll(Arrays.asList(user1,user2));

		
		
	}

}
