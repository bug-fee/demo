import java.util.Random;

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
public class MathOps {
    public static void main(String[] args) {
        //算数运算符
        int x = 9;
        System.out.println(x %= 3);
        /*
         * random构造函数有两种，无参和有参，
         * 当使用无参构造器时默认使用当前时间毫秒数作为种子生成随机数序列，例如：[1, 5, 6]
         * 当使用有参数的构造器时每次生成的随机数有固定顺序：例如第一次执行:[1,5,6];第二次执行:[1,5,6];
         */
        Random random = new Random(50);
        int i, j, k;
        //获取下一个0到100区间的整数
        j = random.nextInt(100) + 1;
        System.out.println(j);
        k = random.nextInt(100) + 1;
        System.out.println(k);
        i = j - k;
        System.out.println(i);


    }
}
