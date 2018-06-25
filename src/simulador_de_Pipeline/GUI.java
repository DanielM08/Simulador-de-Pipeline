package simulador_de_Pipeline;

import javax.swing.*;
import java.awt.GridLayout;

public class GUI extends JFrame {
	public GUI() {
		super("Simulador MIPS Pipeline");
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Estagios estgs = new Estagios();
		add(estgs);
		
		setVisible(true);
		setSize(500, 500);
	}
	
	public class Estagios extends JPanel {
		private int[] estagio;
		private ImageIcon[] imagens;
		
		public Estagios() {
			super();
			this.estagio = new int[5];
			for (int i : this.estagio) {
				i = 0;
			}
			
			imagens = new ImageIcon[5];
			// if
			imagens[0] = new ImageIcon(getClass().getResource("../assets/if_0.png"));
			// id
			imagens[1] = new ImageIcon(getClass().getResource("../assets/id_0.png"));
			// ex
			imagens[2] = new ImageIcon(getClass().getResource("../assets/ex_0.png"));
			// mem
			imagens[3] = new ImageIcon(getClass().getResource("../assets/mem_0.png"));
			// wb
			imagens[4] = new ImageIcon(getClass().getResource("../assets/wb_0.png"));
			
			setLayout(new GridLayout(5,0));
			
			JLabel if_e = new JLabel(imagens[0]);
			add(if_e);
			
		}
		
	}
}
