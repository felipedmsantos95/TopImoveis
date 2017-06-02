/**
 * Classe Imóvel - Representa um imóvel na aplicação 
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */

package obj;
import java.util.*;

public class Imovel {
	
	private int categoria;
	public String endereco;
	public float areaTotal;
	public int qtdQuartos;
	public int qtdSuites;
	public int qtdVagas;
	public float custo;
	public LinkedList<Integer> adicionais = new LinkedList<Integer>();
	
	public Imovel(int categoria, String endereco, float areaTotal, int qtdQuartos, int qtdSuites, int qtdVagas, float custo)
	{
		//Tratar erros de entrada ao final do projeto
		this.setCategoria(categoria);
		this.endereco = endereco;
		this.areaTotal = areaTotal;
		this.qtdQuartos = qtdQuartos;
		this.qtdSuites = qtdSuites;
		this.qtdVagas = qtdVagas;
		this.custo = custo;
	}

	public int getCategoria() {
		assert (categoria > 0 && categoria <= 4) : "Categoria não existente.";
		return categoria;
	}
	
	public boolean addAdicionais(int num)
	{
		Iterator<Integer> iterator = this.adicionais.iterator();
		Integer adicionalAtual;
		int flag = 0;
		if (this.adicionais.size() == 0){
			this.adicionais.add(num);
			return true;
		}
		else
		{
			while(iterator.hasNext() && flag == 0)
			{
				adicionalAtual = iterator.next();
				if(adicionalAtual == num) flag = 1;
				
			}
			if(flag == 0){
				this.adicionais.add(num);
				return true;
			}
			else return false;
		}
	}

	public void setCategoria(int categoria) {
		if(categoria > 0 && categoria <= 4) 
			this.categoria = categoria;
		else this.categoria = 1;
		
		assert (categoria > 0 && categoria <= 4);
	}
	
	//Ver como tratar os adicionais, provalvelmente irei adicionar numeros a linked list que irao condizer com as categorias existentes
	
	public LinkedList<String> cadastrarAdicionais()
	{
		Iterator<Integer> iterator = this.adicionais.iterator();
		LinkedList<String> lista = new LinkedList<String>();
		Integer adicionalAtual;
		
		while(iterator.hasNext())
		{
			adicionalAtual = iterator.next();
			lista.add(this.getNomeAdicional(adicionalAtual));
		}
		
		return lista;
	}
	
	String getNomeAdicional(int id)
	{
		switch(id)
		{
			case 1:
				return "Piscina";
			case 2:
				return "Churrasqueira";
			case 3:
				return "Playground";
			default:
				return "Adicional não cadastrado";
		}
	}
	
	
	
	//Talvez este método não seja necessário
	public String getNomeCategoria(int id)
	{
		switch(id)
		{
			case 1:
				return "Casa Padrão";
			case 2:
				return "Casa Condomínio";
			case 3:
				return "Apartamento";
			case 4:
				return "Kitchenette";
			default:
				return "Categoria não cadastrada";
		}
	}
	
	public String getDataAtual() //Aqui deixamos no formato que o banco aceita o cadastro da data
	{
		Calendar data = Calendar.getInstance();
		
		return (data.get(Calendar.YEAR) + "-" + (data.get(Calendar.MONTH)+1) + "-" + data.get(Calendar.DAY_OF_MONTH));
		
	}
	
	public String formataData(int dia, int mes, int ano)
	{
		Calendar d = Calendar.getInstance();
		if((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12) && (ano >= 1900 && ano <= (d.get(Calendar.YEAR))))
		{
			return (ano + "-" + mes + "-" + dia);
		}
		else return this.getDataAtual();
	}
	
	
	
	
}
