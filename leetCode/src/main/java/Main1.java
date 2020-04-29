import fr.cryptohash.Digest;
import fr.cryptohash.Keccak256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-14 15:50
 * @Description:
 */
public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String str;
        List listR = new ArrayList<List>();
        while ((str = rd.readLine()) != null) {
            List<Integer> list = new ArrayList<Integer>();
            str = str.trim();
            for (int i = 0; i < str.length(); i++) {
                list.add(Integer.valueOf(str.charAt(i)));
            }
            listR.add(list);
        }
        int count = listR.size(); //服务器的个数
        int sum = 0;//结果，通知几个服务器
        int[] a = new int[count]; //标记服务器通知与否；

        for (int i = 0; i < count; i++) {
            int k = i;
            for (int j = k; j < count; j++) {
                int z=k;
                List<Integer> list = (List) listR.get(k);
                if (list.get(j) == 1) {
                    a[j] = 1;
                    z = j;
                } else {

                    List<Integer> temp = (List) listR.get(z);
                    if (list.get(j) == 1) {
                        a[j] = 1;
                    }
                }
            }
            int zeor=cu(a);
            if(zeor==-1){

            }else {
                i=zeor;
            }
            sum++;
        }
        System.out.println(sum);

    }


    public static int cu(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {

                return i;
            }
        }
        return -1;
    }


    public static byte[] computeSha3Hash(byte[] buffer) {
        Digest sha3 = new Keccak256();
        return sha3.digest(buffer);
    }
}