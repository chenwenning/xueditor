<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>hello chenwenning</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.parse.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <%--http://www.voidcn.com/blog/haishui2/article/p-3892007.html--%>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="ueditor/third-party/video-js/video.js"></script>
    <link rel="stylesheet" href="http://127.0.0.1/ueditor/third-party/video-js/video-js.css">
    <script type="text/javascript" src="ueditor/third-party/video-js/html5media.min.js"> </script>
    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
    <script>
        videojs.options.flash.swf = "ueditor/third-party/video-js/video-js.swf"
    </script>
</head>
<body>
<div>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div id="btns">
    <div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
    </div>
</div>
<p>
    <video width="420" height="280" data-setup="{}" src="http://oayhgt4fd.bkt.clouddn.com/zhangliang.mp4" preload="none" controls="" class="edui-upload-video  vjs-default-skin video-js">

    </video>
</p>

</body>
<script type="text/javascript">
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;

    var ue = UE.getEditor('editor');

    /**
     * 自定义上传图片 附件等地址
     * @param action
     * @returns {*} chenwening
     */
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage' || action == "uploadfile") {//上传附件   上传图片等
            return 'http://127.0.0.1/fileUploadServlet?action=' + action;
        } else if (action == 'uploadvideo') {//上传视频
            return 'http://127.0.0.1/fileUploadServlet?action=' + action;
        } else if (action == "listimage") {//获取相册的图片 可以做根据用户id获取这个用户上传的图像
            return "";
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml());
    }
</script>
</html>
