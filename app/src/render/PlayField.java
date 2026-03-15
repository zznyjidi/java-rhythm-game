package render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import global.Config;
import input.KeyMap;
import judgment.Judger;

public class PlayField extends JPanel {
    Lock frameLock = new ReentrantLock();

    KeyMap keyMap;
    Judger judger;

    Set<Drawable> drawingObject = new CopyOnWriteArraySet<>();

    public PlayField() {
        this.setPreferredSize(Config.screenSize);
        this.setDoubleBuffered(true);

        keyMap = new KeyMap(this);
        keyMap.initKeyBind();

        judger = new Judger(keyMap.getQueue());
    }

    public void processFrame(long frameTime) {
        if (!frameLock.tryLock()) {
            System.err.println("O");
            return;
        }
        try {
            Set<Drawable> expiredElements = new HashSet<>();
            for (Drawable drawable : drawingObject) {
                if (drawable.isExpired())
                    expiredElements.add(drawable);
            }
            for (Drawable drawable : expiredElements) {
                drawingObject.remove(drawable);
            }
            // TODO: Add Elements
        } finally {
            frameLock.unlock();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D) g;

        long frameTime = judger.getRelativeChartTime();
        for (Drawable drawable : drawingObject) {
            drawable.drawElement(graphics2d, getSize(), frameTime);
        }
    }
}
