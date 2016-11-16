package syntax.analyzer.ppt;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import syntax.action.Action;
import syntax.action.Error;
import syntax.action.Shift;

import java.util.Map;

/**
 * Created by sbin on 2016/11/16.
 */
public class PptState {

    private BiMap<Character,Action> actionMap;
    private BiMap<Character,Integer> gotoMap;

    public PptState
            (Map<Character, Action> actionMap, Map<Character, Integer> gotoMap) {

        this.actionMap = HashBiMap.create(actionMap);
        this.gotoMap = HashBiMap.create(gotoMap);

    }

    public Action getAction(char c){
        return actionMap.getOrDefault(c,new Error());
    }

    public int gotoState(char c){
        return gotoMap.get(c);
    }

    public char getCharToState(int state){

        Shift shift = new Shift(state);
        Character findByAction = actionMap.inverse().get(shift);

        if(findByAction!=null){
            return findByAction;
        }else {
            return gotoMap.inverse().get(state);
        }

    }

}
