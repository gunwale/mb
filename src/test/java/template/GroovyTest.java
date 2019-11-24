package template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.daimler.Car;

import groovy.lang.GroovyShell;

public class GroovyTest {

  @Test
  public void test() {
    GroovyShell shell = new GroovyShell();
    Object car = shell.evaluate("import com.daimler.Car \n car = Car.lookup(\"1\")");

    assertEquals(car, Car.lookup("1"));
  }
}
