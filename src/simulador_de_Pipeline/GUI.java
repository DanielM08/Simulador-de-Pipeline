package simulador_de_Pipeline;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;

public class GUI extends JFrame 
{	
	private Estagios estgs;
	InterpretadorInstrucoes iI;
	
	public GUI(InterpretadorInstrucoes iI) 
	{
		super("Simulador MIPS Pipeline");
		
		this.iI = iI;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		estgs = new Estagios();
		
		JPanel controlPanel = makeFrame();
						
		Container contents = getContentPane();        
        contents.add(estgs, BorderLayout.CENTER);
        contents.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		setSize(750, 500);
	}
	
	public JPanel makeFrame()
	{
		JPanel contentPanel = new JPanel();
		
		JButton b = new JButton("Prox. Ciclo");
				
		contentPanel.add(b);
		
		b.addActionListener(new ActionListener() 
		{					
			public void actionPerformed(ActionEvent arg0) 
			{	
				int[] seqAnterior = estgs.getEstagios();
				int[] seq = vetorColorir(iI.getInicioInt(), estgs.getNCiclo(), seqAnterior);								
				
				atualizaSequencia(seq);
			}
		});
				
		return contentPanel;
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
	
	public void atualizaSequencia(int[] seq) 
	{
		estgs.setNCiclo(); //Um ciclo ocorreu
		estgs.setEstagios(seq); //Atualiza configuração do estágio
		
		for (int i = 0; i < seq.length; i++) 
		{
			estgs.alterarEstado(i, seq[i]);
		}
	}
	
	private class Estagios extends JPanel 
	{
		private int[] estagio;
		private int nCiclo;
		
		private ImageIcon[] imagens;
		
		JLabel if_e, id_e, ex_e, mem_e, wb_e;
		
		public Estagios() 
		{
			super();
			this.estagio = new int[5];
			for (int i = 0; i < 5; i++) 
			{
				this.estagio[i] = 0;
			}
			this.nCiclo = 1;
			
			imagens = new ImageIcon[5];
			// if
			imagens[0] = new ImageIcon("assets/if_0.png", "assets/if_");
			// id
			imagens[1] = new ImageIcon("assets/id_0.png", "assets/id_");
			// ex
			imagens[2] = new ImageIcon("assets/ex_0.png", "assets/ex_");
			// mem
			imagens[3] = new ImageIcon("assets/mem_0.png", "assets/mem_");
			// wb
			imagens[4] = new ImageIcon("assets/wb_0.png", "assets/wb_");
			
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			if_e = new JLabel(imagens[0]);
			id_e = new JLabel(imagens[1]);
			ex_e = new JLabel(imagens[2]);
			mem_e = new JLabel(imagens[3]);
			wb_e = new JLabel(imagens[4]);
			
			add(if_e);
			add(id_e);
			add(ex_e);
			add(mem_e);
			add(wb_e);
		}
		
		public void setEstagios(int[] est)
		{
			this.estagio = est;
		}
		
		public int[] getEstagios() 
		{
			return this.estagio;
		}
		
		public void setNCiclo()
		{
			this.nCiclo++;
		}
		
		public int getNCiclo()
		{
			return this.nCiclo;
		}
		
		public void alterarEstado(int pos, int valor) 
		{
			String prepath = imagens[pos].getDescription();
			String path = prepath + valor + ".png";
			imagens[pos] = new ImageIcon(path, prepath);
			redraw();			
		}
		
		private void redraw() 
		{
			if_e.setIcon(imagens[0]);
			id_e.setIcon(imagens[1]);
			ex_e.setIcon(imagens[2]);
			mem_e.setIcon(imagens[3]);
			wb_e.setIcon(imagens[4]);
		}		
	}
}
