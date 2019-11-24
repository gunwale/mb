package com.daimler.template.expression;

public class GroovyExpression implements Expression {

  public final static String EXPRESSION_REGEX = "\\${[^}]*}";

  @Override
  public boolean filter(String value) {
    return Expression.super.filter(value) && value.matches(EXPRESSION_REGEX);
  }

}
