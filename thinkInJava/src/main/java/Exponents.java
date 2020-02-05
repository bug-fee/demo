import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

/**
 * 指数描述符
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20190103
 * @Rewrite record：
 * 1、
 */
public class Exponents {

    public static void main(String[] args) {
        // Uppercase and lowercase ‘e’ are the same:
        float expFloat = 1.39e-43f;
        expFloat = 1.39E-43f;
        System.out.println(expFloat);
        double expDouble = 47e47d; // ‘d’ is optional
        double expDouble2 = 47e47; // Automatically double
        System.out.println(expDouble);

        //指定让指数返回为float类型，编译器默认将指数计算返回为double类型，如果指数后面不加“f”，编译器将报错。
        float f4 = 1e-43f; // 10 to the power


        // 1.39 e-43f 在Java里表示1.39 x (10的-43次方)。"f"可选，表示为float类型
    }
}
