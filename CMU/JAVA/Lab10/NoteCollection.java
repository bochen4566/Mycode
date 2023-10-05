package NotePackage;

import java.util.ArrayList;
import java.util.Collections;

public class NoteCollection {
    private ArrayList<Note> noteList;

    public NoteCollection() {
        noteList = new ArrayList<>();
    }

    public void add(Note note) {
        noteList.add(note);
    }

    public ArrayList<Note> getAllNotes() {
        return noteList;
    }

    public boolean isEmpty() {
        return noteList.isEmpty();
    }
    public Note getNoteByNumber(int noteNumber) {
        for (Note note : noteList) {
            if (note.getNoteNumber() == noteNumber) {
                return note;
            }
        }
        return null;
    }

    public ArrayList<Note> getNoteByName(String name) {
        ArrayList<Note> notesWithName = new ArrayList<>();
        for (Note note : noteList) {
            if (note.getName().equals(name)) {
                notesWithName.add(note);
            }
        }
        return notesWithName;
    }

    public void sortByName() {
        Collections.sort(noteList);
    }
    public void sortByNumber(){
        Collections.sort(noteList, new NumberSorter());
    }
    public void sortBySize(){
        Collections.sort(noteList, new SizeSorter());
    }

}