package syntax.analyzer;

import syntax.action.Action;
import syntax.analyzer.ppt.Ppt;
import syntax.analyzer.production.Production;
import syntax.analyzer.production.Productions;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by sbin on 2016/11/16.
 */
public class ActionExecutor {

    Stack<String> reduceLog = new Stack<>();

    public ActionExecutor(){
    }


    public void reduce
            (Ppt ppt, Productions productions,
             Stack<Character> inputStack, Stack<Integer> stateStack,Action action){

    }

    public void shift(Stack<Character> inputStack,Stack<Integer> stateStack,
                      Action action){

    }

    public void accept(Productions productions){
        String firstProduction = productions.getProduction(0).toString();
        reduceLog.push(firstProduction+"\t\t"+firstProduction);

        reduceLog.forEach(System.out::println);

    }

    public void error(){

    }

}
