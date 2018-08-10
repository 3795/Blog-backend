<html>
    <#include "../common/Head.ftl"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-row">
                <div class="layui-col-md12 show-article">
                    <h1 class="title">${article.title}</h1>
                    <div class="row property">
                        <div class="layui-col-md3"><i class="layui-icon layui-icon-note"></i>${article.categoryName}</div>
                        <div class="layui-col-md3"><i class="layui-icon layui-icon-tips"></i>${article.statusMsg}</div>
                        <div class="layui-col-md3"><i class="layui-icon layui-icon-log"></i>${article.createTime?string('yyyy-MM-dd HH:mm')}</div>
                        <div class="layui-col-md3"><i class="layui-icon layui-icon-log"></i>${article.updateTime?string('yyyy-MM-dd HH:mm')}</div>
                    </div><hr/>
                    <div class="summary">
                        ${article.summary}
                    </div>
                    <div class="content">
                        ${article.content}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        <#include "../common/Foot.ftl"/>
</body>
</html>