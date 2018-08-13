<html>
    <#include "../common/Head.ftl"/>
    <body>
        <div class="layui-container">
            <div class="layui-row">
                <#include "../common/NavigationBar.ftl" />
                <div class="layui-col-md10 main">
                    <#include "../common/TopBar.ftl"/>
                    <div class="layui-row">
                        <div class="layui-col-md-offset2 layui-col-md8">
                            <div class="layui-col-md12 card">
                                <div class="mix">
                                    ${(category.name)}
                                        <div class="child-category">
                                            <#list categoryList as category>
                                                <a href="/category/${category.id}"><div class="item">${category.name}</div></a>
                                            </#list>
                                        </div>
                                </div>
                            </div>

                            <#list articleList as article>
                            <div class="layui-col-md12 card">
                                <div class="above">
                                    <div class="layui-col-md5">
                                        <img src="${article.img}" title='${article.title}' alt="${article.title}" class="thumbnail">
                                    </div>
                                    <div class="layui-col-md7">
                                        <p class="title"><a href="/article/${article.id}" target="_blank">${article.title}</a></p>
                                        <p class="summary">
                                            ${article.summary}
                                        </p>
                                    </div>
                                </div><hr/>
                                <div class="below">
                                    <div class="layui-col-md4">
                                        <i class="layui-icon layui-icon-log"></i>${article.createTime?string("yyyy-MM-dd")}
                                    </div>
                                    <div class="layui-col-md4 ">
                                        <i class="layui-icon layui-icon-note"></i>${article.categoryName}
                                    </div>
                                    <div class="layui-col-md4"><a href="/article/${article.id}" target="_blank">阅读全文</a></div>
                                </div>
                            </div>
                            </#list>

                        </div>
                    </div>
                    <#include "../common/Pagination.ftl"/>
                </div>
            </div>
        </div>
    <#include "../common/Foot.ftl"/>
    </body>
</html>