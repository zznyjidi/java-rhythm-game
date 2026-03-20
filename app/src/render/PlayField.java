package render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import chart.Chart;
import global.Config;
import input.KeyMap;
import judgment.JudgeResult;
import judgment.Judger;

public class PlayField extends JPanel {
    Lock frameLock = new ReentrantLock();

    KeyMap keyMap;
    Judger judger;

    Chart chart;
    Set<Drawable> drawingObject = new CopyOnWriteArraySet<>();
    Queue<JudgeResult> judgeResults;

    public PlayField() {
        this.setPreferredSize(Config.screenSize);
        this.setDoubleBuffered(true);

        keyMap = new KeyMap(this);
        keyMap.initKeyBind();

        judger = new Judger(keyMap.getQueue());
        judgeResults = judger.getQueue();
    }

    public void loadChart(Chart chart) {
        this.chart = chart.copy();
        judger.setChart(chart.copy());
    }

    public void startGame(long frameTime) {
        judger.startGame(frameTime);
    }

    public void processFrame(long frameTime) {
        if (!frameLock.tryLock()) {
            System.err.println("O");
            return;
        }
        try {
            Set<Drawable> removeElements = new HashSet<>();

            // Remove Expired Elements
            for (Drawable drawable : drawingObject) {
                if (drawable.isExpired(frameTime))
                    removeElements.add(drawable);
            }

            // Remove Judged Elements & Add Judge Results
            while (!judgeResults.isEmpty()) {
                JudgeResult result = judgeResults.poll();
                drawingObject.add(result);
                removeElements.add(result.getNote());
            }

            // TODO: Add Elements

            // Commit Removal
            for (Drawable drawable : removeElements)
                drawingObject.remove(drawable);
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
