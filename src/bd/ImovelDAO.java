package bd;
import java.sql.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import intf.RedirectedFrame;
import obj.*;

public class ImovelDAO extends BancoDeDados {

	public boolean adicionarImovel(Imovel i)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO imovel VALUES (NULL, '" + i.endereco +"', " + i.areaTotal + ", " + i.qtdQuartos + ", " + i.qtdSuites + ", " + i.qtdVagas + ", " + i.custo + ", " + i.getCategoria() + ")" );
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean alterarEndereco(int imovelID, String endereco)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set endereco='" + endereco + "' WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean alterarAreaTotal(int imovelID, float area)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set area_total=" + area + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean alterarQtdQuartos(int imovelID, int quartos)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set qtd_quartos=" + quartos + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean alterarQtdSuites(int imovelID, int suites)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set qtd_suites=" + suites + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean alterarQtdVagas(int imovelID, int vagas)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set qtd_vagas=" + vagas + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	
	public boolean alterarCusto(int imovelID, float custo)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set custo=" + custo + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean alterarCategoria(int imovelID, int categoria)
	{
		
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE imovel set categoria_id=" + categoria + " WHERE id=" + imovelID);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	public boolean removerImovel(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM adicionais WHERE imovel_id=" + id);
			st.executeUpdate("DELETE FROM imovel WHERE id=" + id);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}		
	}
	
	
	public List<Imovel> getImoveisTable() {
		
		ArrayList<Imovel> contatos = new ArrayList<Imovel>();
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM imovel, categoria WHERE imovel.categoria_id = categoria.id");
			
			while (rs.next()) {
				Imovel contato = new Imovel(rs.getInt(8), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7));
				contatos.add(contato);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return contatos;
	}
	
	
	
	
	public int getImovelID(Imovel i)//Procura exatamente pelo objeto imovel e retorna o id do espelho dele no banco
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM imovel WHERE imovel.categoria_id =" + i.getCategoria() + " AND imovel.endereco='" + i.endereco + "' AND imovel.area_total=" + i.areaTotal + " AND imovel.qtd_quartos=" + i.qtdQuartos + " AND imovel.qtd_suites=" + i.qtdSuites + " AND imovel.qtd_vagas=" + i.qtdVagas + " AND imovel.custo=" + i.custo);
			
			if(rs.next()) return rs.getInt(1);
			else return 0;
		}
		catch(SQLException e)
		{
			return 0;
		}		
	}
	
	public void listarImoveis()
	{
		try
		{
			Statement st = conexao.createStatement();
			Statement ts = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM imovel, categoria WHERE imovel.categoria_id = categoria.id");
			while(rs.next())
			{				
				System.out.print("ID: " + rs.getInt(1) + "\nCategoria: "+ rs.getString(10) + "\nEndereço: " + rs.getString(2) + "\nÁrea Total: " + rs.getFloat(3) + "\nQuantidade de Quartos: " + rs.getInt(4) + "\nQuantidade de Suítes: " + rs.getInt(5) + "\nQuantidade de Vagas de Garagem: " + rs.getInt(6) + "\nPreço do Aluguel: R$ " + rs.getFloat(7) + "\n");
				ResultSet cs = ts.executeQuery("SELECT * from adicionais WHERE imovel_id=" + rs.getInt(1));
				String add = "";
				while(cs.next()) add += cs.getString(2) + "; ";
				if(add != "") System.out.print("Adicionais: " + add + "\n\n");
				else System.out.println();
			}
			
		}
		catch(SQLException e)
		{
			
		}		
	}
	
	public Imovel getImovel(int id)
	{
		try
		{
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM imovel WHERE id=" + id);
			
			if(rs.next()) 
			{
				return new Imovel(rs.getInt(8), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7));
			}
			else return null;
		
		}
		catch (SQLException e)
		{
			return null;
		}
	}
	
	//Os adicionais de cada imovel estarão ideintificados em uma linkedlist onde cada elemento nessa rotina sera cadastrado ao banco de um vez
	public void CadastrarAdicionaisBanco(Imovel i)
	{
		Iterator<String> iterator = i.cadastrarAdicionais().iterator();
		String adicionalAtual;		
		try
		{
			Statement st = conexao.createStatement();
			while(iterator.hasNext())
			{
				adicionalAtual = iterator.next();
				
				st.executeUpdate("INSERT INTO adicionais VALUES (NULL, '" + adicionalAtual +"', " + this.getImovelID(i) + ")");
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/*private int getImovelCat(Imovel i) {
		
			try
			{
				Statement st = conexao.createStatement();
				ResultSet rs = st.executeQuery("SELECT categoria_id FROM imovel WHERE imovel.id =" + this.getImovelID(i));
				
				return rs.getInt(1);
			}
			catch(SQLException e)
			{
				return 0;
			}		
		
	}*/

	public static void main(String[] args) {
		
		
		

	}

}
