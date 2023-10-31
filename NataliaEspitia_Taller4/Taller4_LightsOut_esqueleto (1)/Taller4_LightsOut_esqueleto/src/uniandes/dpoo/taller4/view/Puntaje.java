package uniandes.dpoo.taller4.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Puntaje extends JPanel {

	private JLabel[] values;
	private String nickname;
	private int play = 0;

	public Puntaje(String playerName) {
		this.nickname = playerName;
		setBorder(new EmptyBorder(8, 8, 8, 8));
		setBackground(LightsOut.BASECOL);
		setLayout(new GridLayout(1, 2, 1, 2));

		JLabel[] keys = new JLabel[2];
		String[] keysLbl = { "Jugadas   ", "Player" };
		values = new JLabel[2];
		String[] valuesLbls = new String[] { "" + play, nickname };

		for (int i = 0; i < 2; i++) {
			keys[i] = new JLabel(keysLbl[i]);
			keys[i].setForeground(Color.WHITE);
			keys[i].setFont(LightsOut.FONT);
			values[i] = new JLabel(valuesLbls[i]);
			values[i].setForeground(Color.WHITE);
			values[i].setFont(LightsOut.FONT);
			add(keys[i]);
			add(values[i]);
		}
	}

	public void setJugadas(int i) {
		this.play = i;
		values[0].setText("" + i);
	}

	public void setPlayer(String name) {
		values[1].setText(name);
	}

	public int getJugadas() {
		return this.play;
	}
}
