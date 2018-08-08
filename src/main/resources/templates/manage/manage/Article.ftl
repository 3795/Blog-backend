<html>
    <#include "../common/Head.ftl"/>
    <body class="layui-layout-body">
        <div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
            <div class="layui-body">
                <!-- 内容主体区域 -->
                <div style="padding: 15px;">
                    <div class="layui-row">
                        <div class="layui-col-md12">
                            <table class="layui-table">
                                <colgroup>
                                    <col width="150">
                                    <col width="200">
                                    <col>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>分类</th>
                                    <th>发布时间</th>
                                    <th>更新时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list articleList as article>
                                <tr>
                                    <td>${article.id}</td>
                                    <td>${article.title}</td>
                                    <td>${article.categoryName}</td>
                                    <td>${article.createTime?string('yyyy-MM-dd HH:mm')}</td>
                                    <td>${article.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                                    <td>${article.statusMsg}</td>
                                    <td>
                                        <a class="layui-btn layui-btn-primary layui-btn-xs">查看</a>
                                        <a class="layui-btn layui-btn-normal layui-btn-xs" onclick="changeArticleStatus(${article.id})">更改状态</a>
                                        <a class="layui-btn layui-btn-xs" >编辑</a>
                                        <a class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
    </body>
</html>