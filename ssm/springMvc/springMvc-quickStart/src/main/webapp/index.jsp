<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 2022/10/12
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(function () {
            $("#AjaxButton").bind("click", function () {

                $.ajax({
                    url: "/demo/handle07",
                    type: 'post',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify({"id":1,"name":"张三"}),
                    success: function (data) {
                        console.log(data)
                    }
                })
            })
        })
    </script>
</head>
<body>
<div>
    <h2>测试Ajax数据</h2>
    <fieldset>
        <input type="button" value="提交Ajax数据" id="AjaxButton">
    </fieldset>
</div>
<div>
    <h2>multipart文件上传</h2>
    <fieldset>
        <form method="post" enctype="multipart/form-data" action="/demo/upload">
            <input type="file" name="uploadFile">
            <input type="submit" value="上传文件">
        </form>
    </fieldset>
</div>

</body>
</html>
