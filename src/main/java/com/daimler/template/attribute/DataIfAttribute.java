package com.daimler.template.attribute;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.daimler.utils.GroovyRunner;

/**
 * @author CK_Te
 *
 */
public class DataIfAttribute implements Attribute {

  public static final String NAME = "DataIfAttribute";

  public static final String DATA_IF = "data-if";

  @Override
  public boolean isAttribute(String key) {
    return DATA_IF.equals(key);
  }

  @Override
  public void process(String key, String value, Node node, List<Element> elementsToRemove,
      Map<Node, String> attributesToRemove) {
    if (isAttribute(key)) {
      if (!(Boolean) GroovyRunner.run(value)) {
        elementsToRemove.add((Element) node);
      } else {
        attributesToRemove.put(node, key);
      }
    }
  }

  @Override
  public String toString() {
    return NAME;
  }

}
