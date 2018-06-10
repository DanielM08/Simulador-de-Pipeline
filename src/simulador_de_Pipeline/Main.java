package simulador_de_Pipeline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

	    String texto = "100($s2)";
	    String regex = "\\((?<meuGrupo>.*?)\\)";
	    String retorno = "";

	    Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);;
	    Matcher comparator = pattern.matcher(texto);
	    if (comparator.find(0)){
	        retorno = comparator.group("meuGrupo");
	    }

	    System.out.println(retorno);
	}

}
