package test;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

public class Application {

    private static Documents documents = new Documents();

    public static void main(String[] args) {
        redirect.get("/", "welcome-page");

        get("/:name", (request, response) -> {
            String name = request.params(":name");
            return request.queryParams("edit") != null ? documents.editMode(name) : documents.viewMode(name);
        });

        post("/:name", (request, response) -> {
            String name = request.params(":name");
            String document = request.queryParams("document");
            documents.save(name, document);
            response.redirect("/" + name);
            return null;
        });
    }
}
