package chart;

public class Note {
    public static final int DIRECTION_RESET = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final int DIRECTION_RIGHT = 4;
    public static final int DIRECTION_BOTH_SIDE = 5;

    NoteType type;

    long timeMs;
    long duration;

    int track;
    int direction;

    int extraParam;

    public Note(NoteType type, long timeMs, int track, int direction, long duration, int extraParam) {
        this.type = type;
        this.timeMs = timeMs;
        this.duration = duration;
        this.track = track;
        this.direction = direction;
        this.extraParam = extraParam;
    }

    public Note(NoteType type, long timeMs, int track, int direction) {
        if (!type.equals(NoteType.TAP))
            throw new IllegalArgumentException("Only TAP can be created without duration");
        this(type, timeMs, track, direction, 0, 0);
    }

    public NoteType getType() {
        return type;
    }

    public long getTimeMs() {
        return timeMs;
    }

    public long getDuration() {
        return duration;
    }

    public int getTrack() {
        return track;
    }

    public int getDirection() {
        return direction;
    }

    public int getExtraParam() {
        return extraParam;
    }

    @Override
    public String toString() {
        return "{" +
                " type='" + getType() + "'" +
                ", timeMs='" + getTimeMs() + "'" +
                ", duration='" + getDuration() + "'" +
                ", track='" + getTrack() + "'" +
                ", direction='" + getDirection() + "'" +
                ", extraParam='" + getExtraParam() + "'" +
                "}";
    }
}
