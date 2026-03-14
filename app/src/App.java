import javax.swing.JFrame;

import global.Config;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class App {
    public static void main(String[] args) {
        JFrame game = new JFrame("Game");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screen = graphicEnv.getDefaultScreenDevice();

        if (screen.isFullScreenSupported()) {
            // Exclusive FullScreen
            game.setVisible(true);
            screen.setFullScreenWindow(game);
        } else {
            // Borderless FullScreen
            game.setUndecorated(true);
            game.setSize(Config.screenSize);
            game.setLocation(0, 0);
            game.setExtendedState(Frame.MAXIMIZED_BOTH);
            game.setVisible(true);
        }
    }
}
