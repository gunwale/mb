package com.daimler.utils;

import groovy.lang.GroovyShell;

/**
 * @author CK_Te
 *
 */
public class GroovyRunner {

  static GroovyShell groovyShell = new GroovyShell();

  /**
   * Run groovy script
   * 
   * @param groovy
   * @return
   */
  public static Object run(String groovy) {
    try {
      return groovyShell.evaluate(groovy);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return groovy;
  }

  /**
   * Set variable for groovy runner
   * 
   * @param name
   * @param value
   */
  public static void setVariable(String name, Object value) {
    groovyShell.setVariable(name, value);
  }
}
