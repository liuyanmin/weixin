<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="utf-8"/>
    <title>微信后台-修改指令</title>
    <link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/head.jsp" />
<div class="container" style="margin-top: 50px">
    <h1>修改指令</h1>
    <fieldset>
        <form action="${ctx}/manage/command/save" method="post" class="form-horizontal">
            <div id="form1">
                <div class="control-group">
                    <label class="control-label" for="command">指令:</label>
                    <div class="controls">
                        <input readonly="true" type="text" name="cmdname" id="command" style="height:28px;" value="${command.cmdname}"/>
                    </div>
                </div>
                <div id="weixincontent" class="control-group">
                    <label class="control-label" for="content">微信内容:</label>
                    <div class="controls" id="content">
                        <textarea id="content_txt" rows="6" cols="40" name="cmdcontent">${command.cmdcontent}</textarea>
                    </div>
                </div>
            </div>
            <div id="form2" style="display:none;">
                <div class="control-group">
                    <label class="control-label" for="command">图片标题:</label>
                    <div class="controls">
                        <input type="text" name="imgtitle1" id="imgtitle" style="height:28px;" value="${command.imgtitle1}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片地址:</label>
                    <div class="controls">
                        <input type="text" name="imgurl1" id="imgurl" style="height:28px;" value="${command.imgurl1}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片描述:</label>
                    <div class="controls">
                        <input type="text" name="imgdes1" id="imgdes" style="height:28px;" value="${command.imgdes1}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片跳转链接:</label>
                    <div class="controls">
                        <input type="text" name="imglink1" id="imglink" style="height:28px;" value="${command.imglink1}"/>
                    </div>
                </div>
                <!--input type="button" class="btn btn-inverse" value="继续添加"></input-->
            </div>
            <div id="form3" style="display: none;">
                <div class="control-group">
                    <label class="control-label" for="command">语音标题:</label>
                    <div class="controls">
                        <input type="text" name="audiotitle" id="audiotitle" style="height: 28px;" value="${command.audiotitle}" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">音频地址:</label>
                    <div class="controls">
                        <input type="text" name="audiourl" id="audiourl1" style="height: 28px;" value="${command.audiourl}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">高清音频地址:</label>
                    <div class="controls">
                        <input type="text" name="audiourl2" id="audiourl2"
                               style="height: 28px;" value="${command.audiohqurl}" />（可与上相同或不填写）
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">音频描述:</label>
                    <div class="controls">
                        <input type="text" name="audiodes1" id="audiodes1"
                               style="height: 28px;" value="${command.audiodes}" />
                    </div>
                </div>
                <div id="audiocontent" class="control-group">
                    <label class="control-label" for="content">音频配文:</label>
                    <div class="controls">
                        <textarea id="audio_content_txt" rows="3" name="audiotxt">${command.audiotxt}</textarea>
                    </div>
                </div>
            </div>

            <div id="form4">
                <div class="control-group">
                    <label class="control-label" for="type">状态</label>
                    <div class="controls">
                        <select name="type" id="type">
                            <option value="0" <c:if test="${command.type == 0}">selected</c:if>>永久</option>
                            <option value="1" <c:if test="${command.type == 1}">selected</c:if>>临时</option>
                        </select>
                        <span style="color: red;"> 永久状态无需填写生效、失效时间</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="beginTime">生效时间</label>
                    <div class="controls">
                        <input type="text" name="beginTime" id="beginTime" style="height:28px;" value="${command.beginTime}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="endTime">失效时间</label>
                    <div class="controls">
                        <input type="text" name="endTime" id="endTime" style="height:28px;" value="${command.endTime}"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <label class="radio"><input id="ptxt" type="radio" name="cmdtype" value="text"/> 纯文本</label>
                    <label class="radio"><input id="imgtxt" type="radio" name="cmdtype" value="news">  图文混排</label>
                    <label class="radio"> <input id="audioMsg" type="radio" name="cmdtype" value="music"> 语音信息</label>
                    <button type="submit" class="btn">提交</button>
                    <a href="${ctx}/manage/command/list" class="btn">放弃</a>
                </div>
            </div>
        </form>
    </fieldset>
</div>

<script>var weixinType = ''</script>
<link href="${ctx}/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery-1.8.3.min.js"></script>
<script src="${ctx}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/static/js/form.js"></script>
</body>
</html>
