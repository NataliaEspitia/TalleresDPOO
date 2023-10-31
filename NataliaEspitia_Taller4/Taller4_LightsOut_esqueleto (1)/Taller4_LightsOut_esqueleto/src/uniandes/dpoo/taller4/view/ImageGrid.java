package uniandes.dpoo.taller4.view;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageGrid extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int rows;
    private final int cols;
    private final int cellSize;
    private final BufferedImage image;

    public ImageGrid(int rows, int cols, int cellSize, String imagePath) throws IOException {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.image = ImageIO.read(new File(imagePath));
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                g.drawImage(image, x, y, cellSize, cellSize, null);
            }
        }
    }

}
