package stardancer.observatory.allskyFrontend;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;


public class DirectoryWatcher implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(DirectoryWatcher.class);

    private final Path path;
    private final WatchService watchService;
    private final WatchKey watchKey;


    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }


    public DirectoryWatcher(Path path) throws IOException {
        this.path = path;
        this.watchService = FileSystems.getDefault().newWatchService();
        this.watchKey = this.path.register(watchService, ENTRY_CREATE);
    }

    @Override
    public void run() {
        try {
             for(;;) {
                 //Wait for key to be signalled
                 WatchKey key = watchService.take();

                 if (this.watchKey != key) {
                     LOGGER.debug("Watchkey " + key.toString() + " not recognized!");
                     continue;
                 }

                 for (WatchEvent<?> event : key.pollEvents()) {
                     WatchEvent<Path> pathEvent = cast(event);
                     //TODO - HANDLE EVENT - NOTIFY LISTENERS
                 }

                 if (!key.reset()) {
                     break;
                 }
             }
        } catch (InterruptedException i) {
            return;
        }
    }
}
