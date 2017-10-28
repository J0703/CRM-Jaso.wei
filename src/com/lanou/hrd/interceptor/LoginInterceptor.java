package com.lanou.hrd.interceptor;

import com.lanou.hrd.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dllo on 17/10/27.
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Staff staff = (Staff) ActionContext.getContext().getSession().get("staffLogin");
//        if (staff == null) {
//            return "login";
//        }else if (){
//            return "error";
//        }

        return actionInvocation.invoke();
    }
}
