<html>
    <#include "../common/Head.ftl"/>
    <body>
        <div class="layui-container">
            <div class="layui-row">
                <#include "../common/NavigationBar.ftl" />
                <div class="layui-col-md9 main">
                    <#include "../common/TopBar.ftl"/>
                    <div class="layui-row">
                        <div class="layui-col-md-offset2 layui-col-md8">
                            <div class="layui-col-md12 card">
                                <div class="pure">
                                    ${(category.name)!"NTShare"}
                                </div>
                            </div>

                            <#list articleList as article>
                            <div class="layui-col-md12 card">
                                <div class="article">
                                    <div class="layui-col-md5">
                                        <img src="${article.img}" title='${article.title}' alt="${article.title}" class="thumbnail">
                                    </div>
                                    <div class="layui-col-md7">
                                        <p class="title">${article.title}</p>
                                        <p class="summary">
                                            ${article.summary}
                                        </p>
                                    </div>
                                </div><hr/>
                                <div class="detail">
                                    <div class="layui-col-md4">
                                        <i class="layui-icon layui-icon-log"></i>${article.createTime?string("yyyy-MM-dd")}
                                    </div>
                                    <div class="layui-col-md4 ">
                                        <i class="layui-icon layui-icon-note"></i>${article.categoryName}
                                    </div>
                                    <div class="layui-col-md4">阅读全文</div>
                                </div>
                            </div>
                            </#list>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#include "../common/Foot.ftl"/>
    </body>
</html>