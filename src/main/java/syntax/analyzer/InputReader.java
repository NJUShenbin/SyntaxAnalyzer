package syntax.analyzer;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Stream;

/**
 * Created by sbin on 2016/11/16.
 */
public class InputReader {

    public Queue<Character> read(String path){

        Queue<Character> inputStack = new LinkedList<>();

        try {
            String input = Files.readFirstLine(new File(path), Charsets.UTF_8);
            for(char c : input.toCharArray()){
                inputStack.add(c);
            }
            inputStack.add('$');
            return inputStack;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("cannot read input file");
    }

}
