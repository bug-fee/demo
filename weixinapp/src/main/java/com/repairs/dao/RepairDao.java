package com.repairs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.repairs.bo.Repairbo;
public interface RepairDao {
	/**
	 * 查找维修记录
	 * @param repair
	 * @return
	 */
	public List<Repairbo> selectRepair(Repairbo repair);
	
	/**
	 * 修改维修记录
	 * @param repairbo
	 * @return
	 */
	int updateRepair(Repairbo repairbo);
	
	public int insertRepair1(Repairbo repairbo);
	
	
	public int insertRepair(Repairbo repairbo);
	
	
	public int deleteRepair(int id);
	
	public List<Repairbo> selectRepairAll(Repairbo repairbo);
	
	public Repairbo selectRepairById(Integer id);
	//统计
	public List<Map> stat();
	
	public List<Repairbo> selectRepairByState(Repairbo repairbo);
}
