package com.daimler.template.attribute;

public class DataIfAttribute implements Attribute {

  public static final String DATA_IF = "data-if";

  @Override
  public boolean filter(String attribute) {
    return false;
  }

  @Override
  public String process() {
    return null;
  }

}
