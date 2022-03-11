import java.util.ArrayList;
import java.util.List;

public class CareTaker {
   private static List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(){
        return mementoList.get(mementoList.size() - 2);
    }

    public Memento specificGet(int index){
        return mementoList.get(index);
    }

   public int stateCount(){
        return mementoList.size();
   }
}