package lightClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-01 09:30
 * @Description: 浅拷贝例子（不带属性对象）
 */
public class User {
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        User user1=new User();
        user1.setNum("123456");
        User user2= user1;
        User user3=new User();
        user3.setNum("123456");
        System.out.println("克隆后，未做修改前，两用户的信息：");
        System.out.println("User1.num："+user1.getNum());
        System.out.println("User2.num："+user2.getNum());
        System.out.println("User1和User2是否相等："+user1.equals(user2));
        user2.setNum("7890");
        System.out.println("克隆后，修改User2.num=："+user2.getNum()+",两用户信息如下：");
        System.out.println("User1.num："+user1.getNum());
        System.out.println("User2.num："+user2.getNum());
        System.out.println("User1和User2是否相等："+user1.equals(user2));
    }
}
