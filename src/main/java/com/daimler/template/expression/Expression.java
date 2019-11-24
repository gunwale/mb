package com.daimler.template.expression;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author CK_Te
 *
 */
public interface Expression {

  /**
   * Like wrapper
   * 
   */
  public static final Function<String, String> like = (regex) -> {
    return new StringBuilder("(?s).*").append(regex).append("*").toString();
  };

  /**
   * Check if expression
   * 
   * @return
   */
  default Predicate<String> isExpression() {
    return p -> p != null && !p.isEmpty();
  }

  /**
   * Process expression
   * 
   * @param value
   * @return
   */
  public String process(String value);

}
