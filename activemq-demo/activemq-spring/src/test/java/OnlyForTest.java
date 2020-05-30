import com.wondersgroup.demo.activemq.spring.SimpleProducer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record：
 * 1、
 */
public class OnlyForTest {
    @Test
    public void testAmqProducer() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-activemq.xml");
        SimpleProducer simpleProducer = (SimpleProducer) context.getBean("producer");
        try {
            simpleProducer.sendMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
