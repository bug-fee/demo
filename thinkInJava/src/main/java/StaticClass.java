import java.util.Arrays;
import java.util.Iterator;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181207
 * @Rewrite record：
 * 1、
 */
public class StaticClass {
    //不使用类的实例而使用类直接访问静态成员变量可以有效的提高编译器效率


    public static void main(String[] args) {
        for (Iterator<String> iterator = Arrays.asList(args).iterator(); ((Iterator) iterator).hasNext();) {
            System.out.println(iterator.next());
        }


    }
}
