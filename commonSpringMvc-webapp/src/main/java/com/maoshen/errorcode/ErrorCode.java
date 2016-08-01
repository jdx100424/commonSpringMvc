/**   
 * @Description:(用一句话描述该类做什么)
 * @author Daxian.jiang
 * @Email  Daxian.jiang@vipshop.com
 * @Date 2015年9月17日 上午10:18:43
 * @Version V1.0   
 */
package com.maoshen.errorcode;

public enum ErrorCode {
    LOGIN_USERNAME_PASSWORD_CHECK_FAIL(10001, "用户名或密码错误"),
    LOGIN_USERNAME_PASSWORD_FORMAT_FAIL(10002, "请填写正确的用户名或密码格式"),
    LOGIN_EXIST(10003, "已登录"),
    LOGIN_KAPTCHA_CHECK_FAIL(10004, "验证码不正确"),
    LOGIN_KAPTCHA_FORMAT_FAIL(10005, "请填写正确的验证码格式");
    private int    code; //错误代码

    private String msg; //错误消息

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}