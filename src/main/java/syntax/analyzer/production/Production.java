package syntax.analyzer.production;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sbin on 2016/11/16.
 */
@Data
@AllArgsConstructor
public class Production {

    String left;
    String right;

    public char getLeft(){
        return left.charAt(0);
    }

    @Override
    public String toString() {
        return left+" -> "+right;
    }
}
