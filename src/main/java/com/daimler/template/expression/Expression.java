package com.daimler.template.expression;

public interface Expression {

  default boolean filter(String value) {
    return value != null && !value.isEmpty();
  }

}
