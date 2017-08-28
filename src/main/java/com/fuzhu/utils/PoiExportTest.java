package com.fuzhu.utils;

import com.fuzhu.entity.Student;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 符柱成 on 2017/8/23.
 */
public class PoiExportTest {
    public static void main(String [] args){
        writeInExcel();
    }
    public static void writeInExcel() {
        //列的标题，把他写进代码，是为了方便管理业务的增删
        List<String> headList = new ArrayList<>();
        headList.add("专线类型");
        headList.add("业务类型");
        headList.add("工单标题");
        headList.add("工单号");
        headList.add("ESOP单号");
        headList.add("来源渠道");

        //（一）构建excel文件所需要的对象
        //创建工作薄
        HSSFWorkbook wkb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wkb.createSheet("工单信息表");

        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short)15);
        // 生成一个样式
        HSSFCellStyle style = wkb.createCellStyle();
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row=sheet.createRow(0);//这里其实是创建索引而已，所定某一行
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        /*
            （二）写入标题栏
         */
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        //给表头第一行一次创建单元格
        HSSFCell cell = null;
        for (int i = 0; i < headList.size(); i++) {//这个是我们导出的模板excel的列数
            cell = row.createCell(i);
            cell.setCellValue(headList.get(i));
            cell.setCellStyle(style);
        }
        /*
            （三）准备数据
         */
        //添加一些数据，这里先写死，大家可以换成自己的集合数据
        List<Map<String, String>> dataList=new ArrayList<>();
        for (int t=0;t<1000;t++){
            Map<String, String> temp = new HashMap<>();
            temp.put("groupid", String.valueOf(1+t));
            temp.put("productcode", "abc"+String.valueOf(1+t));
            dataList.add(temp);
        }
        /*
            （四）向单元格里填充数据
         */
        for (short i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 1);//为什么这里+1？？因为要留给第一行给标题栏嘛
            row.createCell(0).setCellValue(dataList.get(i).get("groupid"));
            row.createCell(1).setCellValue(dataList.get(i).get("productcode"));
        }
        /*
            （五）导出
         */
        try {
            //默认导出到E盘下
            FileOutputStream out = new FileOutputStream("E://工单信息表.xls");
            wkb.write(out);
            out.close();
            System.out.println("导出成功!");
        } catch (FileNotFoundException e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("导出失败!");
        }
    }
}
