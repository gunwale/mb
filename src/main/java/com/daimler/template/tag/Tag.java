package com.daimler.template.tag;

import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;

/**
 * @author CK_Te
 *
 */
public interface Tag {

  public final static String SCRIPT_TAG = "script";

  /**
   * Check is tag
   * 
   * @param tagName
   * @param attributes
   * @return
   */
  public boolean isTag(String tagName, Attributes attributes);

  /**
   * Process tag
   * 
   * @param dataNode
   * @param elementsToRemove
   */
  public void process(DataNode dataNode, List<Element> elementsToRemove);

}
