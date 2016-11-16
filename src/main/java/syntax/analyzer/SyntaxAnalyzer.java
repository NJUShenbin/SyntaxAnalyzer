package syntax.analyzer;

import syntax.action.*;
import syntax.action.Error;
import syntax.analyzer.ppt.Ppt;
import syntax.analyzer.ppt.PptReader;
import syntax.analyzer.production.ProductionReader;
import syntax.analyzer.production.Productions;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by sbin on 2016/11/14.
 */
public class SyntaxAnalyzer {

    String pptPath = "ppt.xls";
    String inputPath = "input.txt";
    String productionPath = "production.txt";

    PptReader pptReader = new PptReader();
    ProductionReader productionReader = new ProductionReader();
    InputReader inputReader = new InputReader();


    public void analyze(){
        ActionExecutor actionExecutor = new ActionExecutor();
        Stack<Integer> stateStack = new Stack<>();
        stateStack.push(0);

        Productions productions = productionReader.read(productionPath);
        Ppt ppt = pptReader.read(pptPath);
        Stack<Character> inputStack = inputReader.read(inputPath);

        while (!inputStack.isEmpty()){

            Action action = ppt.getAction(stateStack.peek(),inputStack.peek());

            if(action instanceof Shift){
                actionExecutor.shift(inputStack,stateStack,action);
            }else if(action instanceof Reduce){
                actionExecutor.reduce(ppt,productions,inputStack,stateStack,action);
            }else if(action instanceof Accept){
                actionExecutor.accept(productions);
                break;
            }else if(action instanceof Error){
                actionExecutor.error();
                break;
            }

        }


    }

}
