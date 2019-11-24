package com.daimler.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

import com.daimler.template.attribute.Attribute;
import com.daimler.template.expression.Expression;
import com.daimler.template.expression.GroovyExpression;
import com.daimler.template.tag.Tag;

/**
 * @author CK_Te
 *
 */
public class TemplateEngine {

  public static String parse(Document document, Map<String, Expression> expressions, Map<String, Tag> tags,
      Map<String, Attribute> attributes) {

    List<Element> elementsToRemove = new ArrayList<Element>();
    Map<Node, String> attributesToRemove = new HashMap<Node, String>();

    document.traverse(new NodeVisitor() {
      @Override
      public void head(Node node, int depth) {

        if (node instanceof TextNode) {
          TextNode textNode = (TextNode) node;
          String text = textNode.getWholeText().trim();

          if (!text.isEmpty()) {
            expressions.forEach((expressionName, expressionProcessor) -> {
              ((Element) textNode.parent()).html(expressionProcessor.process(text));
            });
          }

        } else if (node instanceof DataNode) {
          DataNode dataNode = (DataNode) node;

          tags.forEach((tagName, tagProcessor) -> {
            tagProcessor.process(dataNode, elementsToRemove);
          });
        } else if (node != null && !node.nodeName().startsWith("#")) {
          if (node.attributes() != null && node.attributes().size() > 0) {
            node.attributes().forEach(attribute -> {
              node.attr(attribute.getKey(), expressions.get(GroovyExpression.NAME).process(attribute.getValue()));
              attributes.forEach((attributeName, attributeProsessor) -> {
                attributeProsessor.process(attribute.getKey(), attribute.getValue(), node, elementsToRemove,
                    attributesToRemove);
              });
            });
          }
        }
      }

      @Override
      public void tail(Node node, int depth) {
      }
    });

    elementsToRemove.forEach(element -> element.remove());
    attributesToRemove.forEach((node, key) -> {
      node.removeAttr(key);
    });

    return document.toString();
  }
}
