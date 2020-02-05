/**
 * 断路现象
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181212
 * @Rewrite record：
 * 1、
 */

import static net.mindview.util.Print.print;

public class ShortCircuit {
    static boolean test1(int val) {
        print("test1(" + val + ")");
        print("result: " + (val < 1));
        return val < 1;
    }

    static boolean test2(int val) {
        print("test2(" + val + ")");
        print("result: " + (val < 2));
        return val < 2;
    }

    static boolean test3(int val) {
        print("test3(" + val + ")");
        print("result: " + (val < 3));
        return val < 3;
    }

    /**
     * && 运算符 从左到右计算其boolean值，有表达式不满足true时，右边开始不再执行
     * <pre>
     *  test1(0)
     *  result: true
     *  test2(2)
     *  result: false
     *  expression is false
     * </pre>
     *
     * @param args
     * @return 返回值
     * @author Wang si rui
     * @version 1.0
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 1、 <br>
     */
    public static void main(String[] args) {
        boolean b = test1(0) && test2(2) && test3(2);
        print("expression is " + b);
    }

}
