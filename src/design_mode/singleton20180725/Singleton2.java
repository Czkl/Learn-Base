package design_mode.singleton20180725;

public class Singleton2 {

    private static class SingletonFactory{
        private  static final Singleton2 instance = new Singleton2();
    }

    private Singleton2(){}

    public static Singleton2 getInstance(){
        return SingletonFactory.instance;
    }
}
