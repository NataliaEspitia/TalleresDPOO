package uniandes.dpoo.taller4.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

@SuppressWarnings("serial")
public class Game extends JFrame {
	

	private JPanel contentPane;
	private String playerName;
	private PanelGrid game;
	private Puntaje puntaje;
	private Botones buttons;
	private SizeDifficulty header;
	private Tablero model;
	private Top10 top10;
	private static final File DATA = new File("data/top10.csv");
	/**
	 * Create the frame.
	 * 
	 * @param top
	 */
	public Game() {
		
		setTitle("LightsOut");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(LightsOut.BASECOL);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));

		setContentPane(contentPane);

		this.top10 = new Top10();
		top10.cargarRecords(DATA);

		header = new SizeDifficulty();
		contentPane.add(header, BorderLayout.NORTH);

		JPanel container = new JPanel();
		container.setBackground(LightsOut.BASECOL);
		contentPane.add(container, BorderLayout.EAST);
		container.setLayout(new BorderLayout());

		buttons = new Botones(this);
		container.add(buttons, BorderLayout.CENTER);

		this.playerName = getUser();
		puntaje = new Puntaje(this.playerName);
		container.add(puntaje, BorderLayout.SOUTH);

		game = new PanelGrid(this, 1);
		contentPane.add(game, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					top10.salvarRecords(DATA);
				} catch (Exception e) {
				}
			}
		});

		pack();
		setLocationRelativeTo(null);
	}

	private String getUser() {
		String t = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Lights Out",
				JOptionPane.PLAIN_MESSAGE);

		return t == null || t.isBlank() ? "Kvothe" : t.replaceAll("\\s", "");
	}

	public boolean isOn(int i, int j) {
		if (game.getSquare() == 1)
			return true;
		return model.darTablero()[i][j];
	}

	public void newGame() {
		int cell = header.getSizeGrid();
		int diff = header.getDifficultyGame();

		model = new Tablero(cell);
		game.setSquare(cell);

		model.desordenar(diff);
		game.repaint();
	}

	public void restart() {
		model.reiniciar();
		game.repaint();
	}

	public void top10() {
		new ListTop10(top10.darRegistros().stream().toArray(RegistroTop10[]::new));
	}

	public void change() {
		puntaje.setJugadas(0);
		puntaje.setPlayer(getUser());
		newGame();
	}

	public void play(int[] cells) {
		if (model != null) {
			model.jugar(cells[0], cells[1]);
			puntaje.setJugadas(model.darJugadas());
			if (model.tableroIluminado()) {
				int puntaje = model.calcularPuntaje();
				String lbl = "¡GANASTE!"+ "puntaje: " + puntaje;
				if (top10.esTop10(puntaje)) {
					lbl += "\nY LLEGASTE AL TOP";
					top10.agregarRegistro(playerName, puntaje);
				}
				JOptionPane.showMessageDialog(null, lbl);
			}
		}
	}

}
