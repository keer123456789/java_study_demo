package deepClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: deepClone
 * @Author: keer
 * @CreateTime: 2020-04-01 10:17
 * @Description: 深克隆（不带对象属性）
 */
public class User implements Cloneable {
    private String num;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        User user1=new User();
        user1.setNum("123456");
        User user2= user1.clone();
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
