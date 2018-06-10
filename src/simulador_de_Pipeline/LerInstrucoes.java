package simulador_de_Pipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LerInstrucoes 
{

	public static ArrayList<Instrucao> lerArquivo(String endereco) throws FileNotFoundException, IOException 
	{		
		File file = new File(endereco);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		
		ArrayList<Instrucao> instrucoes = new ArrayList<Instrucao>(); 					
		
		String inst;
		while ((inst = bufferedReader.readLine()) != null) 
		{
			StringTokenizer stringTokenizer = new StringTokenizer(inst);
			Instrucao novaInst = new Instrucao();
			
			int aux = 1;
			
			while(stringTokenizer.hasMoreTokens()) 
			{
				String fragmentoInst = stringTokenizer.nextToken().toLowerCase();
				
				if(aux == 1)
					novaInst.setAcao(fragmentoInst);
				else
				{
					if(aux == 3 && (novaInst.getAcao().equals("lw") || novaInst.getAcao().equals("sw")) )
					{
						String reg = pegaRegistrador(fragmentoInst); //Deixa apenas o valor do registrador
						novaInst.setRegistrador(reg);					
					}
					else
					{
						novaInst.setRegistrador(fragmentoInst);
					}
					
				}
				aux++;
			}		
			
			instrucoes.add(novaInst);
		}
		
		bufferedReader.close();
		return instrucoes;
	}
	
	private static String pegaRegistrador(String frag) 
	{	
	    String regex = "\\((?<meuGrupo>.*?)\\)";
	    String retorno = "";

	    Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);;
	    Matcher comparator = pattern.matcher(frag);
	    if (comparator.find(0)){
	        retorno = comparator.group("meuGrupo");
	    }

	    return retorno;
	}

}
