package com.lanou.util;

import com.lanou.hrd.domain.Staff;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dllo on 17/11/2.
 */


@WebFilter(filterName = "Filter", urlPatterns = "/*")
public class inputFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staffLogin");
        if (staff == null){
            request.getRequestDispatcher("/pages/login.jsp").forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
