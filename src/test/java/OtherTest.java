import java.util.Arrays;

public class OtherTest {

    public static void main(String[] args) {
        String s = "C:\\Program Files\\java\\jdk";

        System.out.println(s);

        String[] sa=s.split("\\\\");

        System.out.println(Arrays.toString(sa));
    }
}
