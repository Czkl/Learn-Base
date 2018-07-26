package relect.relect20180722.StaticProxy;

public class TestStaticProxy {

    public static void main(String[] args) {

        // 静态代理
        AbstractPermission abstractPermission = new PermissionProxy();

        abstractPermission.modifyNOte();
        abstractPermission.modifyUserinfo();
        abstractPermission.viewNote();
        abstractPermission.setLevel(1);
        abstractPermission.publichNote();
        abstractPermission.modifyNOte();
        abstractPermission.modifyUserinfo();

    }
}
