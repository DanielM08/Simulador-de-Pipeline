package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import simulador_de_Pipeline.InterpretadorInstrucoes;

class TestInterpretadorInstrucoes {
	
	private final String path = "/home/danielmarx/Documents/eclipse/eclipse_workspace/Simulador-de-Pipeline/data/dataTest2";
	
	@Test
	void test() throws FileNotFoundException, IOException {
			InterpretadorInstrucoes iI = new InterpretadorInstrucoes(path);
			
			System.out.println("Número de ciclos: " + iI.getNumeroCiclos());
			
			Deque<Integer> inicioInst = iI.getInicioInt();			
			
			int aux = 1;
			for(int i : inicioInst)
			{				
				System.out.println("Inicio instrução " + aux + ": " + i);
				aux++;
			}
			
			//assertEquals(iI.getNumeroCiclos(), 9);
	}

}
