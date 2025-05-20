package org.example.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FileDelta {
    String versionId;
    List<String> addedLines;
    List<String> removedLines;
    LocalDateTime timestamp;

    public FileDelta() {
    }

    public FileDelta(String versionId, List<String> addedLines, List<String> removedLines, LocalDateTime timestamp) {
        this.versionId = versionId;
        this.addedLines = addedLines;
        this.removedLines = removedLines;
        this.timestamp = timestamp;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public List<String> getAddedLines() {
        return addedLines;
    }

    public void setAddedLines(List<String> addedLines) {
        this.addedLines = addedLines;
    }

    public List<String> getRemovedLines() {
        return removedLines;
    }

    public void setRemovedLines(List<String> removedLines) {
        this.removedLines = removedLines;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FileDelta{" +
                "versionId='" + versionId + '\'' +
                ", addedLines=" + addedLines +
                ", removedLines=" + removedLines +
                ", timestamp=" + timestamp +
                '}';
    }



}
