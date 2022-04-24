import java.util.HashMap;
import java.util.HashSet;

public class ConsoleController {
    public static void main(String[] args) {
        HashMap<Integer, HashSet<String>> states = StateGenerator.getStates();
    }
}
