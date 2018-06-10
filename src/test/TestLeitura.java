package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import simulador_de_Pipeline.Instrucao;
import simulador_de_Pipeline.LerInstrucoes;

class TestLeitura {
	
	private final String path = "/home/danielmarx/Documents/eclipse/eclipse_workspace/simulador_de_Pipeline/data/dataTest";
	
	@Test
	void testLeituraArquivos() throws FileNotFoundException, IOException 
	{
		
		ArrayList<Instrucao> instrucoes = LerInstrucoes.lerArquivo(path);
		
		for(Instrucao i : instrucoes)
		{
			System.out.print(i.getAcao());
			ArrayList<String> regs = i.getRegistradores();
			
			for(String a : regs)
			{
				System.out.print(" " + a + " ");
			}
			System.out.println();
		}		
	}
	
}
