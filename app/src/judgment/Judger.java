package judgment;

import java.util.Queue;

import chart.Chart;
import chart.Note;
import global.Database;
import input.InputEvent;

public class Judger {
    Queue<InputEvent> eventQueue;
    long startTimeStamp;
    Chart currentChart;
    Note[] firstNode = new Note[Database.TRACK_COUNT];

    public long getInputRelativeTime(InputEvent event) {
        return (event.getTimestamp() - startTimeStamp) / 1_000_000;
    }

    public long getNoteHitOffset(InputEvent event, Note note) {
        return getInputRelativeTime(event) - note.getTimeMs();
    }

    public long getRelativeChartTime() {
        return (System.nanoTime() - startTimeStamp) / 1_000_000;
    }

    public JudgeResult judgeNode(InputEvent event, Note note) {
        long hitOffset = getNoteHitOffset(event, note);
        long hitOffsetAbsolute = Math.abs(hitOffset);
        JudgeResult.State hitState;
        JudgeResult.Timing timing;

        if (hitOffset < -Database.JUDGEMENT_EARLY_RANGE)
            hitState = JudgeResult.State.NotInRange;
        else if (hitOffset > Database.JUDGEMENT_LATE_RANGE)
            hitState = JudgeResult.State.Miss;
        else if (hitOffsetAbsolute >= Database.JUDGEMENT_PERFECT_WINDOW)
            hitState = JudgeResult.State.Perfect;
        else if (hitOffsetAbsolute >= Database.JUDGEMENT_GREAT_RANGE)
            hitState = JudgeResult.State.Great;
        else
            hitState = JudgeResult.State.Miss;

        if (hitOffsetAbsolute >= Database.JUDGEMENT_CRITICAL_WINDOW)
            timing = JudgeResult.Timing.Critical;
        else if (hitOffset > 0)
            timing = JudgeResult.Timing.Late;
        else
            timing = JudgeResult.Timing.Early;

        return new JudgeResult(hitOffset, hitState, timing);
    }

    public void processFrame() {
        long frameTime = getRelativeChartTime();
        FRAME_LOOP: while (true) {
            FILL_LOOP: for (int track = 0; track < Database.TRACK_COUNT; track++) {
                if (firstNode[track] == null)
                    firstNode[track] = currentChart.popNote(track);
                if (firstNode[track] != null) {
                    if (frameTime - firstNode[track].getTimeMs() > Database.JUDGEMENT_LATE_RANGE) {
                        // TODO: Late Miss
                        track--;
                        continue FILL_LOOP;
                    }
                }
            }

            if (eventQueue.isEmpty())
                break FRAME_LOOP;
            InputEvent event = eventQueue.poll();
            // TODO: Judge Event
            judgeNode(event, firstNode[findTrack(event)]);
        }
    }

    public static int findTrack(InputEvent event) {
        // TODO: Track FInder
    }
}
