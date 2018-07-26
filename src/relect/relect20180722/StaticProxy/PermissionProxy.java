package relect.relect20180722.StaticProxy;

public class PermissionProxy implements AbstractPermission {

    private RealPermission realPermission = new RealPermission();
    private int level = 0;

    @Override
    public void modifyUserinfo() {
        if (0 == level) {
            System.out.println("对不起。你没有该权限");
        } else {
            realPermission.modifyUserinfo();
        }
    }

    @Override
    public void viewNote() {
        System.out.println("查看帖子");
    }

    @Override
    public void publichNote() {
        if (0 == level) {
            System.out.println("对不起。你没有该权限");
        } else {
            realPermission.publichNote();
        }
    }

    @Override
    public void modifyNOte() {
        if (0 == level) {
            System.out.println("对不起。你没有该权限");
        } else {
            realPermission.modifyNOte();
        }
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
}
