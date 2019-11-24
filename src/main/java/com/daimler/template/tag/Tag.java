package com.daimler.template.tag;

import org.jsoup.nodes.Element;

public interface Tag {

  public final static String SCRIPT_TAG = "script";

  public boolean filter(Element element);

}
