package simulador_de_Pipeline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class InterpretadorInstrucoes 
{
	private ArrayList<Instrucao> instrucoes;
	private int numeroCiclos;
	private Deque<Integer> inicioInst;
	
	public InterpretadorInstrucoes(String arquivo) throws FileNotFoundException, IOException
	{
		instrucoes = LerInstrucoes.lerArquivo(arquivo);
		inicioInst = new LinkedList<>();
		
		setNumeroCiclos(0);		
		identificaInicioInst();
		contaNumeroCiclos();
	}
	
	/**
	 * Função preenche array que indica o ciclo de inicio de cada uma das instruções.
	 * Considera as dependências entre instruções.
	 */
	private void identificaInicioInst()
	{
		inicioInst.add(1); //Instrução 1 sempre começa no primeiro ciclo
		for(int i = 1; i < instrucoes.size(); i++)
		{
			String acaoAtual = instrucoes.get(i).getAcao();
			String acaoInstAnterior = instrucoes.get(i-1).getAcao();
			
			if(acaoAtual.equals("sw"))
			{
				//Pega o registrador 1 da instrução SW
				String r1 = instrucoes.get(i).getRegistradores().get(0);							
				
				//Verifica se esse registrador está sendo modificado em alguma instrução anterior
				if(acaoInstAnterior.equals("sw")) 
				{
					if(r1.equals(instrucoes.get(i-1).getRegistradores().get(1)))
						defineInicioCiclo(4); //Instrução começa 4 ciclos depois da anterior a ela, pois houve dependência
					else
						defineInicioCiclo(1);
				}
				else
				{
					if(r1.equals(instrucoes.get(i-1).getRegistradores().get(0)))
						defineInicioCiclo(4); 
					else
						defineInicioCiclo(1);
				}
			}
			else 
			{		
				if(acaoAtual.equals("lw"))
				{
					String r2 = instrucoes.get(i).getRegistradores().get(1);
					
					if(acaoInstAnterior.equals("sw")) 
					{
						if(r2.equals(instrucoes.get(i-1).getRegistradores().get(1)))
							defineInicioCiclo(4); //Instrução começa 4 ciclos depois da anterior a ela, pois houve dependência
						else
							defineInicioCiclo(1);
					}
					else
					{
						if(r2.equals(instrucoes.get(i-1).getRegistradores().get(0)))
							defineInicioCiclo(4); 
						else
							defineInicioCiclo(1);
					}	
				}
				else
				{
					String r2 = instrucoes.get(i).getRegistradores().get(1);
					String r3 = instrucoes.get(i).getRegistradores().get(2);
					if(acaoInstAnterior.equals("sw"))
					{
						if(r2.equals(instrucoes.get(i-1).getRegistradores().get(1)))
							defineInicioCiclo(4);
						else
						{
							if(r3.equals(instrucoes.get(i-1).getRegistradores().get(1)))
								defineInicioCiclo(4);
							else
								defineInicioCiclo(1);
						}
					}
					else
					{
						if(r2.equals(instrucoes.get(i-1).getRegistradores().get(0)))
							defineInicioCiclo(4);
						else
						{
							if(r3.equals(instrucoes.get(i-1).getRegistradores().get(0)))
								defineInicioCiclo(4);
							else
								defineInicioCiclo(1);
						}
					}
				}							
			}				
		}
	}
	
	/**
	 * Função auxiliar, define número do ciclo de inicio da instrução que chamou o método.
	 * @param n Indica o número de ciclos que essa instrução começará depois da última
	 */
	private void defineInicioCiclo(int n)
	{
		int aux = inicioInst.getLast();
		inicioInst.addLast(aux + n);
	}
	
	/**
	 * Faz o cálculo do número de ciclos no conjunto de instruções, baseado no inicio de cada instrução
	 */
	private void contaNumeroCiclos()
	{
		setNumeroCiclos(5);
		Deque<Integer> aux = new LinkedList<>(); 
		
		for(int i : inicioInst)
		{
			aux.add(i);
		}
		
		int v1 = aux.poll();
		
		for(int i = 0; i < inicioInst.size() - 1; i++)
		{	
			int v2 = aux.poll();
			
			setNumeroCiclos(getNumeroCiclos() + v2 - v1);
			
			v1 = v2;
		}
	}

	public int getNumeroCiclos() {
		return numeroCiclos;
	}

	private void setNumeroCiclos(int numeroCiclos) {
		this.numeroCiclos = numeroCiclos;
	}
	
	public Deque<Integer> getInicioInt()
	{
		return inicioInst;
	}
	
	public ArrayList<Instrucao> getInstrucoes()
	{
		return instrucoes;
	}
}
