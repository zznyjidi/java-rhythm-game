package render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.Timer;

import chart.Chart;

public class LevelManager implements ActionListener {
    Timer renderingFrameTimer = new Timer(1000 / 60, this);
    Lock frameLock = new ReentrantLock();

    PlayField playField;

    public LevelManager() {
        playField = new PlayField();
    }

    public void startLevel(Chart chart) {
        playField.loadChart(chart);

        long frameTime = System.nanoTime();
        playField.startGame(frameTime);
        renderingFrameTimer.start();
    }

    public JPanel getPlayField() {
        return playField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!frameLock.tryLock()) {
            System.err.println("RO");
            return;
        }
        try {
            long nanoTime = System.nanoTime();

            playField.processFrame(nanoTime);

            playField.repaint();
        } finally {
            frameLock.unlock();
        }
    }
}
