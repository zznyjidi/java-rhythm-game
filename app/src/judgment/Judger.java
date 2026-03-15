package judgment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Timer;

import chart.Chart;
import chart.Note;
import global.Config;
import input.InputEvent;

public class Judger implements ActionListener {
    Timer judgementFrameTimer = new Timer(1, this);
    Lock frameLock = new ReentrantLock();

    Queue<InputEvent> inputEventQueue;
    Queue<JudgeResult> judgeEventQueue;
    long startTimeStamp;
    Chart currentChart;
    Note[] firstNode = new Note[Config.TRACK_COUNT];

    public Judger(Queue<InputEvent> inputEventQueue) {
        this.inputEventQueue = inputEventQueue;
        this.judgeEventQueue = new ConcurrentLinkedQueue<>();
    }

    public void startGame(long startTimeStamp, Chart chart) {
        this.startTimeStamp = startTimeStamp;
        this.currentChart = chart;
        this.judgementFrameTimer.start();
    }

    public long getInputRelativeTime(InputEvent event) {
        return (event.getTimestamp() - startTimeStamp) / 1_000_000;
    }

    public long getNoteHitOffset(InputEvent event, Note note) {
        return getInputRelativeTime(event) - note.getTimeMs();
    }

    public long getRelativeChartTime() {
        return (System.nanoTime() - startTimeStamp) / 1_000_000;
    }

    public Queue<JudgeResult> getQueue() {
        return judgeEventQueue;
    }

    public JudgeResult judgeNode(long frameTime, InputEvent event, Note note) {
        if (event.isPress()) {
            long hitOffset = getNoteHitOffset(event, note);
            long hitOffsetAbsolute = Math.abs(hitOffset);
            JudgeResult.State hitState;
            JudgeResult.Timing timing;

            if (hitOffset < -Config.JUDGEMENT_EARLY_RANGE)
                hitState = JudgeResult.State.NotInRange;
            else if (hitOffset > Config.JUDGEMENT_LATE_RANGE)
                hitState = JudgeResult.State.Miss;
            else if (hitOffsetAbsolute >= Config.JUDGEMENT_PERFECT_WINDOW)
                hitState = JudgeResult.State.Perfect;
            else if (hitOffsetAbsolute >= Config.JUDGEMENT_GREAT_RANGE)
                hitState = JudgeResult.State.Great;
            else
                hitState = JudgeResult.State.Miss;

            if (hitOffsetAbsolute >= Config.JUDGEMENT_CRITICAL_WINDOW)
                timing = JudgeResult.Timing.Critical;
            else if (hitOffset > 0)
                timing = JudgeResult.Timing.Late;
            else
                timing = JudgeResult.Timing.Early;

            return new JudgeResult(frameTime, event, note, hitOffset, hitState, timing);
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!frameLock.tryLock()) {
            IO.println("O");
            return;
        }
        try {
            long frameTime = getRelativeChartTime();
            FRAME_LOOP: while (true) {
                FILL_LOOP: for (int track = 0; track < Config.TRACK_COUNT; track++) {
                    if (firstNode[track] == null)
                        firstNode[track] = currentChart.popNote(track);
                    if (firstNode[track] != null) {
                        if (frameTime - firstNode[track].getTimeMs() > Config.JUDGEMENT_LATE_RANGE) {
                            judgeEventQueue.add(new JudgeResult(
                                    frameTime, null, firstNode[track],
                                    Config.JUDGEMENT_LATE_RANGE + 1,
                                    JudgeResult.State.Miss,
                                    JudgeResult.Timing.Late));
                            firstNode[track] = null;
                            track--;
                            continue FILL_LOOP;
                        }
                    }
                }

                if (inputEventQueue.isEmpty())
                    break FRAME_LOOP;
                InputEvent event = inputEventQueue.poll();
                if (firstNode[event.getTrack()] == null) {
                    judgeEventQueue.add(new JudgeResult(
                            frameTime, event, null, 0,
                            JudgeResult.State.NotInRange, null));
                    continue FRAME_LOOP;
                }
                JudgeResult result = judgeNode(frameTime, event, firstNode[event.getTrack()]);
                if (result != null) {
                    judgeEventQueue.add(result);
                    if (result.getState() != JudgeResult.State.NotInRange)
                        firstNode[event.getTrack()] = null;
                }
            }
        } finally {
            frameLock.unlock();
        }
    }
}
