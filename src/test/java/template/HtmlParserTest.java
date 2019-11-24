package template;

import static com.daimler.utils.Environment.DEFAULT_ENVIRONMENT;
import static org.junit.Assert.assertEquals;
import static template.TestConst.RESOURCE_NAME;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.junit.Test;

import com.daimler.utils.TemplateEngine;

public class HtmlParserTest {

  @Test
  public void test() throws IOException {
    assertEquals(
        TemplateEngine.parse(
            Jsoup.parse(new File(getClass().getClassLoader().getResource(RESOURCE_NAME).getFile()),
                StandardCharsets.UTF_8.name()),
            DEFAULT_ENVIRONMENT.getExpressions(), DEFAULT_ENVIRONMENT.getTags(), DEFAULT_ENVIRONMENT.getAttributes())
            .toString().replaceAll("\n", "").trim(),
        "<!doctype html><html> <head>   </head> <body>  <div>    <title>111111 Smart</title>   </div>     <h1 title=\"Smart\">Smart</h1>    <div data-loop-model=\"car.models\">   Model: model  </div>    </body></html>");
  }
}
