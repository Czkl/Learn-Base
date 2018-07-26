package relect.relect20180722;

import java.lang.reflect.*;
import java.util.ArrayList;

public class BaseRelectUtils implements TestInterface {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // 获取父类
//        BaseRelectUtils baseRelectUtils = new BaseRelectUtils();

//        System.out.println(baseRelectUtils.getClass().getName());
        // 输出 relect.relect20180722.BaseRelectUtils

//        System.out.println(baseRelectUtils.getClass().getSimpleName());
        // 输出 BaseRelectUtils


//        function1();
//        function2();
//        function3();
//        function4();
//        function5();
        funtion6();

    }



    /**
     *
     * 获取Class对象的3种方式
     */
    public static void function1()  throws ClassNotFoundException  {
        Class<?> clazz1 = null;
        Class<?> clazz2 = null;
        Class<?> clazz3 = null;


        // 最常用方式
        clazz1 = Class.forName("relect.relect20180722.BaseRelectUtils");

        // 通过实例获取
        clazz2 = new BaseRelectUtils().getClass();

        // 直接通过类获取
        clazz3 = BaseRelectUtils.class;

        System.out.println(clazz1.getName());
        System.out.println(clazz2.getName());
        System.out.println(clazz3.getName());

        // 输出
        //relect.relect20180722.BaseRelectUtils
        //relect.relect20180722.BaseRelectUtils
        //relect.relect20180722.BaseRelectUtils

    }

    /**
     *
     * 获取一个对象的父类与实现的接口
     */
    public static void function2(){

        SonClass sonClass = new SonClass();

        Class<?> clazz = sonClass.getClass();

        Class<?> supClazz = clazz.getSuperclass();
        Class<?>[] interfaceClazzs = clazz.getInterfaces(); // 可实现多个接口

        System.out.println(supClazz.getName());
        // 输出 relect.relect20180722.FatherClass

        for (Class tempClazz:interfaceClazzs
             ) {
            System.out.println(tempClazz.getName());
        }
        // 输出 relect.relect20180722.TestInterface

    }


    /**
     *
     * 获取某个类中的全部构造函数，通过反射机制实例化一个类的对象：
     */
    public static void function3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = Class.forName("relect.relect20180722.FatherClass");

        Constructor[] constructors = clazz.getConstructors();

        int count = 0;
        for (Constructor tempConstructor: constructors
             ) {

            Class<?>[] classes = tempConstructor.getParameterTypes();
            System.out.print("constructors["+count+++"] : ");
            for (int i = 0; i < classes.length; i++) {
                System.out.print(classes[i].getName()+" , ");
            }
            System.out.println();
        }

//        Object d = constructors[4].newInstance(); 抽象类不能被实例化
    }

    /**
     *
     * 这里我们声明并实例一个泛型为Integer的ArrayList
     * 通过反射获取方法add 调用传参String 类型 在泛型为Integer的ArrayList中存放一个String类型的对象。
     * 为什么？？？
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void function4() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java反射机制实例。");

        System.out.println(list.get(0));


        //下面是探讨为什么反射的时候add方法只能传入Object.class，因为我们拿到的add方法本质上，public boolean  add (java.lang.Object arg0
        Method method1[] = list.getClass().getMethods();//拿到所有方法
        for (int i = 0; i < method1.length; ++i) {
            Class<?> returnType = method1[i].getReturnType();
            Class<?> para[] = method1[i].getParameterTypes();
            int temp = method1[i].getModifiers();
            System.out.print(Modifier.toString(temp) + " ");//拿权限，他是包括了final、static的
            System.out.print(returnType.getName() + "  ");//返回类型
            System.out.print(method1[i].getName() + " ");//方法名字
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);//拿到参数名字
                if (j < para.length - 1) {
                    System.out.print(",");
                }
                else{
                    System.out.print(")");
                }
            }
            System.out.println();
        }
    }

    public static void function5(){

        int[] arr = {2, 3, 6, 4, 12, 3, 2};

        Class<?> clazz = arr.getClass().getComponentType();

        System.out.println("数组类型： " + clazz.getName());
        System.out.println("数组长度  " + Array.getLength(arr));
        System.out.println("数组的第一个元素: " + Array.get(arr, 0));
        Array.set(arr, 0, 100);
        System.out.println("修改之后数组第一个元素为： " + Array.get(arr, 0));

    }

    public static void funtion6(){

        int[] temp = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] newTemp = (int[]) arrayInc(temp, 15);
        print(newTemp);
        String[] atr = { "a", "b", "c" };
        String[] str1 = (String[]) arrayInc(atr, 8);
        print(str1);

    }

    // 修改数组大小
    public static Object arrayInc(Object obj, int len) {
        Class<?> arr = obj.getClass().getComponentType();

        //实例换一个新的数组
        Object newArr = Array.newInstance(arr, len);

        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArr, 0, co);

        return newArr;
    }
    // 打印
    public static void print(Object obj) {
        Class<?> c = obj.getClass();
        if (!c.isArray()) {
            return;
        }
        System.out.println("数组长度为： " + Array.getLength(obj));
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + " ");
        }
        System.out.println();
    }

}
