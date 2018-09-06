package test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class Database {

    public Optional<String> read(String name) {
        try {
            return Optional.of(new String(Files.readAllBytes(Paths.get(".wiki/" + name)), StandardCharsets.UTF_8));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public void save(String name, String document) {
        try {
            Files.write(Paths.get(".wiki/" + name), document.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
