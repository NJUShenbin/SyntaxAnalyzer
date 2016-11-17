package syntax.action;

/**
 * Created by sbin on 2016/11/16.
 */
public abstract class Action {

    protected int num;

    public Action(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()){
            return false;
        }

        if(((Action)obj).getNum()!=getNum()){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode()+num;
    }
}
