package judgment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import chart.Note;
import global.Config;
import input.InputEvent;
import render.Drawable;

public class JudgeResult implements Drawable {
    public static enum State {
        Perfect, Great, Miss, NotInRange
    }

    public static enum Timing {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (frameTime ^ (frameTime >>> 32));
        result = prime * result + ((inputEvent == null) ? 0 : inputEvent.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JudgeResult other = (JudgeResult) obj;
        if (frameTime != other.frameTime)
            return false;
        if (inputEvent == null) {
            if (other.inputEvent != null)
                return false;
        } else if (!inputEvent.equals(other.inputEvent))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        return true;
    }

    @Override
    public void drawElement(Graphics2D graphics2d, Dimension screenSize, long frameTime) {
        double centerX = screenSize.width * note.getRelX();
        double centerY = screenSize.height / Config.TRACK_COUNT * (note.getTrack() + 1);

        graphics2d.setColor(new Color(0x000000));
        graphics2d.drawString(state.name(), (float) centerX, (float) centerY);
    }

    @Override
    public boolean isExpired(long frameTime) {
        return (frameTime / 1_000_000) > (this.frameTime + Config.judgementResultDisplayTimeMs);
    }
}
