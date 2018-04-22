<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>添加新指令</title>
    <meta charset="utf-8" />
    <link href="${ctx}/static/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/head.jsp" />
<div class="container" style="margin-top: 50px">
    <h1>添加新指令</h1>
    <fieldset>
        <form action="${ctx}/manage/command/save" method="post" class="form-horizontal">
            <div id="form1">
                <div class="control-group">
                    <label class="control-label" for="command">指令:</label>
                    <div class="controls">
                        <input type="text" name="cmdname" id="command" style="height: 28px;" />
                    </div>
                </div>
                <div id="weixincontent" class="control-group">
                    <label class="control-label" for="content_txt">微信内容:</label>
                    <div class="controls">
                        <textarea id="content_txt" rows="6" cols="40" name="cmdcontent"></textarea>
                    </div>
                </div>
            </div>
            <div id="form2" style="display: none;">
                <div class="control-group">
                    <label class="control-label" for="command">图片标题:</label>
                    <div class="controls">
                        <input type="text" name="imgtitle1" id="imgtitle"
                               style="height: 28px;" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片地址:</label>
                    <div class="controls">
                        <input type="text" name="imgurl1" id="imgurl"
                               style="height: 28px;" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片描述:</label>
                    <div class="controls">
                        <input type="text" name="imgdes1" id="imgdes"
                               style="height: 28px;" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">图片跳转链接:</label>
                    <div class="controls">
                        <input type="text" name="imglink1" id="imglink"
                               style="height: 28px;" />
                    </div>
                </div>
            </div>
            <div id="form3" style="display: none;">
                <div class="control-group">
                    <label class="control-label" for="command">语音标题:</label>
                    <div class="controls">
                        <input type="text" name="audiotitle1" id="audiotitle"
                               style="height: 28px;" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">音频地址:</label>
                    <div class="controls">
                        <input type="text" name="audiourl1" id="audiourl1"
                               style="height: 28px;" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">高清音频地址:</label>
                    <div class="controls">
                        <input type="text" name="audiourl2" id="audiourl2"
                               style="height: 28px;" /><span style="color:red">（可与上相同或不填写）</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="command">音频描述:</label>
                    <div class="controls">
                        <input type="text" name="audiodes1" id="audiodes1"
                               style="height: 28px;" />
                    </div>
                </div>
                <div id="audiocontent" class="control-group">
                    <label class="control-label" for="audio_content_txt">音频配文:</label>
                    <div class="controls">
                        <textarea id="audio_content_txt" rows="3" name="audio_content"></textarea>
                    </div>
                </div>
            </div>

            <div id="form4">
                <div class="control-group">
                    <label class="control-label" for="type">状态</label>
                    <div class="controls">
                        <select name="type" id="type">
                            <option value="0">永久</option>
                            <option value="1">临时</option>
                        </select>
                        <span style="color: red;"> 永久状态无需填写生效、失效时间</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="beginTime">生效时间</label>
                    <div class="controls date input-append">
                        <input type="text" name="beginTime" id="beginTime" style="height:28px;" value=""/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="endTime">失效时间</label>
                    <div class="controls">
                        <input type="text" name="endTime" id="endTime" style="height:28px;" value=""/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <label class="radio"> <input id="ptxt" type="radio" name="cmdtype" value="text" /> 纯文本</label>
                    <label class="radio"> <input id="imgtxt" type="radio" name="cmdtype" value="news"> 图文混排</label>
                    <label class="radio"> <input id="audioMsg" type="radio" name="cmdtype" value="music"> 语音信息</label>
                    <button type="submit" class="btn">提交</button>
                    <a href="${ctx}/manage/command/list"><button type="button" class="btn">回到首页</button></a>
                </div>
            </div>
        </form>
    </fieldset>
</div>

<script>var weixinType;</script>
<link href="${ctx}/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery-1.8.3.min.js"></script>
<script src="${ctx}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${ctx}/static/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/static/js/form.js"></script>
</body>
</html>

