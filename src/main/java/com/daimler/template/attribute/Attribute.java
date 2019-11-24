package com.daimler.template.attribute;

import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * @author CK_Te
 *
 */
public interface Attribute {

  /**
   * Check if attribute
   * 
   * @param key
   * @return
   */
  public boolean isAttribute(String key);

  /**
   * Process attribute
   * 
   * @param key
   * @param value
   * @param node
   * @param elementsToDelete
   * @param attributesToRemove
   */
  public void process(String key, String value, Node node, List<Element> elementsToDelete,
      Map<Node, String> attributesToRemove);
}
