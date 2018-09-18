package com.puc.tcc.controller;



import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.puc.tcc.dao.ClienteDao;
import com.puc.tcc.model.Cliente;



@RestController
public class ClienteController {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@RequestMapping("/")
	String index() {
	    return clienteDao.configdb();
	}
	
	@RequestMapping(value="/clientes",method=RequestMethod.GET)
	@ResponseBody
	public List<Cliente> getLista(){
		List<Cliente> lista = clienteDao.listaTodos();
	return lista;
		
	}
	@RequestMapping(value="/cliente/add",method=RequestMethod.POST)
	@ResponseBody
	public Cliente add(@RequestBody Cliente cliente){
	 return	clienteDao.adicionar(cliente);
	
	}
	@RequestMapping(value="/cliente/up",method=RequestMethod.PUT)
	@ResponseBody
	public Cliente up(@RequestBody Cliente cliente){
	 return	clienteDao.alterar(cliente);
	
	}
	@RequestMapping(value="/cliente/sel",method=RequestMethod.POST)
	@ResponseBody
	public Cliente consulta(@RequestBody Cliente cpf){
	 return	clienteDao.consultar(cpf);
	
	}
	@RequestMapping(value="/cliente/del",method=RequestMethod.POST)
	@ResponseBody
	public boolean apagar(@RequestBody Cliente cpf){
	 return	clienteDao.apagar(cpf);
	
	}
	@RequestMapping(value="/cliente/pes",method=RequestMethod.POST)
	@ResponseBody
	public List<Cliente> pesquisa(@RequestBody Cliente nome){
	 return	clienteDao.pesquisar(nome);
	
	}
	@RequestMapping(value="/cliente/auth",method=RequestMethod.POST)
	@ResponseBody
	public Cliente autentica(@RequestBody Cliente EmailEsenha){
	 return	clienteDao.autenticar(EmailEsenha);
	
	}
	
	
	
}
