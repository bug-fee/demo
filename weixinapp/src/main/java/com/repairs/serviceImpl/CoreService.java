package com.repairs.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.repairs.message.po.Article;
import com.repairs.message.resp.NewsMessage;
import com.repairs.message.resp.TextMessage;
import com.repairs.weixinutil.MessageUtil;
public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		String respXml=null;
		String respContent="";
		try {
			Map<String, String> requestMap =MessageUtil.xmlToMap(request);
			String fromUserName= requestMap.get("FromUserName");
			String toUserName=requestMap.get("ToUserName");
			String msgType=requestMap.get("MsgType");
			//回复文本消息
			TextMessage text = new TextMessage();
			text.setFromUserName(toUserName);
			text.setToUserName(fromUserName);
			text.setMsgType(MessageUtil.MESSAGE_TEXT);
			text.setCreateTime(new Date().getTime());
			//回复图文消息
			NewsMessage news=new NewsMessage();
			news.setFromUserName(toUserName);
			news.setToUserName(fromUserName);
			news.setMsgType(MessageUtil.MESSAGE_NEWS);
			news.setCreateTime(new Date().getTime());
			news.setArticleCount(1);
			List<Article> articles=new ArrayList<Article>();
			
			
			if (msgType.equals(MessageUtil.MESSAGE_TEXT)) {
				respContent="您发送的是文本消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_IMAGE)){
				respContent="您发送的是图片消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_VOICE)){
				respContent="您发送的是语音消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_VIDEO)){
				respContent="您发送的是视频消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_LOCATION)){
				respContent="您发送的是位置消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_LINK)){
				respContent="您发送的是链接消息";
			}else if(msgType.equals(MessageUtil.MESSAGE_EVNET)){
				String eventType=requestMap.get("Event");
				if (eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)) {
					respContent="欢迎关注河南牧业经济学院报修平台。";
				}else if(eventType.equals(MessageUtil.MESSAGE_UNSUBSCRIBE)){
					
				}else if(eventType.equals(MessageUtil.MESSAGE_CLICK)){
					String eventKey=requestMap.get("EventKey");
					if (eventKey.equals("V11_ABS")) {
						respContent="00000000";
					}
				}
			}
			
			text.setContent(respContent);
			if (text.getContent().equals("")) {
				news.setArticles(articles);
				respXml=MessageUtil.newsMessage(news);			
			}else{
				
				respXml=MessageUtil.textMessageToXml(text);
			}
		} catch (Exception e) {
			
		}
		return respXml;
	}

}
