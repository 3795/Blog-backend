<div class="layui-row pagination">
    <a href="${url + (currentPage-1)}"><button class="layui-btn"><em>←</em></button></a>
    <a href="${url + 1}"><button class="layui-btn">1</button></a>
        <#if (maxPage > 2) >
            <#list 2..2 as page>
                <a href="${url + page}"><button class="layui-btn">${page}</button></a>
            </#list>
            <a><button class="layui-btn">...</button></a>
        </#if>
    <#if (maxPage > 1)>
        <a href="${url + maxPage}"><button class="layui-btn">${maxPage}</button></a>
    </#if>
    <a href="${url + (currentPage+1)}"><button class="layui-btn"><em>→</em></button></a>
</div>