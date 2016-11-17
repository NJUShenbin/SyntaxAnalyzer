package syntax.action;

/**
 * Created by sbin on 2016/11/16.
 */
public class Reduce extends Action {

    public Reduce(int num) {
        super(num);
    }

    @Override
    public boolean equals(Object obj) {
        return this==obj;
    }
}
