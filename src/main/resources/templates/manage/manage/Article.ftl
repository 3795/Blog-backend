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
                            <table class="layui-table" id="article">
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
                                        <a class="layui-btn layui-btn-primary layui-btn-xs" href="/manage/article/${article.id}" target="_blank">查看</a>
                                        <a class="layui-btn layui-btn-normal layui-btn-xs" onclick="changeArticleStatus(${article.id})">更改状态</a>
                                        <a class="layui-btn layui-btn-xs" href="/manage/article/edit/${article.id}">编辑</a>
                                        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteArticle(${article.id})">删除</a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <div class="layui-col-md12 article-pagination">
                                <a href="/manage/article?page=1"><span class="layui-btn layui-btn-xs">首页</span></a>
                                <a href="/manage/article?page=${currentPage-1}"><span class="layui-btn layui-btn-xs">上一页</span></a>
                                <#if (maxPage > 5) >
                                    <#list 1..5 as page>
                                    <a href="/manage/article?page=${page}"><span class="layui-btn layui-btn-xs">${page}</span></a>
                                    </#list>
                                    <span class="layui-btn layui-btn-xs">...</span>
                                <#else>
                                    <#list 1..maxPage as page>
                                    <a href="/manage/article?page=${page}"><span class="layui-btn layui-btn-xs">${page}</span></a>
                                    </#list>
                                </#if>
                                <a href="/manage/article?page=${currentPage+1}"><span class="layui-btn layui-btn-xs">下一页</span></a>
                                <a href="/manage/article?page=${maxPage}"><span class="layui-btn layui-btn-xs">尾页</span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
    </body>
</html>