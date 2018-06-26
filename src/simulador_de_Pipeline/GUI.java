package simulador_de_Pipeline;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class GUI extends JFrame {
	private Estagios estgs;
	
	public GUI() {
		super("Simulador MIPS Pipeline");
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		estgs = new Estagios();
		add(estgs);
		
		setVisible(true);
		setSize(750, 500);
	}
	
	public void atualizaSequencia(int[] seq) {
		for (int i = 0; i < seq.length; i++) {
			estgs.alterarEstado(i, seq[i]);
		}
	}
	
	private class Estagios extends JPanel {
		private int[] estagio;
		private ImageIcon[] imagens;
		
		JLabel if_e, id_e, ex_e, mem_e, wb_e;
		
		public Estagios() {
			super();
			this.estagio = new int[5];
			for (int i : this.estagio) {
				i = 0;
			}
			
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
		
		public void alterarEstado(int pos, int valor) {
			String prepath = imagens[pos].getDescription();
			String path = prepath + valor + ".png";
			imagens[pos] = new ImageIcon(path, prepath);
			redraw();
			
		}
		
		private void redraw() {
			if_e.setIcon(imagens[0]);
			id_e.setIcon(imagens[1]);
			ex_e.setIcon(imagens[2]);
			mem_e.setIcon(imagens[3]);
			wb_e.setIcon(imagens[4]);
		}
		
	}
}
