package syntax.action;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2016/11/16.
 */
public class ActionTest {

    @Test
    public void testEquals(){
        Action a1 = new Error();
        Action a2 = new Error();
        assert a1.equals(a2);

        Action a3 = new Reduce(5);
        Action a4 = new Reduce(5);
        assert a3.equals(a4);
    }

    @Test
    public void testHash(){
//        BiMap<Integer,Action> map = HashBiMap.create();
//        map.put(1,new Error());
//        assert map.inverse().get(new Error())!=null;
//        assert map.inverse().get(new Reduce(-1))==null;

        Map<Action,Character> actionMap = new HashMap<>();
        actionMap.put(new Shift(6),'c');
        assert actionMap.get(new Shift(6))!=null;
    }

}