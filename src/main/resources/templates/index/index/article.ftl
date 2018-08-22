<html>
    <#include "../common/Head.ftl"/>
<body>
<div class="layui-container">
    <div class="layui-row">
                <#include "../common/NavigationBar.ftl" />
        <div class="layui-col-md10 main">
                    <#include "../common/TopBar.ftl"/>
            <div class="layui-row">
                <div class="layui-col-md-offset1 layui-col-md10">
                    <div class="article">
                        <p class="title">${article.title}</p><hr/>
                        <div class="content">${article.content}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <#include "../common/Foot.ftl"/>
    <script type="text/javascript" src="/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
    <script>
        SyntaxHighlighter.all();        //执行代码高亮
    </script>
</body>
</html>