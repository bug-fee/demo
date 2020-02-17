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
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = -3134586266164369031L;
    private Long id;
    private String message;
    private byte[] attachment;

    public ResponseMessage() {
        super();
    }

    public ResponseMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResponseMessage(Long id, String message, byte[] attachment) {
        this.id = id;
        this.message = message;
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
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

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
