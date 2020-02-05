/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181208
 * @Rewrite record：
 * 1、
 */
public class Equivalence {

    public static void main(String[] args) {
        Integer i1 = new Integer(1);
        Integer i21 = new Integer(2);
        //应使用equals做对比,此处使用的地址引用做的对比
        System.out.println(i1 == i21);//false
        System.out.println(1>1.1);
    }
}
