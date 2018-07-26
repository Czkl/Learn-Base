package relect.relect20180722.StaticProxy;

import relect.relect20180722.StaticProxy.AbstractPermission;

public class RealPermission implements AbstractPermission {


    @Override
    public void modifyUserinfo() {
        System.out.println("修改用户信息");
    }

    @Override
    public void viewNote() {

    }

    @Override
    public void publichNote() {
        System.out.println("发布新帖");
    }

    @Override
    public void modifyNOte() {
        System.out.println("修改发布内容！");
    }

    @Override
    public void setLevel(int level) {

    }
}
