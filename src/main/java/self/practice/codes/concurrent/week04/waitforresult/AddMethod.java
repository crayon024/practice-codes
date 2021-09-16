package self.practice.codes.concurrent.week04.waitforresult;

public class AddMethod {

    public static int add() {
        int result = 0;
        for (int i = 0; i < 1000000; i++) {
            result++;
        }
        return result;
    }
}
