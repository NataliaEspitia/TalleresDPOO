package uniandes.dpoo.taller4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LightsOut extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentpane;
	
	public LightsOut()
	{
		
		super("LightsOut");
		this.setBounds(600,600,600,600);
		contentpane.setLayout(new BorderLayout());
		this.setVisible(true);
		
		
	}

	
	public static final Font FONT = new Font("Arial", Font.BOLD, 16);
	public static final Color BASECOL = new Color(130, 192, 233);
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
				}
			}
		});
	}

}
