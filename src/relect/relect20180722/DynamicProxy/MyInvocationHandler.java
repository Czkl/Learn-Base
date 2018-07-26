package relect.relect20180722.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    private Object obj = null;

    //目的是把要被代理的对象set进来
    public Object setAgent(Object obj) {
        this.obj = obj;
        //Proxy这个类的作用就是用来动态创建一个代理对象的类,newProxyInstance这个方法的作用就是得到一个动态的代理对象。一会详细讲讲其源码。

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object temp = method.invoke(this.obj,args);
        return  temp;
    }
}
