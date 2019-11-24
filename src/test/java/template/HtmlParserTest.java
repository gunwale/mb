package template;

import static template.TestConst.RESOURCE_NAME;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;
import org.junit.Test;

public class HtmlParserTest {

  @Test
  public void test() throws IOException {
    Document document = Jsoup.parse(new File(getClass().getClassLoader().getResource(RESOURCE_NAME).getFile()),
        StandardCharsets.UTF_8.name());

    document.traverse(new NodeVisitor() {
      @Override
      public void head(Node node, int depth) {
        if (node instanceof TextNode) {
          TextNode textNode = (TextNode) node;
          String text = textNode.getWholeText().trim();
          if (!text.isEmpty()) {
            System.err.println("Text : " + textNode.getWholeText());
          }
        } else if (node instanceof DataNode) {
          DataNode textNode = (DataNode) node;
          System.err.println("Data : " + textNode.getWholeData());
        } else if (node != null && !node.nodeName().startsWith("#")) {
          if (node.attributes() != null && node.attributes().size() > 0) {
            System.err.println(node.nodeName() + " : " + node.attributes());
          }
        }
      }

      @Override
      public void tail(Node node, int depth) {

      }
    });
  }
}
