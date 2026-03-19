package chart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Chart {
    public static int TRACK_COUNT = 3;

    List<List<Note>> notes;

    public Chart(List<Note> allNotes) {
        notes = new ArrayList<>();
        for (int i = 0; i < TRACK_COUNT; i++) {
            notes.add(new LinkedList<>());
        }

        for (Note note : allNotes) {
            notes.get(note.getTrack()).add(note);
        }
    }

    public Chart(Chart chart) {
        notes = new ArrayList<>();
        for (List<Note> track : chart.notes) {
            notes.add(new LinkedList<>(track));
        }
    }

    public Chart copy() {
        return new Chart(this);
    }

    public Note popNote(int track) {
        return ((LinkedList<Note>) (notes.get(track))).pollFirst();
    }
}
