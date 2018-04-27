package indi.welljun.web.formBean;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.util.HashMap;
import java.util.Map;

public class RegisterForm {
    private String uname;
    private String password;
    private String confirm;
    private String email;
    private String phone;
    private Map errors = new HashMap();

    public Map getErrors() {
        return errors;
    }
    public void setErrors(Map errors) {
        this.errors = errors;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public String getConfirm() {
        return confirm;
    }
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    public boolean validate() {
        boolean isOk = true;
        if(this.uname == null || this.uname.trim().equals("")) {
            isOk = false;
            errors.put("uname", "用户名不能为空！！！");
        } else {
            if(!(this.uname.length()>2)&&!(this.uname.length()<9)) {
                isOk = false;
                errors.put("uname", "用户名必须是3~8位！！！");
            }
        }
        if(this.password == null || this.password.trim().equals("")) {
            isOk = false;
            errors.put("password", "密码不能为空！！！");
        } else {
            if(!this.password.matches("\\d{3,10}")) {
                isOk = false;
                errors.put("password", "密码必须是3~10位数字！！！");
            }
        }
        if(this.confirm == null || this.confirm.trim().equals("")) {
            isOk = false;
            errors.put("confirm", "确认密码不能为空！！！");
        } else {
            if(!this.password.equals(this.confirm)) {
                isOk = false;
                errors.put("confirm", "两次密码要一致！！！");
            }
        }
        if(this.email == null || this.email.trim().equals("")) {
            isOk = false;
            errors.put("email", "邮箱不能为空！！！");
        } else {
            if(!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱格式不对！！！");
            }
        }
        if(this.phone == null || this.phone.trim().equals("")) {
            isOk = false;
            errors.put("phone", "手机号码不能为空！！！");
        } else{
            if(!this.phone.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
                isOk = false;
                errors.put("phone", "手机号码格式不对！！！");
            }
        }
        return isOk;
    }
}
