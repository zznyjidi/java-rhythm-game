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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + (int) (timeMs ^ (timeMs >>> 32));
        result = prime * result + (int) (duration ^ (duration >>> 32));
        result = prime * result + track;
        result = prime * result + direction;
        result = prime * result + extraParam;
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
        Note other = (Note) obj;
        if (type != other.type)
            return false;
        if (timeMs != other.timeMs)
            return false;
        if (duration != other.duration)
            return false;
        if (track != other.track)
            return false;
        if (direction != other.direction)
            return false;
        if (extraParam != other.extraParam)
            return false;
        return true;
    }
}
