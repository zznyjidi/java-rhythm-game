package judgment;

import java.util.Queue;

import chart.Note;
import input.InputEvent;

public class Judger {
    Queue<InputEvent> eventQueue;
    long startTimeStamp;

    public long getInputRelativeTime(InputEvent event) {
        return (event.getTimestamp() - startTimeStamp) / 1_000_000;
    }

    public long getNoteHitOffset(InputEvent event, Note note) {
        return getInputRelativeTime(event) - note.getTimeMs();
    }

    public JudgeResult judgeNode(InputEvent event, Note note) {
        long hitOffset = getNoteHitOffset(event, note);
        long hitOffsetAbsolute = Math.abs(hitOffset);
        JudgeResult.State hitState;
        JudgeResult.Timing timing;

        if (hitOffset < -100)
            hitState = JudgeResult.State.NotInRange;
        else if (hitOffset > 60)
            hitState = JudgeResult.State.Miss;
        else if (hitOffsetAbsolute >= 30)
            hitState = JudgeResult.State.Perfect;
        else if (hitOffsetAbsolute >= 60)
            hitState = JudgeResult.State.Great;
        else
            hitState = JudgeResult.State.Miss;

        if (hitOffsetAbsolute >= 10)
            timing = JudgeResult.Timing.Critical;
        else if (hitOffset > 0)
            timing = JudgeResult.Timing.Late;
        else
            timing = JudgeResult.Timing.Early;

        return new JudgeResult(hitOffset, hitState, timing);
    }
}
