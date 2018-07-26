package relect.relect20180722.DynamicProxy;

public class RealProxy implements ProxyInterface{

    @Override
    public String say(String name, int age) {

        return name + " : " + age;
    }
}
