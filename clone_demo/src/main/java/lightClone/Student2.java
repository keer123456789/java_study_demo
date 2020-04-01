package lightClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: lightClone
 * @Author: keer
 * @CreateTime: 2020-04-01 14:59
 * @Description: 属性实现Cloneable 未显示调用属性的clone（浅拷贝）
 */
public class Student2 implements Cloneable{
    private int number;

    private Address2 addr;

    public Address2 getAddr() {
        return addr;
    }

    public void setAddr(Address2 addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() {
        Student2 stu = null;
        try {
            stu = (Student2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    public static void main(String[] args) {
        Address2 addr = new Address2();
        addr.setAdd("杭州市");
        Student2 stu1 = new Student2();
        stu1.setNumber(123);
        stu1.setAddr(addr);
        Student2 stu2 = (Student2) stu1.clone();
        System.out.println("克隆后，未做修改前，学生的信息：");
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        System.out.println("stu1 和stu2是否相等："+stu1.equals(stu2));
        System.out.println("stu1 和stu2的地址是否相等："+stu1.getAddr().equals(stu2.getAddr()));
        stu1.setNumber(3333);
        stu1.getAddr().setAdd("中国");
        System.out.println("克隆后，修改stu1后，学生的信息：");
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        System.out.println("stu1 和stu2是否相等："+stu1.equals(stu2));
        System.out.println("stu1 和stu2的地址是否相等："+stu1.getAddr().equals(stu2.getAddr()));

    }
}
