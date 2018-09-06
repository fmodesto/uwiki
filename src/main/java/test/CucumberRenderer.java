package test;

import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.CoreHtmlNodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

class CucumberRenderer extends CoreHtmlNodeRenderer {

    private final HtmlWriter html;

    CucumberRenderer(HtmlNodeRendererContext context) {
        super(context);
        this.html = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.<Class<? extends Node>>singleton(FencedCodeBlock.class);
    }

    @Override
    public void render(Node node) {
        FencedCodeBlock codeBlock = (FencedCodeBlock) node;
        if (!"cucumber".equals(codeBlock.getInfo())) {
            super.render(node);
        } else {
            renderCucumber(codeBlock);
        }
    }

    private void renderCucumber(FencedCodeBlock codeBlock) {
        System.out.println("HERE: " + codeBlock.getLiteral());
    }
}