package design_mode.singleton20180725;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton {

    //单例模式

    private static  volatile Singleton instance = null; // 单例对象

    private Singleton(){} // 私有构造函数

    //静态工厂方法
    public static  Singleton getInstance(){
        if (instance == null) {  //双重检测机制
            synchronized (Singleton.class) { //同步锁
                if (instance == null) { //双重检测机制
                    instance = new Singleton();
                }
            }
        }
        return  instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //利用反射打破单例

        //获得构造器
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        //设置为可访问
        constructor.setAccessible(true);

        //构造两个不同的对象
        Singleton singleton1 = constructor.newInstance();
        Singleton singleton2 = constructor.newInstance();

        //验证是否是不同对象
        System.out.println(singleton1.equals(singleton2));

    }
}
