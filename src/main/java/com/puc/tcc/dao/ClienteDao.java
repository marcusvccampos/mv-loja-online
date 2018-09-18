package com.puc.tcc.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import com.puc.tcc.model.Cliente;

@Repository
public class ClienteDao {	

//public Cliente adicionar(Cliente cliente);
//public Cliente alterar(Cliente cliente);
//public boolean apagar(Cliente cpf);
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	//@Value("${spring.datasource.username}")
	//private String dbUser;
	
	//@Value("${spring.datasource.password}")
	//private String dbSenha;
	
	
	
	@Autowired
	private DataSource datasource;
	
	public Cliente adicionar(Cliente c) {
		Connection con = null;
		PreparedStatement ps = null;

		
		String query = "insert into Cliente(cpf,nome,estado,municipio,endereco,telefone,email,senha)values(?,?,?,?,?,?,?,?);";
		
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, c.getCpf());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getEstado());
			ps.setString(4, c.getMunicipio());
			ps.setString(5, c.getEndereco());
			ps.setString(6,	c.getTelefone());
			ps.setString(7, c.getEmail());
			ps.setString(8, c.getSenha());
			ps.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return c;
	}
	
	public List<Cliente> listaTodos(){
	
	String query = "SELECT cpf,nome,estado,municipio,endereco,telefone,email,senha FROM Cliente";
	List<Cliente> lista = new ArrayList<Cliente>();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
		con = datasource.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			Cliente c = new Cliente();
        	c.setCpf(rs.getString("cpf"));
            c.setNome(rs.getString("nome"));
            c.setEstado(rs.getString("estado"));
            c.setMunicipio(rs.getString("municipio"));
            c.setEndereco(rs.getString("endereco"));
            c.setTelefone(rs.getString("telefone"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
			lista.add(c);
        }
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try {
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	return lista;
}
	
	public Cliente alterar(Cliente c) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "update Cliente set nome=?, estado=?, municipio=?, endereco=?, telefone=?, email=?, senha=? where cpf=?;";
		
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getEstado());
			ps.setString(3, c.getMunicipio());
			ps.setString(4, c.getEndereco());
			ps.setString(5, c.getTelefone());
			ps.setString(6, c.getEmail());
			ps.setString(7, c.getSenha());
			ps.setString(8, c.getCpf());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return c;
	}
	
	public Cliente consultar(Cliente cpf) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = new Cliente();
		String query = "select cpf,nome,estado,municipio,endereco,telefone,email,senha from Cliente where cpf=?;";
				
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cpf.getCpf());
			rs = ps.executeQuery();
			
			
			if(rs.next()) {
				cliente.setCpf(rs.getString(1));
				cliente.setNome(rs.getString(2));
				cliente.setEstado(rs.getString(3));
				cliente.setMunicipio(rs.getString(4));
				cliente.setEndereco(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setEmail(rs.getString(7));
				cliente.setSenha(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return cliente;
	}
	
	public boolean apagar(Cliente cpf) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "delete from Cliente where cpf = ?;";
		
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cpf.getCpf());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return true;
	}
	
	
	public List<Cliente> pesquisar(Cliente nome) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cliente> lista=new ArrayList<Cliente>();
		
		String query ="select cpf,nome,estado,municipio,endereco,telefone,email,senha from Cliente where nome like ?;";
		
		try {
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, "%"+nome.getNome()+"%");
			rs = ps.executeQuery();
			
	        while (rs.next()) {
				Cliente c = new Cliente();
	        	c.setCpf(rs.getString("cpf"));
	            c.setNome(rs.getString("nome"));
	            c.setEstado(rs.getString("estado"));
	            c.setMunicipio(rs.getString("municipio"));
	            c.setEndereco(rs.getString("endereco"));
	            c.setTelefone(rs.getString("telefone"));
	            c.setEmail(rs.getString("email"));
	            c.setSenha(rs.getString("senha"));
				lista.add(c);
	        }
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return lista;
	}
	
	
	public Cliente autenticar(Cliente EmailEsenha) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = new Cliente();
		String query = "select cpf,nome,estado,municipio,endereco,telefone,email,senha from Cliente where email=? and senha=?;";
		
		try {
			con= datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, EmailEsenha.getEmail());
			ps.setString(2, EmailEsenha.getSenha());
			rs = ps.executeQuery();
			
			
			if(rs.next()){
				cliente.setCpf(rs.getString(1));
				cliente.setNome(rs.getString(2));
				cliente.setEstado(rs.getString(3));
				cliente.setMunicipio(rs.getString(4));
				cliente.setEndereco(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setEmail(rs.getString(7));
				cliente.setSenha(rs.getString(8));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return cliente;
	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
	    if (dbUrl == null || dbUrl.isEmpty()) {
	      return new HikariDataSource();
	    } else {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	     // config.setUsername(dbUser);
	      //config.setPassword(dbSenha);
	      return new HikariDataSource(config);
	    }
	  }
	
	public String configdb() {
		Connection con  = null;
		PreparedStatement ps = null;
		String status = "Status do banco:";
		
		String query = ("CREATE TABLE IF NOT EXISTS Cliente(\r\n" + 
	      		"    cpf varchar(14) not null,\r\n" + 
	      		"    nome varchar(50) not null,\r\n" + 
	      		"    estado varchar(2) not null,\r\n" + 
	      		"    municipio varchar(50) not null,\r\n" +
	      		"    endereco varchar(100) not null,\r\n" + 
	      		"    telefone varchar(20) not null,\r\n" + 
	      		"    email varchar(50) not null,\r\n" + 
	      		"    senha varchar(10) not null\r\n" + 
	      		"    \r\n" + 
	      		");");
		try {
			con =datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			return status+" configurado";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return status+" não configurado";
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
}


	

