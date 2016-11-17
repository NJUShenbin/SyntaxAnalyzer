package syntax.analyzer.production;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbin on 2016/11/16.
 */
public class Productions {

    List<Production> productionList = new ArrayList<>();

    public void addProduction(String productionString){
        String[] split = productionString.split("->");
        productionList.add(new Production(split[0].trim(),split[1].trim()));
    }

    public Production getProduction(int index){
        return productionList.get(index);
    }

}
