package judgment;

import chart.Note;

public class JudgeResult {
    static enum State {
        Perfect, Great, Miss, NotInRange
    }

    static enum Timing {
        Early, Critical, Late
    }

    long timeOffset;
    State state;
    Timing timing;

    Note note;

    public JudgeResult(Note note, long timeOffset, State state, Timing timing) {
        this.note = note;
        this.timeOffset = timeOffset;
        this.state = state;
        this.timing = timing;
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
                " timeOffset='" + getTimeOffset() + "'" +
                ", state='" + getState() + "'" +
                ", timing='" + getTiming() + "'" +
                ", note='" + getNote() + "'" +
                "}";
    }
}
