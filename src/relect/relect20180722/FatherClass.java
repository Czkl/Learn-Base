package relect.relect20180722;

public abstract class FatherClass {

    private  String field1;

    private  String field2;

    private  String field3;

    private  String field4;

    public FatherClass() {
        System.out.println("father no parame ");
    }

    public FatherClass(String field2) {
        this.field2 = field2;
        System.out.println("father have one parame");
    }


    @Override
    public String toString() {
        return "FatherClass{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                '}';
    }
}
