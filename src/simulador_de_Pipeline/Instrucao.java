package simulador_de_Pipeline;

import java.util.ArrayList;

public class Instrucao 
{
	private String acao;
	private ArrayList<String> registradores;

	
	public Instrucao ()
	{
		registradores = new ArrayList<String>(3);
	}
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public void setRegistrador(String v)
	{
		registradores.add(v);
	}
	
	public  ArrayList<String> getRegistradores()
	{
		return registradores;
	}
}
