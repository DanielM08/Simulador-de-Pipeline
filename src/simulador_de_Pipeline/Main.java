package simulador_de_Pipeline;

import simulador_de_Pipeline.GUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		String path = "/home/danielmarx/Documents/eclipse/eclipse_workspace/Simulador-de-Pipeline/data/dataTest";
		
		InterpretadorInstrucoes iI = new InterpretadorInstrucoes(path);
		
		GUI gui = new GUI(iI);
				
		System.out.println("Número de ciclos: " + iI.getNumeroCiclos());
		
		Deque<Integer> inicioInst = iI.getInicioInt();			
		
		int aux = 1;
		for(int i : inicioInst)
		{				
			System.out.println("Inicio instrução " + aux + ": " + i);
			aux++;
		}
	}	
}

