package com.daimler.template.tag;

import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;

import com.daimler.utils.GroovyRunner;

/**
 * @author CK_Te
 *
 */
public class GroovyScriptTag implements Tag {

  public final static String NAME = "GroovyScriptTag";

  public final static String GROOVY_TYPE = "server/groovy";

  @Override
  public boolean isTag(String tagName, Attributes attributes) {
    return SCRIPT_TAG.equals(tagName) && GROOVY_TYPE.equals(attributes.get("type"));
  }

  @Override
  public void process(DataNode dataNode, List<Element> elementsToRemove) {
    if (isTag(dataNode.parent().nodeName(), dataNode.parent().attributes())) {
      GroovyRunner.run(dataNode.getWholeData());
      elementsToRemove.add((Element) dataNode.parent());
    }
  }

  public String toString() {
    return NAME;
  }

}
