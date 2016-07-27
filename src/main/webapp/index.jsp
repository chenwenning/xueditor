<html>
<head>
    <title>hello chenwenning</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
</head>
<body>
<div>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
</body>
<script type="text/javascript">
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;

    var ue = UE.getEditor('editor');

    /**
     * 自定义上传地址
     * @param action
     * @returns {*} chenwening
     */
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage' || action == "uploadfile") {
            return 'http://127.0.0.1/fileUploadServlet?action=' + action;
        } else if (action == 'uploadvideo') {//上传视频
            return 'http://127.0.0.1/fileUploadServlet?action=' + action;
        } else if (action == "listimage") {//获取相册的图片 可以做根据用户id获取这个用户的图像
            return "";
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
</html>
