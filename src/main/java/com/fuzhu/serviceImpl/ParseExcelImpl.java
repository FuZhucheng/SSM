package com.fuzhu.serviceImpl;

import com.fuzhu.entity.GoodDetails;
import com.fuzhu.service.ParseExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 符柱成 on 2017/5/27.
 */
@Service
public class ParseExcelImpl implements ParseExcel {
    @Override
    public List<GoodDetails> parseExcel(File xlsFile, String filename) {
        boolean isE2007 = false;    //判断是否是excel2007格式
        if (filename.endsWith("xlsx"))
            isE2007 = true;
        System.out.println(isE2007);

        List<GoodDetails> goodDetailsList = new ArrayList<>();
        try {
            InputStream input = new FileInputStream(filename);  //建立输入流
            Workbook wb = null;
            //根据文件格式(2003或者2007)来初始化
            if (isE2007)
                wb = new XSSFWorkbook(input);
            else
                wb = new HSSFWorkbook(input);

            System.out.println(wb);
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单

            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
            while (rows.hasNext()) {

                Row row = rows.next();  //获得行数据
                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器

                GoodDetails goodDetails = null;
                if (row.getRowNum() > 0) {
                    goodDetails = new GoodDetails();  //每行一条记录嘛
                    System.out.println("第几行   " + row.getRowNum());
                }
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    int i = cell.getColumnIndex();
                    System.out.println("Cell #" + cell.getColumnIndex());

                    if (goodDetails != null) {
                        switch (i) {
                            case 0:
                                goodDetails.setGoodName(String.valueOf(cell.getStringCellValue()));
                                break;
                            case 1:
                                goodDetails.setGoodBrand(String.valueOf(cell.getStringCellValue()));
                                break;
                            case 2:
                                goodDetails.setStoreAdd(String.valueOf(cell.getStringCellValue()));
                                break;
                            case 3:
                                goodDetails.setSellerCredit(String.valueOf(cell.getStringCellValue()));
                                break;
                            case 4:
                                goodDetails.setGoodPrice(String.valueOf(cell.getNumericCellValue()));
                                break;
                            default:
                                System.out.println("unsuported sell type");
                                break;
                        }
                    }

//下面这段注释代码，给大家认识下每一列对应的数据类型：

//                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
//                        case HSSFCell.CELL_TYPE_NUMERIC: //读取数字
//                            //先看是否是日期格式
//                            if(HSSFDateUtil.isCellDateFormatted(cell)){
//                                //读取日期格式
//                                System.out.print("一   "+cell.getDateCellValue()+" ");
//                            }else{
//                                //读取数字
//                                System.out.print("一   "+cell.getNumericCellValue()+" ");
//                                if (goodDetails!=null){
//                                    goodDetails.setGoodPrice(String.valueOf(cell.getNumericCellValue()));
//                                }
//
//                            }
//                            break;
//                        case HSSFCell.CELL_TYPE_STRING://读取文本对象
//                            System.out.println("二   "+cell.getStringCellValue());
//
//                            break;
//                        case HSSFCell.CELL_TYPE_BOOLEAN:  //得到Boolean对象的方法
//                            System.out.println("三   "+cell.getBooleanCellValue());
//                            break;
//                        case HSSFCell.CELL_TYPE_FORMULA://得到公式
//                            System.out.println("四   "+cell.getCellFormula());
//                            break;
//                        default:
//                            System.out.println("unsuported sell type");
//                            break;
//                    }
                }
                goodDetailsList.add(goodDetails);//拿到我们导入的list后就是往数据库批量插入了，这个就太简单了，我就不写了。
            }
            System.out.println(goodDetailsList.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goodDetailsList;
    }
}
