package com.daimler.template.tag;

import org.jsoup.nodes.Element;

public class GroovyScriptTag implements Tag {

  public final static String GROOVY_TYPE = "server/groovy";

  public boolean filter(Element element) {
    return SCRIPT_TAG.equals(element.tagName()) && element.hasAttr("type") && GROOVY_TYPE.equals(element.attr("type"));
  }

}
