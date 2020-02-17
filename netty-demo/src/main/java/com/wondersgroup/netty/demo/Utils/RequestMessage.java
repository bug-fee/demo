package com.wondersgroup.netty.demo.Utils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * attention:
 * 通过netty传输的对象必须实现Serializable接口，否则在调用的过程中不会报错，仅仅是得不到服务端/客户端返回的数据
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class RequestMessage implements Serializable {
    private static final long serialVersionUID = 6486949384772087709L;

    private Long id;
    private String message;
    private byte[] attachment;

    public RequestMessage() {
        super();
    }

    public RequestMessage(Long id, String message, byte[] attachment) {
        this.id = id;
        this.message = message;
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
