package com.repairs.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.repairs.bo.WeixinTag;


/**
 *标签
 */
public class TagUtil {
	/**
	 * 获取公众号已创建的标签
	 * @param accessToken 调用接口凭证
	 */
	public static List<WeixinTag> getTags(String accessToken){
		List<WeixinTag> weixinTagList=null;
		//拼接请求地址
		String requestUrl="https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
		requestUrl=requestUrl.replace("ACCESS_TOKEN", accessToken);
		//查询
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"GET",null);
		if(null!=jsonObject){
			try{
				weixinTagList=JSONArray.toList(jsonObject.getJSONArray("tags"),WeixinTag.class);
			}catch(JSONException e){
				weixinTagList=null;
				int errorCode=jsonObject.getInt("errcode");
				String errorMsg=jsonObject.getString("errmsg");
				System.out.println("查询失败");
			}
		}
		return weixinTagList;
	}
	/**
	 * 创建标签
	 * @param accessToken 接口访问凭证
	 * @param tagName 标签名称
	 */
	public static WeixinTag createTag(String accessToken,String tagName){
		WeixinTag weixinTag=null;
		//拼接请求地址
		String requestUrl="https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
		requestUrl=requestUrl.replace("ACCESS_TOKEN", accessToken);
		//需要提交的JSON数据
		String jsonData="{\"tag\":{\"name\":\"%s\"}}";
		//创建分组
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData,tagName));
		
		if(null!=jsonObject){
			try{
				weixinTag=new WeixinTag();
				weixinTag.setId(jsonObject.getJSONObject("tag").getInt("id"));
				weixinTag.setName(jsonObject.getJSONObject("tag").getString("name"));
			}catch(JSONException e){
				weixinTag=null;
				int errorCode=jsonObject.getInt("errcode");
				String errorMsg=jsonObject.getString("errmsg");
				System.out.println("创建标签失败");
			}
		}
		return weixinTag;
	}
	/**
	 * 编辑标签
	 * @param accessToken 接口访问凭证
	 * @return true|false
	 */
	public static boolean updateTag(String accessToken,int tagId,String tagName){
		boolean result=false;
		//拼接请求地址
		String requestUrl="https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
		requestUrl=requestUrl.replace("ACCESS_TOKEN", accessToken);
		//需要提交的JSON数据
		String jsonData="{\"tag\":{\"id\":%d,\"name\":\"%s\"}}";
		//修改分组名
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData,tagId,tagName));
		
		if(null!=jsonObject){
			int errorCode=jsonObject.getInt("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode){
				result=true;
				System.out.println("修改标签名成功");
			}else{
				System.out.println("修改标签名失败");
			}
		}
		return result;
	}
	/**
	 * 为用户打标签
	 * @param accessToken 接口访问凭证
	 * @param openId 用户标识
	 * @param tagId 标签id
	 * @return true|false
	 */
	public static boolean addTag(String accessToken,String openId,int tagId){
		boolean result=false;
		//拼接请求地址
		String requestUrl="https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
		requestUrl=requestUrl.replace("ACCESS_TOKEN", accessToken);
		//需要提交的JSON数据
		String jsonData="{\"openid_list\":[\"%s\"],\"tagid\":%d}";
		//
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData,openId,tagId));
		
		if(null!=jsonObject){
			int errorCode=jsonObject.getInt("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if(0==errorCode){
				result=true;
				System.out.println("成功");
			}else{
				System.out.println("失败:"+errorCode);
			}
		}
		return result;
	}
	/**
	 * 为用户取消标签
	 */
	public static boolean deleteTag(String accessToken,String openId,int tagId){
		//拼接请求地址
		String requestUrl="https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
		requestUrl=requestUrl.replace("ACCESS_TOKEN", accessToken);
		//需要提交的JSON数据
		String jsonData="{\"openid_list\":[\"%s\"],\"tagid\":%d}";
		//
		JSONObject jsonObject=CommonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData,openId,tagId));
		
		if(null!=jsonObject){
			int errorCode=jsonObject.getInt("errcode");
			if(0==errorCode){
				System.out.println("成功");
				return true;
			}else{
				System.out.println("失败");
				return false;
			}
		}
		return false;
	}
	
}
