package org.weixin4j.pay;

import java.util.Date;

/**
 * JsApiTicket业务
 *
 * @author qsyang
 * @version 1.0
 */
public class JsApiTicket {

    private final String ticket;
    private final long exprexpiredTime;

    public JsApiTicket(String ticket, int expires_in) {
        this.ticket = ticket;
        //获取当前时间
        Date now = new Date();
        //获取当前时间毫秒数
        long nowLong = now.getTime();
        //设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
        this.exprexpiredTime = nowLong + (expires_in * 1000);
    }

    /**
     * 判断用户凭证是否过期
     *
     * @return 过期返回 true,否则返回false
     */
    public boolean isExprexpired() {
        Date now = new Date();
        long nowLong = now.getTime();
        return nowLong > exprexpiredTime;
    }

    public String getTicket() {
        return ticket;
    }

}
