package com.daimler.template.expression;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.daimler.utils.GroovyRunner;

public class GroovyExpression implements Expression {

  public final static String NAME = "GroovyExpression";

  public final static String EXPRESSION_REGEX = "\\$\\{[^\\}]*\\}";
  public final static String EXPRESSION_DOLLAR_SIGN = "\\$";
  public final static String EXPRESSION_DOLLAR_SIGN_ESCAPED = "\\\\\\$";
  public final static String EXPRESSION_OPEN_CURLY_BRACES = "\\{";
  public final static String EXPRESSION_OPEN_CURLY_BRACES_ESCAPED = "\\\\\\{";
  public final static String EXPRESSION_CLOSE_CURLY_BRACES = "\\}";
  public final static String EXPRESSION_CLOSE_CURLY_BRACES_ESCAPED = "\\\\\\}";

  @Override
  public Predicate<String> isExpression() {
    return Expression.super.isExpression().and(p -> p.matches(like.apply(EXPRESSION_REGEX)));
  }

  @Override
  public String process(String value) {
    return Optional.of(value).filter(isExpression()).map(html -> {
      html = escapeHtml4(html);
      Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(html);
      if (matcher.find()) {
        for (int i = 0; i <= matcher.groupCount(); i++) {
          String expression = matcher.group(i);
          html = html.replaceFirst(escapeExpression(expression),
              (String) GroovyRunner.run(removeExpression(expression)));
        }
      }
      return html;
    }).orElse(value);
  }

  private String escapeExpression(String value) {
    return value.replaceAll(EXPRESSION_DOLLAR_SIGN, EXPRESSION_DOLLAR_SIGN_ESCAPED)
        .replaceAll(EXPRESSION_OPEN_CURLY_BRACES, EXPRESSION_OPEN_CURLY_BRACES_ESCAPED)
        .replaceAll(EXPRESSION_CLOSE_CURLY_BRACES, EXPRESSION_CLOSE_CURLY_BRACES_ESCAPED);
  }

  private String removeExpression(String value) {
    return value.substring(2, value.length() - 1);
  }

  @Override
  public String toString() {
    return NAME;
  }

}
