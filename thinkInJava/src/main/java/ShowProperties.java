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
public class ShowProperties {

    /**
     *
     * @author Wang si rui
     * @version 1.0
     * @param args
     * @return 返回值
     * @lastAuthor 最后修改人
     * @lastDate最后时间
     * @Rewrite record 修改记录：
     * 				1、 <br>
     */
    public static void main(String[] args) {
        //获取所有的的系统属性，并以打印的方式列出来
//        java.version
//          Java Runtime Environment version
//        java.vendor
//          Java Runtime Environment vendor
//        java.vendor.url
//          Java vendor URL
//        java.home
//          Java installation directory
//        java.vm.specification.version
//          Java Virtual Machine specification version
//        java.vm.specification.vendor
//          Java Virtual Machine specification vendor
//        java.vm.specification.name
//          Java Virtual Machine specification name
//        java.vm.version
//          Java Virtual Machine implementation version
//        java.vm.vendor
//          Java Virtual Machine implementation vendor
//        java.vm.name
//          Java Virtual Machine implementation name
//        java.specification.version
//          Java Runtime Environment specification version
//        java.specification.vendor
//          Java Runtime Environment specification vendor
//        java.specification.name
//          Java Runtime Environment specification name
//        java.class.version
//          Java class format version number
//        java.class.path
//          Java class path
//        java.library.path
//          List of paths to search when loading libraries
//        java.io.tmpdir
//          Default temp file path
//        java.compiler
//          Name of JIT compiler to use
//        java.ext.dirs
//          Path of extension directory or directories Deprecated. This property, and the mechanism which implements it, may be removed in a future release.
//        os.name
//          Operating system name
//        os.arch
//          Operating system architecture
//        os.version
//          Operating system version
//        file.separator
//          File separator ("/" on UNIX)
//        path.separator
//          Path separator (":" on UNIX)
//        line.separator
//          Line separator ("\n" on UNIX)
//        user.name
//          User's account name
//        user.home
//          User's home directory
//        user.dir
//          User's current working directory
        System.getProperties().list(System.out);

//        //获取当前计算机的用户名：Administrator
//        System.out.println(System.getProperty("user.name"));
//        //获取jar包所在路径 null
//        System.out.println(System.getProperty("java.libarary.path"));
//        //获取Java版本 1.8.0_77
//        System.out.println(System.getProperty("java.version"));
//        //获取操作系统版本 6.1
//        System.out.println(System.getProperty("os.version"));
//        //当前系统用户的根目录 C:\Users\Administrator
//        System.out.println(System.getProperty("user.home"));
//        //获取Java虚拟机的指定版本 1.8
//        System.out.println(System.getProperty("java.vm.specification.version"));

    }
}
