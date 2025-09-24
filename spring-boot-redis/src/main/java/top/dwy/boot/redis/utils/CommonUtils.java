package top.dwy.boot.redis.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alani
 */
public class CommonUtils {
    /**
    * @param phone
    * @return
     */
    public static boolean checkPhone(String phone){
        if (phone == null || phone.length() != 11){
            return false;
        }
        String regex = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    /**
    * @return int
     */

    public static  int generateCode(){
        return ThreadLocalRandom.current().nextInt(1000, 9999);
    }
}
