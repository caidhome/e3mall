/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-12-02 08:36:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mainmenu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<div class=\"mainNav\">\r\n");
      out.write("  <div class=\"navmenu\">\r\n");
      out.write("    <div class=\"categories\" id=\"public_cate\">\r\n");
      out.write("      <div class=\"dt\"><a name=\"sfbest_Uhead_Uhead_menu_all\" class=\"trackref topall\" href=\"http://www.e3mall.cn/html/web/categorys.html\">精选商品分类</a></div>\r\n");
      out.write("      <div id=\"allSort\" class=\"dd\">\r\n");
      out.write("    <div id=\"booksort\">\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!----百度统计代码开始------->\r\n");
      out.write("\r\n");
      out.write("<!----百度统计代码结束-------></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!----menufloat------->\r\n");
      out.write("    <div class=\"menu1\">\r\n");
      out.write("      <ul>\r\n");
      out.write("            <li><a name=\"sfbest_Uhead_Uhead_nav_nav1\" class=\"trackref\" href=\"http://www.e3mall.cn\">首页</a></li>\r\n");
      out.write("            \t\t\t\t<li id=\"cat1\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav2\" href=\"http://www.e3mall.cn/fresh/\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">优选生鲜</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"cat2\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav3\" href=\"http://www.e3mall.cn/html/activity/1449221429.html#trackref=sfbest_Uhead_Uhead_nav_nav10\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">全球美食</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"cat3\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav4\" href=\"http://www.e3mall.cn/html/activity/1449559102.html#trackref=sfbest_Uhead_Uhead_nav_nav5\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">优选厨房</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"cat4\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav5\" href=\"http://www.e3mall.cn/html/activity/1454404627.html\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">新品尝鲜</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"cat5\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav6\" href=\"http://www.e3mall.cn/ht\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">优选国际</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"cat6\">\r\n");
      out.write("\t\t\t\t\t<a name=\"sfbest_Uhead_Uhead_nav_nav7\" href=\"http://www.e3mall.cn/qiye/\" class=\"trackref \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\">企业专区</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t              <!--\r\n");
      out.write("                  <li class=\"minisite1\"></li>\r\n");
      out.write("\t\t  <li class=\"minisite\"><a name=\"sfbest_Uhead_Uhead_nav_nav6\" class=\"trackref \" href=\"http://www.e3mall.cn/wine/\">红酒廊</a></li>\r\n");
      out.write("\t\t  <li class=\"minisite\"><a name=\"sfbest_Uhead_Uhead_nav_nav7\" class=\"trackref \" href=\"http://www.e3mall.cn/taste/\">寰宇美食</a></li>\r\n");
      out.write("               -->\r\n");
      out.write("      </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <span class=\"clear\"></span>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
