package com.tt.traffic.common.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List readExcel(String excelFileName) throws BiffException, IOException{

        //创建一个list 用来存储读取的内容
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;

        //创建输入流
        InputStream stream = new FileInputStream(excelFileName);

        //获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);

        list = readExcel(rwb, 0, null, 0, null);
        stream.close();
        rwb.close();
        //返回值集合
        return list;
    }

    /**
     *
     * @param excelFileName
     * @param startCol 起始列，从0开始
     * @param colNum
     * @param startRow 起始行，从1开始
     * @param rowNum
     * @return
     * @throws jxl.read.biff.BiffException
     * @throws IOException
     */
    public static List readExcel(String excelFileName,Integer startCol,Integer colNum,Integer startRow,Integer rowNum) throws BiffException, IOException{

        //创建一个list 用来存储读取的内容
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;

        //创建输入流
        InputStream stream = new FileInputStream(excelFileName);

        //获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);

        list = readExcel(rwb, startCol, colNum, startRow, rowNum);
        stream.close();
        rwb.close();
        //返回值集合
        return list;
    }

    public static List readExcel(Workbook workbook,Integer startCol,Integer colNum,Integer startRow,Integer rowNum){

        //创建一个list 用来存储读取的内容
        List list = new ArrayList();

        Cell cell = null;

        //获取文件的指定工作表 默认的第一个
        Sheet sheet = workbook.getSheet(0);

        if(rowNum == null){
            rowNum = sheet.getRows();
        }
        //行数
        for(int i=startRow; i<rowNum; i++){

            //创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];
            if(colNum == null){
                colNum = sheet.getColumns();
            }
            //列数
            for(int j=startCol; j<colNum; j++){

                //获取第i行，第j列的值
                cell = sheet.getCell(j,i);
                str[j] = cell.getContents();

            }
            //把刚获取的列存入list
            list.add(str);
        }
        //返回值集合
        return list;
    }
}