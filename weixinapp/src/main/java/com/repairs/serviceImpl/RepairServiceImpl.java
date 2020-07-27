package com.repairs.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairs.bo.Repairbo;
import com.repairs.bo.Userbo;
import com.repairs.dao.RepairDao;
import com.repairs.dao.UserDao;
import com.repairs.service.RepairService;
import com.repairs.service.UserService;
import com.repairs.utils.CommonsResult;

@Service
public class RepairServiceImpl implements RepairService {
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;

	@Override
	public String selectNews(String openId) {
		Userbo userbo = new Userbo();
		userbo.setOpenId(openId);
		List<Userbo> list = userDao.selectUser(userbo);
		userbo = list.get(0);
		Repairbo repairbo = new Repairbo();
		repairbo.setUser(userbo);
		repairbo.setOffset(0);
		repairbo.setLimit(5);
		repairbo.setOrder("desc");
		List<Repairbo> repairbos = repairDao.selectRepair(repairbo);
		String json = JSONArray.fromObject(repairbos).toString();
		return json;
	}

	@Override
	public String selectNewsDetails(Repairbo repair) {
		List<Repairbo> repairbos = repairDao.selectRepair(repair);
		String json = JSONArray.fromObject(repairbos).toString();
		return json;
	}

	@Override
	/**
	 * 根据搜索框输入的类型进行模糊查询
	 */
	public String repairResult(String openId, Integer flage, String textvalue,
			Integer offset, Integer showtype) {
		String json = "{\"infor\":\"false\"}";
		Repairbo repairbo = new Repairbo();
		Userbo user = new Userbo();
		user.setOpenId(openId);
		Userbo userbo = new Userbo();
		List<Userbo> userlist = userDao.selectUser(user);
		if (userlist.size() != 0) {
			userbo = userlist.get(0);
		}
		if (showtype != null && showtype == 3) {
			repairbo.setState(null);
		} else {
			repairbo.setState(showtype);
		}
		List<Repairbo> listrepairbos;
		if(flage==null){
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				System.out.println(jsonArray.toString());
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		if (flage == 1) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setFacilityName(textvalue);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}

		}
		if (flage == 2) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setSeriano(textvalue);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		if (flage == 3) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setFaultTitle(textvalue);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		return null;
	}

	@Override
	@Transactional
	public String insertRepair(Repairbo repairbo, String openId) {
		if (openId != null) {
			Userbo userbo = new Userbo();
			userbo.setOpenId(openId);
			List<Userbo> userbos = userDao.selectUser(userbo);
			if (userbos.size() != 0) {
				userbo = userbos.get(0);
				repairbo.setUser(userbo);
				if (repairbo.getFloorName() == null
						|| repairbo.getFloorName().equals("")) {

					repairbo.setFloorName(userbo.getFloorName());
				}
				if (repairbo.getWorkTell() == null
						|| repairbo.getWorkTell().equals("")) {

					repairbo.setWorkTell(userbo.getWorkTell());
				}
				if (repairbo.getRoomName() == null
						|| repairbo.getRoomName().equals("")) {

					repairbo.setRoomName(userbo.getRoomName());
				}
				if (repairbo.getSubDeptName() == null
						|| repairbo.getSubDeptName().equals("")) {

					repairbo.setSubDeptName(userbo.getSubDeptName());
				}
				if (repairbo.getCampus() == null
						|| repairbo.getCampus().equals("")) {

					repairbo.setCampus(userbo.getCampus());
				}
				if (repairbo.getMobileTell() == null
						|| repairbo.getMobileTell().equals("")) {

					repairbo.setMobileTell(userbo.getTell());
				}
			}
		}
		repairbo.setLookup(0);
		repairbo.setAdminLookup(0);
		repairbo.setServiceLookup(0);
		repairbo.setState(0);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		repairbo.setSubTime(time);
		repairDao.insertRepair1(repairbo);
		System.out.println(repairbo.getId());
		repairbo.setSeriano("ML0000" +repairbo.getId());
		int i = repairDao.updateRepair(repairbo);
		if (i > 0) {
			String str =userService.callAdmin(repairbo.getId());
			if(str=="success"){
				return "success";
			}
		}
		return "false";
	}

	@Override
	public CommonsResult insertRepairNoticeAdminBo(Repairbo repairbo,
			String openId) {
	
		return null;
	}
	
	/**
	 * 根据选择的类型展示相应的数据
	 */
	public String initRepair(String openId, int showtype, int offset) {
		String json = "{\"infor\":\"false\"}";
		Userbo user = new Userbo();
		user.setOpenId(openId);
		user = userDao.selectUser(user).get(0);
		Repairbo repairbo = new Repairbo();
		user.setOpenId(openId);
		Userbo userbo = new Userbo();
		List<Userbo> userlist = userDao.selectUser(user);
		if (userlist.size() != 0) {
			userbo = userlist.get(0);
		}
		List<Repairbo> listrepairbos;
		repairbo.setUser(user);
		if (showtype == 3) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}

		}
		if (showtype == 0) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setState(0);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		if (showtype == 1) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setState(1);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		if (showtype == 2) {
			userbo.setId(userbo.getId());
			repairbo.setUser(userbo);
			repairbo.setState(2);
			repairbo.setOffset(offset);
			repairbo.setLimit(5);
			listrepairbos = repairDao.selectRepair(repairbo);
			if (listrepairbos.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(listrepairbos);
				return jsonArray.toString();
			} else {
				return json.toString();
			}
		}
		return null;
	}

	@Override
	public CommonsResult insertRepairByPC(Repairbo repairbo) {
		// TODO Auto-generated method stub
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(now);
		repairbo.setSubTime(time);
		boolean message = false;
		int i = repairDao.insertRepair(repairbo);
		if (i > 0) {
			repairbo.setSeriano("ML0000"+repairbo.getId());
			repairDao.updateRepair(repairbo);
			message = true;
		}
		return new CommonsResult(message);
	}

	@Override
	public CommonsResult selectRepairPage(Repairbo repair) {
		// TODO Auto-generated method stub
		List<Repairbo> repairs = repairDao.selectRepair(repair);
		int size = repairDao.selectRepairAll(repair).size();
		if (size > 0) {
			int total = size % repair.getLimit() == 0 ? size
					/ repair.getLimit() : size / repair.getLimit() + 1;
			;
			return new CommonsResult(true, "", repairs, total);
		} else {
			return new CommonsResult(false, "无数据");
		}
	}

	@Override
	public List<Repairbo> selectRepairAll(Repairbo repair) {
		// TODO Auto-generated method stub

		List<Repairbo> selectRepairAll = repairDao.selectRepairAll(repair);
		if (selectRepairAll.size() > 0) {
			return selectRepairAll;
		} else {
			return null;
		}

	}

	@Override
	public CommonsResult deleteRepair(int id) {
		// TODO Auto-generated method stub
		int i = repairDao.deleteRepair(id);
		if (i > 0) {
			return new CommonsResult(true);
		} else {
			return new CommonsResult(false);
		}

	}

	@Override
	public CommonsResult updateRepair(Repairbo repair) {
		CommonsResult result = null;
		try {
			int i = repairDao.updateRepair(repair);
			if (i == 1) {
				result = new CommonsResult(true, "修改成功");
			} else {
				result = new CommonsResult(false, "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new CommonsResult(false, "修改失败");
		}
		return result;
	}

	@Override
	public Repairbo select(Integer id) {
		// TODO Auto-generated method stub
		return repairDao.selectRepairById(id);
	}


	@Override
	public HSSFWorkbook selectRepairByState(Integer expTime) {
		// TODO Auto-generated method stub
		List<Repairbo> repairbos=null;
		Repairbo repair = new Repairbo();
		if(expTime==1){
			repairbos=repairDao.selectRepair(repair);
		}else if(expTime==2){
			repairbos=repairDao.selectRepair(repair);
		}else if(expTime==3){
			repairbos=repairDao.selectRepair(repair);
		}
		String[] excelHeader = {"报修单号","报修校区", "报修人", "报修部门", "是否解决","维修人","维修人电话","维修费用"};     
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("报修记录");  
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
        for (int i = 0; i < excelHeader.length; i++) {   
        	row.setHeightInPoints(40);
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);  
            cell.setCellStyle(style);    
            sheet.setColumnWidth(i, 2500);  
            sheet.setColumnWidth(0, 3766);
        } 
        
        for(int i = 0; i < repairbos.size(); i++){
        	row = sheet.createRow(i + 1);   
            row.setHeightInPoints(20);
            Repairbo repairbo=repairbos.get(i);
            row.setRowStyle(style);
            row.createCell(0).setCellValue(repairbo.getSeriano());
            row.createCell(1).setCellValue(repairbo.getCampus().getName());
            if(repairbo.getUser()!=null){
                row.createCell(2).setCellValue(repairbo.getUser().getName());
            }else{
            	row.createCell(2).setCellValue(repairbo.getRepairName());
            }
            row.createCell(3).setCellValue(repairbo.getSubDeptName());
            row.createCell(4).setCellValue("已完成");
            if(repairbo.getServiceman()!=null){
            	row.createCell(5).setCellValue(repairbo.getServiceman().getName());
            	row.createCell(6).setCellValue(repairbo.getServiceman().getTell());
            }else{
            	row.createCell(5).setCellValue(repairbo.getAdminbo().getName());
            	row.createCell(6).setCellValue(repairbo.getAdminbo().getTell());
            }
            row.createCell(7).setCellValue(repairbo.getCharge());
        }
        return wb;
	}



	@Override
	public String stat() {
		List<Map> map=repairDao.stat();
		if (map!=null&&map.size()!=0) {
			return JSONArray.fromObject(map).toString();
		}else{
			return null;
		}
	}

}
