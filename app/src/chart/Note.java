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

    double relX;
    int extraParam;

    public Note(NoteType type, long timeMs, int track, int direction, double relX, long duration, int extraParam) {
        this.type = type;
        this.timeMs = timeMs;
        this.duration = duration;
        this.track = track;
        this.relX = relX;
        this.direction = direction;
        this.extraParam = extraParam;
    }

    public Note(NoteType type, long timeMs, int track, int direction, double relX) {
        if (!type.equals(NoteType.TAP))
            throw new IllegalArgumentException("Only TAP can be created without duration");
        this(type, timeMs, track, direction, relX, 0, 0);
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

    public double getRelX() {
        return relX;
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
                ", relX='" + getRelX() + "'" +
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
        long temp;
        temp = Double.doubleToLongBits(relX);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (Double.doubleToLongBits(relX) != Double.doubleToLongBits(other.relX))
            return false;
        if (extraParam != other.extraParam)
            return false;
        return true;
    }
}
