package deepClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: deepClone
 * @Author: keer
 * @CreateTime: 2020-04-01 10:38
 * @Description:
 */
public class Student implements Cloneable {
    private int number;

    private Address addr;

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
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
        Student stu = null;
        try {
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // 对象属性设置
        stu.addr = (Address) addr.clone();

        return stu;
    }

    public static void main(String[] args) {
        Address addr = new Address();
        addr.setAdd("北京市");
        Student stu1 = new Student();
        stu1.setNumber(123);
        stu1.setAddr(addr);
        Student stu2 = (Student) stu1.clone();
        System.out.println("克隆后，未做修改前，学生的信息：");
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        System.out.println("stu1 和stu2是否相等：" + stu1.equals(stu2));
        System.out.println("stu1 和stu2的地址是否相等：" + stu1.getAddr().equals(stu2.getAddr()));
        stu1.setNumber(3333);
        stu1.getAddr().setAdd("中国");
        System.out.println("克隆后，修改stu1后，学生的信息：");
        System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
        System.out.println("stu1 和stu2是否相等：" + stu1.equals(stu2));
        System.out.println("stu1 和stu2的地址是否相等：" + stu1.getAddr().equals(stu2.getAddr()));
    }
}
