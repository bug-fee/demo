package com.wondersgroup.swaggerdemo.service;

import com.wondersgroup.swaggerdemo.dto.Person;
import java.util.List;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record： 1、
 */
public interface PersonService {

  List getAllPersons();

  Person getPersonById(int id);

  void deletePerson(int id);

  Person createPerson(Person person);
}
