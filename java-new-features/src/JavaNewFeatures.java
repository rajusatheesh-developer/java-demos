import java.util.ArrayList;
import java.util.List;

public class JavaNewFeatures
{

    public static void main(String[] args) {


        ArrayList<Integer> integerArrayList=new ArrayList<>();
        integerArrayList.add(1);
        integerArrayList.add(10);

        // Immutable collection - UnsupportedOperationException on modify
        List<Integer> copyOfList=List.copyOf(integerArrayList);

    }
}
