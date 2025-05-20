package org.example.service;

import org.example.dto.FileDelta;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FileVersionManager {
    private final Path filePath;
    private final Deque<FileDelta> history = new LinkedList<>();
    private List<String> lastSnapshot = new ArrayList<>();
    private int versionCounter = 0;

    public FileVersionManager(Path filePath) throws IOException, IOException{
        this.filePath = filePath;
        this.lastSnapshot = Files.readAllLines(filePath);
        history.add(createDelta("v0", lastSnapshot, new ArrayList<>()));
    }

    public void checkForChanges() throws IOException, IOException {
        List<String> current = Files.readAllLines(filePath);
        if (!current.equals(lastSnapshot)) {
            versionCounter++;
            String versionId = "v" + versionCounter;
            FileDelta delta = createDelta(versionId, current, lastSnapshot);
            if (history.size() == 3) history.removeFirst();
            history.addLast(delta);
            lastSnapshot = current;
        }
    }

    private FileDelta createDelta(String versionId, List<String> current, List<String> previous) {
        List<String> added = new ArrayList<>(current);
        added.removeAll(previous);
        List<String> removed = new ArrayList<>(previous);
        removed.removeAll(current);
        return new FileDelta(versionId, added, removed, LocalDateTime.now());
    }

    public void printHistory() {
        for (FileDelta delta : history) {
            System.out.println("Version " + delta.getVersionId());
            System.out.println("Added: " + delta.getAddedLines());
            System.out.println("Removed: " + delta.getRemovedLines());
            System.out.println("Time: " + delta.getTimestamp());
            System.out.println("-----");
        }
    }


    public Path getFilePath() {
        return this.filePath;
    }

}
