package com.fuzhu.utils;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by 符柱成 on 2017/8/24.
 */
public class ExportMapExcel {
    public void exportExcel(String title, List<String> headersName, List<String> headersId,
                            List<Map<String, Object>> dtoList) {
        /*
               （一）表头--标题栏
         */
        Map<Integer, String> headersNameMap = new HashMap<>();
        int key = 0;
        for (int i = 0; i < headersName.size(); i++) {
            if (!headersName.get(i).equals(null)) {
                headersNameMap.put(key, headersName.get(i));
                key++;
            }
        }
        /*
                （二）字段---标题的字段
         */
        Map<Integer, String> titleFieldMap = new HashMap<>();
        int value = 0;
        for (int i = 0; i < headersId.size(); i++) {
            if (!headersId.get(i).equals(null)) {
                titleFieldMap.put(value, headersId.get(i));
                value++;
            }
        }
       /*
                （三）声明一个工作薄：包括构建工作簿、表格、样式
       */
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(title);
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        HSSFRow row = sheet.createRow(0);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell;
        Collection c = headersNameMap.values();//拿到表格所有标题的value的集合
        Iterator<String> headersNameIt = c.iterator();//表格标题的迭代器
        /*
                （四）导出数据：包括导出标题栏以及内容栏
        */
        //根据选择的字段生成表头--标题
        short size = 0;
        while (headersNameIt.hasNext()) {
            cell = row.createCell(size);
            cell.setCellValue(headersNameIt.next().toString());
            cell.setCellStyle(style);
            size++;
        }
        //表格一行的字段的集合，以便拿到迭代器
        Collection zdC = titleFieldMap.values();
        Iterator<Map<String, Object>> titleFieldIt = dtoList.iterator();//总记录的迭代器
        int zdRow = 1;//真正的数据记录的列序号
        while (titleFieldIt.hasNext()) {//记录的迭代器，遍历总记录
            Map<String, Object> mapTemp = titleFieldIt.next();//拿到一条记录
                row = sheet.createRow(zdRow);
                zdRow++;
                int zdCell = 0;
                Iterator<String> zdIt = zdC.iterator();//一条记录的字段的集合的迭代器
                while (zdIt.hasNext()) {
                    String tempField =zdIt.next();//字段的暂存
                    if (mapTemp.get(tempField) != null) {
                        row.createCell((short) zdCell).setCellValue(String.valueOf(mapTemp.get(tempField)));//写进excel对象
                        zdCell++;
                    }
                }
        }
        try {
            FileOutputStream exportXls = new FileOutputStream("E://工单信息表Map.xls");
            wb.write(exportXls);
            exportXls.close();
            System.out.println("导出成功!");
        } catch (FileNotFoundException e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        }
    }
    public static void main(String [] args) {

        List<String> listName = new ArrayList<>();
        listName.add("id");
        listName.add("名字");
        listName.add("性别");
        List<String> listId = new ArrayList<>();
        listId.add("id");
        listId.add("name");
        listId.add("sex");

        List<Map<String,Object>> listB = new ArrayList<>();
        for (int t=0;t<6;t++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",t);
            map.put("name","abc"+t);
            map.put("sex","男"+t);
            listB.add(map);
        }
        System.out.println("listB  : "+listB.toString());
        ExportMapExcel exportExcelUtil = new ExportMapExcel();
        exportExcelUtil.exportExcel("测试POI导出EXCEL文档",listName,listId,listB);

    }
}
