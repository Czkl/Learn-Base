package relect.relect20180722.DynamicProxy;

public class TestDynamicProxy {

    public static void main(String[] args) {

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();

        ProxyInterface dd  = (ProxyInterface) myInvocationHandler.setAgent(new RealProxy());

        System.out.println(dd.say("hehe", 13));

    }
}
