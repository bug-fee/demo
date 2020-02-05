import javax.lang.model.type.PrimitiveType;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date 20181126
 * @Rewrite record：
 * 1、
 */
public class Documentation2 {
    private static int property;

    //此行注释所带的<pre>标签可以保留文本的格式保证排版时不被格式化代码所重置
    /**
     * <pre>
     *     System.out.println(new Date());
     * </pre>
     */
    public static void main(String[] args) {
        System.out.println(Documentation2.property);
    }
}
