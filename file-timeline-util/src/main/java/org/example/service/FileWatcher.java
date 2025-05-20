package org.example.service;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher {
    private final FileVersionManager manager;
    private final Path watchDir;
    private final Path fileName;

    public FileWatcher(FileVersionManager manager) {
        this.manager = manager;
        this.watchDir = manager.getFilePath().getParent();
        this.fileName = manager.getFilePath().getFileName();
    }

    public void start() throws IOException, InterruptedException, IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        watchDir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                Path changed = (Path) event.context();
                if (changed.endsWith(fileName)) {
                    manager.checkForChanges();
                }
            }
            key.reset();
        }
    }
}
