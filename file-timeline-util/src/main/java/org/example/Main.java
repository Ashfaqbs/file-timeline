package org.example;

import org.example.service.FileVersionManager;
import org.example.service.FileWatcher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get("C://tmp//git//file-timeline//file-timeline-util//sample.txt");

        FileVersionManager manager = new FileVersionManager(filePath);
        FileWatcher watcher = new FileWatcher(manager);

        // Start file watching in a separate thread
        new Thread(() -> {
            try {
                watcher.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Simple console interface
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type 'history' to view version history or 'exit' to quit:");
            String cmd = scanner.nextLine();
            if (cmd.equals("history")) {
                manager.printHistory();
            } else if (cmd.equals("exit")) {
                break;
            }
        }
    }
}
