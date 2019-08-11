package test;



public class PyTest {
    public static void main(String[] args) throws Exception {
        String exe = "python";
        String command = "toast.py";
        String title = "hello";
        String context = "text";
        String[] cmdArr = new String[] {exe, command, title, context, "10"};
        Runtime.getRuntime().exec(cmdArr);

    }
}
