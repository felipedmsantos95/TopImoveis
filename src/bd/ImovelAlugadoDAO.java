package bd;
import java.sql.*;
import obj.*;

public class ImovelAlugadoDAO extends BancoDeDados {

	public boolean verificaDisponibilidade(int idImovel)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM imoveis_alugados WHERE imovel_id=" + idImovel);
			
			if(rs.next()) 
			{
				return false;
			}
			else return true;
		
		}
		catch (SQLException e)
		{
			return true;
		}
	
	}
	
	public boolean alugarImovel(int idImovel, String dataInicio, String dataFim, String nomeCliente)
	{
		
		ClienteDAO cli = new ClienteDAO();
		try
		{
			if(this.verificaDisponibilidade(idImovel))
			{
				Statement st = conexao.createStatement();
				st.executeUpdate("INSERT INTO imoveis_alugados VALUES (NULL, '" + dataInicio +"', '" + dataFim + "', " + idImovel + ", " + cli.getClienteID(nomeCliente) + ")" );
				return true;
			}
			else return false;
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean alugarImovelporID(int imovelID, String dataInicio, String dataFim, Cliente c)
	{
		ClienteDAO cli = new ClienteDAO();
		try
		{
			if(this.verificaDisponibilidade(imovelID))
			{
				Statement st = conexao.createStatement();
				st.executeUpdate("INSERT INTO imoveis_alugados VALUES (NULL, '" + dataInicio +"', '" + dataFim + "', " + imovelID + ", " + cli.getClienteID(c.nome) + ")" );
				return true;
			}
			else return false;
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean estenderAluguel(int imovelID, String novaData)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imoveis_alugados SET data_fim='" + novaData + "' WHERE imovel_id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public String getDataFinal(int imovelID)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT data_fim FROM imoveis_alugados WHERE imovel_id=" + imovelID);
			if(rs.next()) return rs.getString(1);
			else return "";
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return "";
		}
		
	}
	
	
	public static void main(String[] args) {
		
	}

}
