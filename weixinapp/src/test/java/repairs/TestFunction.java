package repairs;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Campusbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.Userbo;
import com.repairs.dao.AdminDao;
import com.repairs.dao.ServicemanDao;
import com.repairs.dao.UserDao;
import com.repairs.service.CampusService;

public class TestFunction {
	
	/**
	 * 查找所有校区
	 */
	@Test
	public void testSelectAllCampusbo(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		CampusService campusService=ac.getBean("campusService", CampusService.class);
		List<Campusbo> list=campusService.selectAllCampus();
		if(list!=null&&list.size()>0){
			Iterator<Campusbo> it=list.iterator();
			while(it.hasNext()){
				Campusbo campus=it.next();
				System.out.println(campus.getName());
			}
		}
	}
	
	/**
	 * 查询维修人员
	 */
	@Test
	public void testSelectServiceman(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		ServicemanDao servicemandao=ac.getBean("servicemanDao", ServicemanDao.class);
		Servicemanbo serviceman=new Servicemanbo();
		Campusbo campus=new Campusbo();
		campus.setId(1);
		serviceman.setCampus(campus);
		serviceman.setOpenId("ovrtQ1NjUzQF7MThiUXY7Z5Mwwxg");
		List<Servicemanbo> list=servicemandao.selectServiceman(serviceman);
		if(list!=null&&list.size()>0){
			Iterator<Servicemanbo> it=list.iterator();
			while(it.hasNext()){
				Servicemanbo servicem=it.next();
				System.out.println(servicem.getName());
			}
		}
	}
	
	/**
	 * 查询管理员
	 */
	@Test
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDao adminDao=ac.getBean("adminDao", AdminDao.class);
		Adminbo admin=new Adminbo();
		admin.setUserName("yoory");
		admin.setPassword("yoory1221");
		List<Adminbo> result=adminDao.selectAdmin(admin);
		if(result!=null&&!result.isEmpty()){
			admin=result.get(0);
			System.out.println(admin.getName());
		}

	}
	
	/**
	 * 查询用户
	 */
	@Test
	public void testSelectUser(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao=ac.getBean("userDao", UserDao.class);
		Userbo user=new Userbo();
		Campusbo campus=new Campusbo();
		campus.setId(1);
		user.setCampus(campus);
		user.setOpenId("ovrtQ1Ged4zgrE_q5upOLxQ1sdKQ	");
		List<Userbo> result=userDao.selectUser(user);
		if(result!=null&&!result.isEmpty()){
			user=result.get(0);
			System.out.println(user.getTell());
		}
	}
	
	/**
	 * 插入管理员
	 */
	@Test
	public void testInsertAdmin(){
		try{
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDao adminDao=ac.getBean("adminDao", AdminDao.class);
		Adminbo admin=new Adminbo();
		admin.setName("王子文");
		admin.setOpenId("ovrtQ1Ged4zgrE_q5upOLxQ1sdKQ");
		admin.setUserName("wzw");
		admin.setPassword("1221");
		admin.setTell("12386835386");
		adminDao.insertAdmin(admin);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
