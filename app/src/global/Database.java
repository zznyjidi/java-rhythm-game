package global;

import chart.Chart;

public class Database {
    public static final int TRACK_COUNT = 3;

    public static final int JUDGEMENT_PERFECT_WINDOW = 30;
    public static final int JUDGEMENT_GREAT_WINDOW = 30;
    public static final int JUDGEMENT_EARLY_MISS_WINDOW = 40;
    public static final int JUDGEMENT_CRITICAL_WINDOW = 40;

    public static final int JUDGEMENT_GREAT_RANGE = JUDGEMENT_PERFECT_WINDOW + JUDGEMENT_GREAT_WINDOW;
    public static final int JUDGEMENT_LATE_RANGE = JUDGEMENT_GREAT_RANGE;
    public static final int JUDGEMENT_EARLY_RANGE = JUDGEMENT_LATE_RANGE + JUDGEMENT_EARLY_MISS_WINDOW;

    public static char[][] TRACK_KEYBIND = {
            { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' },
            { 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';' },
            { 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/' }
    };

    public static Chart currentChart;
}
