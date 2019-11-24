package template;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.daimler.Car;
import com.daimler.utils.GroovyRunner;

public class GroovyTest {

  @Test
  public void test() {
    Object car = GroovyRunner.run("import com.daimler.Car \n car = Car.lookup(\"1\")");

    assertEquals(car, Car.lookup("1"));
    assertEquals(GroovyRunner.run("car.brand"), Car.lookup("1").getBrand());
  }
}
