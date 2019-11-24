package com.daimler.filter;

import static com.daimler.utils.Environment.DEFAULT_ENVIRONMENT;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.jsoup.Jsoup;

import com.daimler.utils.GroovyRunner;
import com.daimler.utils.TemplateEngine;

public class TemplateFilter implements Filter {

  FilterConfig filterConfig = null;

  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    PrintWriter out = response.getWriter();

    CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) response);

    filterChain.doFilter(request, responseWrapper);

    GroovyRunner.setVariable("request", request);

    out.write(TemplateEngine.parse(Jsoup.parse(responseWrapper.toString()), DEFAULT_ENVIRONMENT.getExpressions(),
        DEFAULT_ENVIRONMENT.getTags(), DEFAULT_ENVIRONMENT.getAttributes()));
  }

  public static class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter output;

    public String toString() {
      return output.toString();
    }

    public CharResponseWrapper(HttpServletResponse response) {
      super(response);
      output = new CharArrayWriter();
    }

    public PrintWriter getWriter() {
      return new PrintWriter(output);
    }
  }

}
