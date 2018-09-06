package test;

import spark.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class Documents {

    private final Database db = new Database();
    private final Renderer renderer = new Renderer();

    private final String view = loadResource("/view.html");
    private final String edit = loadResource("/edit.html");
    private final String empty = edit.replace("$DOC$", "");

    private String loadResource(String resource) {
        try (InputStream input = getClass().getResourceAsStream(resource)) {
            return IOUtils.toString(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String viewMode(String name) {
        return db.read(name)
                .map(renderer::render)
                .map(e -> view.replace("$DOC$", e))
                .orElse(empty);
    }

    public String editMode(String name) {
        return db.read(name)
                .map(e -> edit.replace("$DOC$", e))
                .orElse(empty);
    }

    public void save(String name, String document) {
        db.save(name, document);
    }
}
