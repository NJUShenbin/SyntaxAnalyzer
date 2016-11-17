package syntax.analyzer.ppt;

import syntax.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by sbin on 2016/11/16.
 */
public class Ppt {

    List<PptState> stateList = new ArrayList<>();

    public void addState
            (Map<Character, Action> actionMap, Map<Character, Integer> gotoMap){
        stateList.add(new PptState(actionMap,gotoMap));
    }

    public Action getAction(Integer state,char a){
        return stateList.get(state).getAction(a);
    }

    public Integer getGoto(Integer state,char left){
        return stateList.get(state).gotoState(left);
    }

    public String getOperatorString(Stack<Integer> stateStack)
    {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<stateStack.size()-1;i++){
            PptState currentState = stateList.get(stateStack.get(i));
            Integer nextStateIndex = stateStack.get(i+1);
            builder.append(currentState.getCharToState(nextStateIndex));
        }
        return builder.toString();
    }

}
