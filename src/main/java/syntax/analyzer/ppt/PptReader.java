package syntax.analyzer.ppt;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import syntax.action.Accept;
import syntax.action.Action;
import syntax.action.Reduce;
import syntax.action.Shift;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sbin on 2016/11/16.
 */
public class PptReader {

    public Ppt read(String path){

        Ppt ppt = new Ppt();

        HSSFSheet sheet = getSheet(path);
        int gotoIndex = getGotoIndex(sheet);
        HSSFRow inputCharRow = sheet.getRow(1);

        for(Row oneRow : sheet){
            if(oneRow.getCell(0)==null){
                continue;
            }
            if(oneRow.getCell(0).getCellType()!= Cell.CELL_TYPE_NUMERIC){
                continue;
            }

            Map<Character, Action> actionMap =
                    getActionMap(oneRow,inputCharRow,gotoIndex);

            Map<Character,Integer> gotoMap =
                    getGotoMap(oneRow,inputCharRow,gotoIndex);

            ppt.addState(actionMap,gotoMap);
        }

        return ppt;
    }

    private Map<Character, Action> getActionMap
            (Row row,Row inputCharRow,int gotoIndex){
        Map<Character,Action> actionMap = new HashMap<>();

        for(Cell cell:row){
            if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                continue;
            }
            if(cell.getColumnIndex()>=gotoIndex){
                break;
            }

            Action action = createAction(cell.getStringCellValue());

            char c = getGotoOrIndexKey(cell,inputCharRow);
            actionMap.put(c,action);
        }
        return actionMap;
    }

    private Map<Character, Integer> getGotoMap
            (Row row,Row inputCharRow,int gotoIndex){

        Map<Character, Integer> gotoMap = new HashMap<>();

        for(Cell cell:row){
            if(cell.getColumnIndex()<gotoIndex){
                continue;
            }

            int stateNum = (int)cell.getNumericCellValue();
            char c = getGotoOrIndexKey(cell,inputCharRow);
            gotoMap.put(c,stateNum);
        }
        return gotoMap;
    }

    private char getGotoOrIndexKey(Cell cell,Row inputCharRow){
        return inputCharRow
                .getCell(cell.getColumnIndex())
                .getStringCellValue()
                .charAt(0);
    }

    private Action createAction(String content){

        if(content.equals("accept")){
            return new Accept();
        }

        int num = Integer.valueOf(content.substring(1));

        if(content.startsWith("S")){
            return new Shift(num);
        }else if(content.startsWith("r")){
            return new Reduce(num);
        }

        throw new RuntimeException("invalid content:"+content);
    }


    private HSSFSheet getSheet(String path){
        try {
            FileInputStream file = new FileInputStream(new File(path));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            return sheet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getGotoIndex(HSSFSheet sheet){
        HSSFRow row = sheet.getRow(0);
        for(Cell cell:row){
            if(cell.getStringCellValue().equals("goto")){
                return cell.getColumnIndex();
            }
        }
        throw new RuntimeException("can not find goto section");
    }

}
