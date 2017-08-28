<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 符柱成
  Date: 2017/5/25
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Ajax与报表</title>
    <meta charset="UTF-8">


</head>


<body>
<div >
<form action="findGoods" method="post"
      id="queryForm">
    <div class="col-md-6">
        <table class="table">
            <thead>
            <tr>
                <th><input type="checkbox" name="chooseAll"
                /></th>
                <th>商品名</th>
                <th>商品类型</th>
                <th>商品地址</th>
                <th>商品星级</th>
                <th>商品价格</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${goodslist}">
                <tr>
                    <td style="text-align:center;"><input type="checkbox"
                                                          name="PK"/></td>
                    <td>${item.goodName}</td>
                    <td>${item.goodBrand}</td>
                    <td>${item.storeAdd}</td>
                    <td>${item.sellerCredit}</td>
                    <td>${item.goodPrice}</td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

    <div class="modal">
        <p>
            <label>商品地址:</label>
            <select name="location" id="location" onchange="locationChange()">
                <option value="">请选择</option>
                <option value="广东省">广东省</option>
                <option value="北京市">北京市</option>
            </select>
        </p>
        <p>
            <label>列出对应的账号:</label>
            <select name="accounts" id="accounts">
                <option value="">请选择</option>
            </select>
        </p>
    </div>
    <div class="col-xs-4">
        <button type="submit" class="btn btn-lg btn-default">查询</button>
    </div>

    <div style="width:100%;text-align:center">
        <button><a href="getExcel">下载商品列表</a></button>
    </div>


</form>
</div>
<div>
    <form id="formItem" method="post" action="uploadExcel"   enctype="multipart/form-data">
        <div id="fileArea"
             style="text-align: left; margin: 10px 10px 10px 20px;">
            <p>
                <label>选择导入文件：</label>
                <input type="file" id="filename" name="filename" />
            </p>
            <input type="button" name="upload" id="upload" value="导入数据" onclick="doLoadTask()">

        </div>
    </form>

</div>


<div id="manualHelpTable"
     style="text-align: left; margin-left: 20px;">
    <table>
        <tbody style="vertical-align: top;">
        <tr>
            <td style="white-space:nowrap;">文件类型:</td>
            <td>
                .xls或.xlsx电子表格文件 (1.表格中不需要留空行； 2.文件名的后缀必须是小写的".xls"或".xlsx"；)
            </td>
        </tr>
        <tr>
            <td style="white-space:nowrap;">备注:</td>
            <td>
                <pre>导入支持新增，暂不支持修改数据，仅支持商品数据导入</pre>
            </td>
        </tr>
        <tr>
            <td style="white-space:nowrap;">导入模板:</td>
            <td style="color: blue;">
                <a href="../../source/plan.xls">商品表导入模板.xls</a>&nbsp;&nbsp;&nbsp;
                <a href="../../source/plan.xlsx">商品表导入模板.xlsx</a>
            </td>
        </tr>

        </tbody>
    </table>
</div>

<div style="width:100%;text-align:center">
    <button><a href="downloadPoiExecl">POI下载文件</a></button>
</div>
<!-- ./wrapper -->
<!-- jQuery 2.2.0 -->
<script src='../../js/jquery-2.2.3.min.js'></script>
<!-- Bootstrap 3.3.6 -->
<script src='../../js/bootstrap.min.js'></script>
<!-- FastClick -->
<script src='../../js/fastclick.js'></script>
<script>
    function locationChange() {

        var location = $("#location").find("option:selected").text();
        alert(location)
        var obj = document.getElementById('accounts');
        obj.options.length = 0;
        obj.add(new Option("请选择", ""));
        $.ajax({
            type: "post",
            url: "/ajax/findUserByProvince",
            cache: false,
            data: {location: location},
            dataType: "json",
            success: function (result) {
                if (result.length > 0) {
                    obj.add(new Option("请选择", ""));
                    for (var i in result) {
                        var selectOption = new Option(result[i].account, result[i].account);
                        obj.add(selectOption);
                    }
                }
            }
        });
    }
    function doLoadTask() {
        var file = $("#filename").val();
        if (file == '') {
            alert("请选择待处理文件");
            return;
        } else {
            //获取文件类型后缀
            var temp = file.substring(file.lastIndexOf("\\") + 1).toString();
//            alert(temp)
            var extend = file.substring(file.lastIndexOf(".") + 1).toString();
//            alert(extend)
            if (extend == "") {
                alert("请选择正确格式的文件!");
                return;
            } else {
                if (!(extend == "xlsx") && !(extend == "xls")) {
                    alert("请选择正确格式的文件!");
                    return;
                }
            }
        }

//        var form = document.getElementById("formItem");
        $("#formItem").submit();
    }

</script>
</body>
</html>
