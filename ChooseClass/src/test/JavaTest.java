package test;

public class JavaTest {
    public static void main(String[] args) {
        String str = "coccahello";
        String[] re =  str.split("cocca");
        for (int i = 0; i <re.length ; i++) {
            System.out.println(re[i]);
        }
    }
}
