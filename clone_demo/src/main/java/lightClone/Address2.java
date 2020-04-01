package lightClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: lightClone
 * @Author: keer
 * @CreateTime: 2020-04-01 14:58
 * @Description: 实现Cloneable接口
 */
public class Address2 implements Cloneable{
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        Address2 address = null;
        try {
            address = (Address2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }

}
