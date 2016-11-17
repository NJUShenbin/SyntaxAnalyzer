package syntax.analyzer;

import syntax.action.Action;
import syntax.analyzer.ppt.Ppt;
import syntax.analyzer.production.Production;
import syntax.analyzer.production.Productions;

import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by sbin on 2016/11/16.
 */
public class ActionExecutor {

    Stack<String> reduceLog = new Stack<>();
    Stack<Production> productionStack = new Stack<>();

    public ActionExecutor(Queue<Character> inputStack){
        StringBuilder builder = new StringBuilder();
        inputStack.forEach(builder::append);
        reduceLog.push("->"+builder.substring(0,builder.length()-1));
    }


    public void reduce
            (Ppt ppt, Productions productions,
             Queue<Character> inputStack, Stack<Integer> stateStack,Action action){
        Production production = productions.getProduction(action.getNum());

        for (char c : production.getRight().toCharArray()){
            stateStack.pop();
        }

        Integer currentState = stateStack.peek();
        stateStack.push(ppt.getGoto(currentState,production.getLeft()));

        reduceLog.push(getCurrentAnalyzeString(ppt,stateStack,inputStack));
        productionStack.push(production);
    }

    private String getCurrentAnalyzeString
            (Ppt ppt,Stack<Integer> stateStack,
             Queue<Character> inputStack){
        StringBuilder builder = new StringBuilder();

        inputStack.forEach(builder::append);
        String s = builder.substring(0,builder.length()-1);
        return "->"
                +ppt.getOperatorString(stateStack)+s;

    }

    public void shift(Queue<Character> inputStack, Stack<Integer> stateStack,
                      Action action){
        //输入字符串出栈
        inputStack.poll();

        //新状态进栈
        stateStack.push(action.getNum());
    }

    public void accept(Productions productions){
        Production firstProduction = productions.getProduction(0);
        reduceLog.push(firstProduction.getLeft()+"'");

        System.out.println("推导过程:");
        while (!reduceLog.isEmpty()){
            System.out.println(reduceLog.pop());
        }

        System.out.println("\n规约序列:");
        productionStack.push(productions.getProduction(0));
        while (!productionStack.isEmpty()){
            System.out.println(productionStack.pop());
        }
    }

    public void error(){
        System.out.println("error");
        while (!reduceLog.isEmpty()){
            System.out.println(reduceLog.pop());
        }
    }

}
