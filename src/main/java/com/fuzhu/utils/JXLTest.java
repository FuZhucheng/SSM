package com.fuzhu.utils;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 符柱成 on 2017/8/23.
 */
public class JXLTest {

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

        //（一）路径的拼接（模板文件路径）
        //模板文件流
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        basePath = StringUtils.substringBeforeLast(basePath, "/");
        basePath = StringUtils.substringBeforeLast(basePath, "/");
        basePath = StringUtils.substringBeforeLast(basePath, "/");
        basePath = basePath+"/src/main/webapp/source/";
        File templateFile = new File(basePath + "commonexport.xls");
        //（二）导出的文件流
        String resultFilePath = basePath + "导出的文件名.xls";
        File resultFile = new File(resultFilePath);
        //（三）excel文件对象
        Workbook wb = null;//先初始化一个EXCEL文件
        WorkbookSettings settings = new WorkbookSettings();//以下两行先不要理会，后面会详细解释，这个是关于Linux与wins的区别，关于单元格最大的字符限制
        settings.setWriteAccess(null);
        WritableWorkbook wwb = null;

        try {
            wb = Workbook.getWorkbook(templateFile);
            wwb = Workbook.createWorkbook(resultFile, wb, settings);
            WritableSheet sheet = wwb.createSheet("Sheet1", 0);//excel的工作表格
            //（四）标题栏
            for (int i = 0; i < headList.size(); i++) {//这个是我们导出的模板excel的列数
                Label la = new Label(i, 0, wb.getSheet(0).getCell(i, 0).getContents());
				sheet.addCell(la);
            }
            List<Map<String, String>> dataList=new ArrayList<>();
            sheet.setRowView(0, 300);//设置第一行高度
            //（五）数据准备--假数据
            for (int t=0;t<1000;t++){
                Map<String, String> temp = new HashMap<>();
                temp.put("groupid", String.valueOf(1+t));
                temp.put("productcode", "abc"+String.valueOf(1+t));
                dataList.add(temp);
            }
            //（六）导进excel的数据
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, String> map = dataList.get(i);
                Label C1 = new Label(0, i + 1, map.get("groupid"));//第一个参数指示：第一列
                Label C3 = new Label(2, i + 1, map.get("productcode"));//第一个参数指示：第三列
                sheet.addCell(C1);
                sheet.addCell(C3);
            }
            /*
            （七）导出
            */
            wwb.write();
            wwb.close();
            wb.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
