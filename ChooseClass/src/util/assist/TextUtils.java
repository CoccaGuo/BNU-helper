package util.assist;

import java.util.List;

public class TextUtils {
    public static String join(String str, List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s:
             list) {
           stringBuilder.append(s);
           stringBuilder.append(str);
        }
        stringBuilder.delete(stringBuilder.lastIndexOf(str),stringBuilder.length());
        return stringBuilder.toString();
    }

}
