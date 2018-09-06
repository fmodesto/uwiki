package test;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class Renderer {

    private final Parser parser = buildParser();
    private final HtmlRenderer renderer = buildHtmlRenderer();

    private Parser buildParser() {
        return Parser.builder().build();
    }

    private HtmlRenderer buildHtmlRenderer() {

        return HtmlRenderer.builder()
                .nodeRendererFactory(CucumberRenderer::new)
                .build();
    }

    public String render(String markup) {
        return renderer.render(parser.parse(markup));
    }
}
