package com.daimler.utils;

import java.util.HashMap;
import java.util.Map;

import com.daimler.template.attribute.Attribute;
import com.daimler.template.attribute.DataIfAttribute;
import com.daimler.template.expression.Expression;
import com.daimler.template.expression.GroovyExpression;
import com.daimler.template.tag.GroovyScriptTag;
import com.daimler.template.tag.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author CK_Te
 *
 */
@Data
@Builder
@AllArgsConstructor
public class Environment {

  public static final Environment DEFAULT_ENVIRONMENT = Environment.defaultValues();

  private Map<String, Expression> expressions;
  private Map<String, Tag> tags;
  private Map<String, Attribute> attributes;

  private static Environment defaultValues() {
    Environment environment = Environment.builder().attributes(new HashMap<String, Attribute>())
        .expressions(new HashMap<String, Expression>()).tags(new HashMap<String, Tag>()).build();

    environment.getExpressions().put(GroovyExpression.NAME, new GroovyExpression());
    environment.getTags().put(GroovyScriptTag.NAME, new GroovyScriptTag());
    environment.getAttributes().put(DataIfAttribute.NAME, new DataIfAttribute());

    return environment;
  }

}
