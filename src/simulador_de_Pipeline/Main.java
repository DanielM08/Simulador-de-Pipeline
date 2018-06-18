package simulador_de_Pipeline;

import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) 
	{

	    
	}

	
	/**
	 * Retorna a configuração dos 5 estágios do pipeline do MIPS através do vetor Cores. 
	 * Se uma posição está setada como 1, indica-se que aquele estágio está em funcionamento no ciclo.
	 * @param inicioInst Indica o ciclo de início de cada instrução
	 * @param nCiclo Indica em que ciclo 
	 * @param Cores
	 * @return
	 */
	public int[] vetorColorir(Deque<Integer> inicioInst, int nCiclo, int[] Cores)
	{
		
		for(int i = 4; i > 0; i--)
		{
			if(Cores[i-1] == 1)
			{
				Cores[i] = 1;
				Cores[i-1] = 0;
			}
			else
				Cores[i] = 0;
		}
		
		//Procura se alguma instrução inicia naquele ciclo
		for(int i : inicioInst)
		{
		
			if(nCiclo == i)
			{
				Cores[0] = 1;
				break;
			}
		}
		
		return Cores;
	}
	
}

