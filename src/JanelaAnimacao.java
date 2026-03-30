import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class JanelaAnimacao extends JPanel {
    private File[] frames;
    private int frameAtual = 0;

    public JanelaAnimacao(String pastaFrames) {
        File pasta = new File(pastaFrames);
        frames = pasta.listFiles((dir, name) -> name.endsWith(".png"));

        if (frames != null && frames.length > 0) {
            Arrays.sort(frames);

            Timer timer = new Timer(50, e -> {
                if (frameAtual < frames.length - 1) {
                    frameAtual++;
                    repaint();
                } else {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Animação finalizada.");
                }
            });
            timer.start();
        } else {
            System.out.println("Erro: Nenhum frame encontrado em: " + pastaFrames);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (frames != null && frameAtual < frames.length) {
            try {
                BufferedImage img = javax.imageio.ImageIO.read(frames[frameAtual]);
                g2d.drawImage(img, 0, 0, null);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
