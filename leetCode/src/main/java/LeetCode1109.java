/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-13 08:46
 * @Description:
 */
public class LeetCode1109 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        int[] dif = new int[n];
        for (int[] booking : bookings) {
            dif[booking[0] - 1] += booking[2];
            if(booking[1]!=n){
                dif[booking[1]]-=booking[3];
            }
        }
        answer[0]=dif[0];
        for(int i=1;i<n;i++){
            answer[i]=answer[i-1]+dif[i];
        }
        return answer;
    }
}
