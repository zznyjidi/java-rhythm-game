package judgment;

import chart.Note;
import input.InputEvent;

public class JudgeResult {
    static enum State {
        Perfect, Great, Miss, NotInRange
    }

    static enum Timing {
        Early, Critical, Late
    }

    long frameTime;
    InputEvent inputEvent;

    long timeOffset;
    State state;
    Timing timing;

    Note note;

    public JudgeResult(long frameTime, InputEvent inputEvent, Note note, long timeOffset, State state, Timing timing) {
        this.frameTime = frameTime;
        this.inputEvent = inputEvent;
        this.note = note;
        this.timeOffset = timeOffset;
        this.state = state;
        this.timing = timing;
    }

    public long getFrameTime() {
        return frameTime;
    }

    public InputEvent getInputEvent() {
        return inputEvent;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public State getState() {
        return state;
    }

    public Timing getTiming() {
        return timing;
    }

    public Note getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "{" +
                " frameTime='" + getFrameTime() + "'" +
                ", inputEvent='" + getInputEvent() + "'" +
                ", timeOffset='" + getTimeOffset() + "'" +
                ", state='" + getState() + "'" +
                ", timing='" + getTiming() + "'" +
                ", note='" + getNote() + "'" +
                "}";
    }
}
