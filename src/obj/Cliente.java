/**
 * Classe Cliente - Representa um cliente na aplicação 
 * @author Felipe Santos &lt; felipedmsantos95@gmail.com&gt;
 * @version 1.0, 2017-06-24
 */


package obj;

import java.util.Iterator;
import java.util.LinkedList;

public class Cliente {
	
	public String nome;
	public LinkedList<Integer> opcionais = new LinkedList<Integer>();
	
	public Cliente(String nome)
	{
		this.nome = nome;
	}

	public boolean addOpcionais(int num)//Não permite que o mesmo opcional seja cadastrado mais de uma vez
	{
		Iterator<Integer> iterator = this.opcionais.iterator();
		Integer adicionalAtual;
		int flag = 0;
		if (this.opcionais.size() == 0){
			this.opcionais.add(num);
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
				this.opcionais.add(num);
				return true;
			}
			else return false;
		}
	}
	
	String getNomeOpcional(int id)
	{
		switch(id)
		{
			case 1:
				return "Seguro";
			case 2:
				return "Chave extra";
			case 3:
				return "Imóvel mobiliado";
			default:
				return "Opcional não cadastrado";
		}
	}
	
	
	public LinkedList<String> cadastrarOpcionais()
	{
		Iterator<Integer> iterator = this.opcionais.iterator();
		LinkedList<String> lista = new LinkedList<String>();
		Integer OpcionalAtual;
		
		while(iterator.hasNext())
		{
			OpcionalAtual = iterator.next();
			lista.add(this.getNomeOpcional(OpcionalAtual));
		}
		
		return lista;
	}
}
