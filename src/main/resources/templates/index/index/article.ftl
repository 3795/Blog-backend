<html>
    <#include "../common/Head.ftl"/>
<body>
<div class="layui-container">
    <div class="layui-row layui-hide-xs">
        <#include "../common/NavigationBar.ftl" />
        <div class="layui-col-md10 main">
        <#include "../common/TopBar.ftl"/>
            <div class="layui-row">
                <div class="layui-col-md-offset1 layui-col-md10">
                    <div class="article">
                        <p class="title">${article.title}</p><hr/>
                        <div class="content" id="content-editormd">
                            <textarea style="display:none;">${article.content}</textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--移动端-->
    <div class="layui-row layui-hide-lg layui-hide-md layui-hide-sm">
        <#include "../common/mobile/Navigation.ftl" />
        <div class="layui-col-xs12 mobile-main">
        <#include "../common/mobile/TopBar.ftl"/>
            <div class="layui-row">
                <div class="layui-col-xs12">
                    <div class="article">
                        <p class="title">${article.title}</p><hr/>
                        <div class="content" id="content-editormd-mobile">
                            <textarea style="display:none;">${article.content}</textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <#include "../common/Foot.ftl"/>
<script src="/editor.md/editormd.min.js"></script>
<script src="/editor.md/lib/flowchart.min.js"></script>
<script src="/editor.md/lib/jquery.flowchart.min.js"></script>
<script src="/editor.md/lib/marked.min.js"></script>
<script src="/editor.md/lib/prettify.min.js"></script>
<script src="/editor.md/lib/raphael.min.js"></script>
<script src="/editor.md/lib/sequence-diagram.min.js"></script>
<script src="/editor.md/lib/underscore.min.js"></script>
<script type="text/javascript">
    $(function () {
        editormd.markdownToHTML("content-editormd", {
            htmlDecode: "style,script,iframe", //可以过滤标签解码
            emoji: true,
            taskList: true,
            tex: true,               // 默认不解析
            flowChart: true,         // 默认不解析
            sequenceDiagram: true, // 默认不解析
            codeFold: true
        });

        editormd.markdownToHTML("content-editormd-mobile", {
            htmlDecode: "style,script,iframe", //可以过滤标签解码
            emoji: true,
            taskList: true,
            tex: true,               // 默认不解析
            flowChart: true,         // 默认不解析
            sequenceDiagram: true, // 默认不解析
            codeFold: true
        });
    })
</script>
</body>
</html>