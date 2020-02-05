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
public class PrimitiveType {
    /**
     * 原始类型的
     * <pre>
     *      PrimitiveType Default
     *      boolean false
     *      char ‘\u0000’(null)
     *      byte(byte) 0
     *      short(short) 0
     *      int 0
     *      long 0L
     *      float 0.0f
     *      double 0.0d
     * </pre>
     */
    public static void main(String[] args) {
        int x = 0;
        Integer X=x;
        long l=0L;
        Long L=l;
        short s=(short)1;
        Short S=s;
        char c = 'c';
        Character C=c;
        byte b=1;
        Byte B=b;
        double d=0.1;
        Double D=d;
        boolean bl=false;
        Boolean Bl=bl;
        float f=1.1f;
        Float F=f;
        //X引用x时只是将引用的地址传递到了X 中，当x变化时X依然引用原地址，也就是说X并不会变化
        System.out.println(x);
        x=1;
        System.out.println(X);

    }
}
