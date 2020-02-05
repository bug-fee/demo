import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import static net.mindview.util.Print.print;

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
public class WrapperClass {

    public static void main(String[] args) {

        int i1 = 0x2f; // Hexadecimal (lowercase)
        print("i1: " + Integer.toBinaryString(i1));
        int i2 = 0X2F; // Hexadecimal (uppercase)
        print("i2: " + Integer.toBinaryString(i2));
        int i3 = 0177; // Octal (leading zero)
        print("i3: " + Integer.toBinaryString(i3));
        char c = 0xffff; // max char hex value
        print("c: " + Integer.toBinaryString(c));
        byte b = 0x7f; // max byte hex value
        print("b: " + Integer.toBinaryString(b));
        short s = 0x7fff; // max short hex value
        print("s: " + Integer.toBinaryString(s));
        long n1 = 200L; // long suffix
        long n2 = 200l; // long suffix (but can be confusing)
        long n3 = 200;
        float f1 = 1;
        float f2 = 1F; // float suffix
        float f3 = 1f; // float suffix
        double d1 = 1d; // double suffix
        double d2 = 1D; // double suffix
        // (Hex and Octal also work with long)


        //字符包装类型
        char x = 'x';
        Character character = new Character(x);
        System.out.println(
                character
        );
        //JKD 5 以后支持包装类型自动解包自动装包
        character = 'X';
        System.out.println(character);

        //自动解包
        x = character;
        System.out.println(x);

        //char 的默认值为 ‘\u0000’ (null)
        char[] chars = new char[1];
        System.out.println(chars[0]);


        //包装类型Integer和BigDecimal虽然精度高，但是内部调用过多，效率稍低。

        Integer i = new Integer("10000");
        //输出integer的二进制字符串
        System.out.println(Integer.toBinaryString(i));
        //输出integer的十六进制字符串
        System.out.println(Integer.toHexString(i));
        //输出initeger的八进制字符串
        System.out.println(Integer.toOctalString(i));
        //输出integer的最大值
        System.out.println(Integer.MAX_VALUE);
        //Integer最大值为2147483647，此处使用二进制字符串转换为十进制int类型将抛出异常NumberFormatException.forInputString
//        System.out.println(Integer.parseInt("10011100010000"));

    }
}
