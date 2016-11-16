package syntax.analyzer.production;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by sbin on 2016/11/16.
 */
public class ProductionReader {

    public Productions read(String path){

        List<String> productionStringList = null;
        Productions productions = new Productions();

        try {
            productionStringList = Files.readLines(new File(path), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String production:productionStringList){
            productions.addProduction(production);
        }

        return productions;
    }

}
