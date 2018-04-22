<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>微信后台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
    <style>body {padding-top: 60px;}</style>
    <link href="${ctx}/static/css/bootstrap-responsive.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="../assets/ico/favicon.png">

</head>
<body>
    <jsp:include page="../common/head.jsp" />

    <div class="container">
        <h1>微信自定义指令平台</h1><br>
        <form role="form" action="" method="post">
            <div class="input-group col-md-3" style="margin: 0;padding: 0;" >
                <input type="text" name="text_key" style="margin: 0;padding: 12px;" class="form-control" placeholder="请输入指令" value=""/>
                <span class="input-group-btn">
                   <button class="btn btn-info btn-search" type="submit">查找</button>
                </span>
            </div>
        </form>
        <table id="cmdtable" class="table table-bordered">
            <tbody>
                <tr>
                    <td colspan='4'>
                        <a href='#' class='btn'>清理关键词回复缓存</a>
                        <a href='${ctx}/manage/command/add' class='btn'>添加指令</a>
                    </td>
                </tr>
                <tr>
                    <th>指令</th>
                    <th>微信内容</th>
                    <th>类型</th>
                    <th>状态</th>
                    <th>编辑</th>
                </tr>

                <c:forEach items="${commandList}" var="command" varStatus="status">
                    <tr>
                        <td><a href='#'>${command.cmdname}</a></td>
                        <td>${command.cmdcontent}</td>
                        <td>${command.cmdtype}</td>
                        <td><c:if test="${command.type == 0}">永久</c:if><c:if test="${command.type == 1}">临时</c:if></td>
                        <td style='width:110px;'>
                            <a href='${ctx}/manage/command/edit?cmdName=${command.cmdname}'><button id='modif${status.count}' class='btn' type='button'>修改</button></a>
                            <a href='#'><button id='delete${status.count}' class='btn' type='button'>删除</button></a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>

    <script src="${ctx}/static/js/weixin_main.js" />
</body>
</html>
