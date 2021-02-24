package com.wondersgroup.swaggerdemo.service.impl;

import com.wondersgroup.swaggerdemo.dto.Person;
import com.wondersgroup.swaggerdemo.service.PersonService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record： 1、
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Override
  public List getAllPersons() {
    return null;
  }

  @Override
  public Person getPersonById(int id) {
    return null;
  }

  @Override
  public void deletePerson(int id) {

  }

  @Override
  public Person createPerson(Person person) {
    return null;
  }
}
