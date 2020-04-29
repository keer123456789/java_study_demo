import fr.cryptohash.Digest;
import fr.cryptohash.Keccak256;

/**
 * @BelongsProject: java_study_demo
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-16 10:26
 * @Description:
 */
public class DigestDemo {
    public static byte[] computeSha3Hash(byte[] buffer) {
        Digest sha3 = new Keccak256();
        return sha3.digest(buffer);
    }
}
