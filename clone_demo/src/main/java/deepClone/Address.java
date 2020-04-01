package deepClone;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: deepClone
 * @Author: keer
 * @CreateTime: 2020-04-01 10:38
 * @Description:
 */
public class Address implements Cloneable{
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public Object clone() {
        Address address = null;
        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }
}


