package com.lanou.hrd.interceptor;

import com.lanou.hrd.domain.Staff;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Created by dllo on 17/10/30.
 */
public class ActionInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Staff staff = (Staff) ActionContext.getContext().getApplication().get("staffLogin");

        if (!staff.getPost().getPostId().equals("2c9090e85f56b56f015f56b6720b0004") &&
                !staff.getPost().getPostId().equals("2c9090e85f56b56f015f56b6a4090005")){
            return "No permission";
        }
        return actionInvocation.invoke();
    }
}
