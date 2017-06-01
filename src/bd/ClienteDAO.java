package bd;
import obj.Cliente;
import java.sql.*;
import java.util.Iterator;

import javax.swing.JFrame;

import intf.RedirectedFrame;



public class ClienteDAO extends BancoDeDados {

	public boolean cadastrarCliente(Cliente c)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO cliente VALUES (NULL, '" + c.nome +"')" );
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}		
	}
	
	public boolean removerCliente(String nome)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM opcionais WHERE cliente_id=" + this.getClienteID(nome));
			st.executeUpdate("DELETE FROM cliente WHERE nome='" + nome + "'");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}		
	}
	
	
	
	public int getClienteID(String c)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM cliente WHERE cliente.nome ='" + c + "'");
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	
	public Cliente getCliente(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE id=" + id);
			
			if(rs.next()) 
			{
				return new Cliente(rs.getString(2));
			}
			else return null;
		
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	public void CadastrarOpcionaisBanco(Cliente c)
	{
		Iterator<String> iterator = c.cadastrarOpcionais().iterator();
		String opcionalAtual;		
		try
		{
			Statement st = conexao.createStatement();
			while(iterator.hasNext())
			{
				opcionalAtual = iterator.next();				
				st.executeUpdate("INSERT INTO opcionais VALUES (NULL, " + this.getClienteID(c.nome) + ", '" + opcionalAtual + "')");
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void listarClientes()
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM cliente");
			while(rs.next())
			{				
				System.out.println("Nome: "+ rs.getString(2) + "\n");
			}
			
		}
		catch(SQLException e)
		{
			
		}		
	}
	
	public void relatorioCliente(int id)
	{
		try
		{
			
			ClienteDAO c = new ClienteDAO();
			System.out.println("=======================" + c.getCliente(id).nome + "======================================================");
			Statement ts = conexao.createStatement();
			ResultSet cs = ts.executeQuery("SELECT * from opcionais WHERE cliente_id=" + id);
			String add = "";
			while(cs.next())
			{
				add += cs.getString(3) + "; ";
			}
			if(add != "") System.out.println("Opcionais do Cliente: " + add);
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from imoveis_alugados, cliente, imovel where imoveis_alugados.cliente_id = cliente.id AND imoveis_alugados.cliente_id = " + id + " AND imovel.id = imoveis_alugados.imovel_id");
			while(rs.next())
			{				
				System.out.println("Endereço Alugado: "+ rs.getString(9) + "\n--Custo: R$ " + rs.getFloat(14));
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}		
	}
	
	public void relatorioGeral()
	{
		try
		{
			
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("select * from cliente");
			while(rs.next())
			{				
				this.relatorioCliente(rs.getInt(1));
				System.out.println();
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Listar custos de cada cliente
	public static void main(String[] args) {
		
		
	}
	
	
	
	
	
}
