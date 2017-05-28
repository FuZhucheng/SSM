<%--
  Created by IntelliJ IDEA.
  User: 符柱成
  Date: 2017/5/25
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.fuzhu.entity.GoodDetails" %>
<%
    String titleName = "商品列表";

    response.addHeader("Content-disposition", "attachment;filename=" + (new String(titleName.getBytes("GBK"), "iso-8859-1")) + ".xls");
    List<String> headList=(List)request.getAttribute("headList");
    List<GoodDetails> dataList=(List)request.getAttribute("resultList");
%>

<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:o="urn:schemas-microsoft-com:office:office"
          xmlns:x="urn:schemas-microsoft-com:office:excel"
          xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
          xmlns:html="http://www.w3.org/TR/REC-html40">
    <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
        <Created>2006-09-13T11:21:51Z</Created>
        <LastSaved>2011-10-20T11:56:16Z</LastSaved>
        <Version>12.00</Version>
    </DocumentProperties>
    <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
        <RemovePersonalInformation/>
    </OfficeDocumentSettings>
    <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
        <WindowHeight>11640</WindowHeight>
        <WindowWidth>19200</WindowWidth>
        <WindowTopX>0</WindowTopX>
        <WindowTopY>90</WindowTopY>
        <ProtectStructure>False</ProtectStructure>
        <ProtectWindows>False</ProtectWindows>
    </ExcelWorkbook>
    <Styles>
        <Style ss:ID="Default" ss:Name="Normal">
            <Alignment ss:Vertical="Center"/>
            <Borders/>
            <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"/>
            <Interior/>
            <NumberFormat/>
            <Protection/>
        </Style>
        <Style ss:ID="s66" ss:Name="常规 2 2 2">
            <Font ss:FontName="宋体" x:CharSet="134" ss:Size="12"/>
        </Style>
        <Style ss:ID="s62">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
        </Style>
        <Style ss:ID="s67" ss:Parent="s66">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
            <Borders>
             <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            </Borders>
              <Font ss:FontName="宋体" x:CharSet="134" ss:Color="#FFFFFF" ss:Bold="1"/>
            <Interior ss:Color="#0066CC" ss:Pattern="Solid"/>
        </Style>
        <Style ss:ID="s68">
            <Alignment ss:Horizontal="Center" ss:Vertical="Center" ss:WrapText="1"/>
            <Borders>
             <Border ss:Position="Bottom" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Left" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Right" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            <Border ss:Position="Top" ss:LineStyle="Continuous" ss:Weight="1"
            ss:Color="#000000"/>
            </Borders>
              <Font ss:FontName="宋体" x:CharSet="134" ss:Size="9"/>
            <Interior ss:Color="#C0C0C0" ss:Pattern="Solid"/>
        </Style>
    </Styles>
    <Worksheet ss:Name="商品列表">
        <Table ss:ExpandedColumnCount="<%=headList.size() %>" ss:ExpandedRowCount="65000" x:FullColumns="1"
               x:FullRows="1" ss:DefaultColumnWidth="99" ss:DefaultRowHeight="20.0625">
            <Row ss:AutoFitHeight="0" ss:Height="33.75">
                <%
                    for(int i=0;i<headList.size();i++){
                %>
                <Cell ss:StyleID="s67"><Data ss:Type="String"><%=headList.get(i) %></Data></Cell>
                <%
                    }
                %>

            </Row>

            <%
                for(int x=0;x<dataList.size();x++){

            %>
            <Row ss:AutoFitHeight="0">
                <Cell ss:StyleID="s68"><Data ss:Type="String"><%=dataList.get(x).getGoodName()==null?"":dataList.get(x).getGoodName()%></Data></Cell>
                <Cell ss:StyleID="s68"><Data ss:Type="String"><%=dataList.get(x).getGoodBrand()==null?"":dataList.get(x).getGoodBrand()%></Data></Cell>
                <Cell ss:StyleID="s68"><Data ss:Type="String"><%=dataList.get(x).getStoreAdd()==null?"":dataList.get(x).getStoreAdd()%></Data></Cell>
                <Cell ss:StyleID="s68"><Data ss:Type="String"><%=dataList.get(x).getSellerCredit()==null?"":dataList.get(x).getSellerCredit()%></Data></Cell>
                <Cell ss:StyleID="s68"><Data ss:Type="String"><%=dataList.get(x).getGoodPrice()==null?"":dataList.get(x).getGoodPrice()%></Data></Cell>
            </Row>
            <%
                }
            %>
        </Table>
        <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
            <PageSetup>
                <Header x:Margin="0.3"/>
                <Footer x:Margin="0.3"/>
                <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
            </PageSetup>
            <Unsynced/>
            <Print>
                <ValidPrinterInfo/>
                <PaperSizeIndex>9</PaperSizeIndex>
                <HorizontalResolution>200</HorizontalResolution>
                <VerticalResolution>200</VerticalResolution>
            </Print>
            <Selected/>
            <Panes>
                <Pane>
                    <Number>3</Number>
                    <ActiveRow>1</ActiveRow>
                </Pane>
            </Panes>
            <ProtectObjects>False</ProtectObjects>
            <ProtectScenarios>False</ProtectScenarios>
        </WorksheetOptions>
    </Worksheet>
</Workbook>
