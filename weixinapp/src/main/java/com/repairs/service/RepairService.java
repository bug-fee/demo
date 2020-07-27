package com.repairs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.repairs.bo.Repairbo;
import com.repairs.utils.CommonsResult;

public interface RepairService {
	/**
	 * 查询出用户最新的维修消息记录
	 * **/
	String selectNews(String openId);
	/**
	 * 查询出维修记录的详情
	 * **/
	String selectNewsDetails(Repairbo repair);
	 public String repairResult(String openId,Integer flage,String textvalue,Integer offset,Integer showtype) ;
     public String initRepair(String openid,int showtype,int offset);
    /* public String initRepair(int showtype,int page);
     public String initRepair(int showtype,int page, HttpSession session);*/
	String insertRepair(Repairbo repairbo, String openId);
	
	public CommonsResult insertRepairNoticeAdminBo(Repairbo repairbo,String openId);
	
	public List<Repairbo> selectRepairAll(Repairbo repair);
	
	public CommonsResult insertRepairByPC(Repairbo repairbo);
	
	public CommonsResult selectRepairPage(Repairbo repair);
	
	public CommonsResult deleteRepair(int id);
	//修改repair
	CommonsResult updateRepair(Repairbo repair);
	
	
	public Repairbo select(Integer id);

	
	public HSSFWorkbook selectRepairByState(Integer expTime);

	//统计
	String stat();

}
