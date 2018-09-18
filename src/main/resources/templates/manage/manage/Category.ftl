<html>
    <#include "../common/Head.ftl"/>
    <body class="layui-layout-body">
        <div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
            <div class="layui-body">
                <!-- 内容主体区域 -->
                <div style="padding: 15px;">
                    <div class="layui-row">
                        <div class="layui-col-md8">
                            <table class="layui-table">
                                <colgroup>
                                    <col width="150">
                                    <col width="200">
                                    <col>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>父ID</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list categoryList as category>
                                <tr>
                                    <td>${category.id}</td>
                                    <td>${category.name}</td>
                                    <td>${category.parentId}</td>
                                    <td>
                                        <#if category.status=0>
                                            未启用
                                            <#else>
                                            启用
                                        </#if>
                                    </td>
                                    <td>
                                        <a class="layui-btn layui-btn-primary layui-btn-xs" onclick="changeCategoryStatus(${category.id})">
                                            <#if category.status = 0>
                                                启用
                                            <#else>
                                                禁用
                                            </#if>
                                        </a>
                                        <a class="layui-btn layui-btn-xs" onclick="showArea(${category.id}, '${category.name}', ${category.parentId}, ${category.status})">编辑</a>
                                        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteCategory(${category.id})">删除</a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-col-md4">
                            <form class="layui-form category-add-form" action="#">
                                <p class="title">添加分类</p>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">分类名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" id="name" required  placeholder="请输入分类名称" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">是否启用</label>
                                    <div class="layui-input-block">
                                        <select name="status" id="status">
                                            <option value="">请选择分类状态</option>
                                            <option value="0">禁用</option>
                                            <option value="1">启用</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">选择父级</label>
                                    <div class="layui-input-block">
                                        <select name="parendId" id="parentId">
                                            <option value="">请选择父级分类</option>
                                            <option value="0">无</option>
                                            <#list categoryList as category>
                                                <#if category.status = 1 && category.parentId = 0 >
                                                    <option value="${category.id}">${category.name}</option>
                                                </#if>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                                <button type="button" class="layui-btn" onclick="addCategory()">添加分类</button>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="layui-col-md12 article-pagination">
                            <a href="/manage/category?page=1"><span class="layui-btn layui-btn-xs">首页</span></a>
                            <a href="/manage/category?page=${currentPage-1}"><span class="layui-btn layui-btn-xs">上一页</span></a>
                                <#if (maxPage > 5) >
                                    <#list 1..5 as page>
                                    <a href="/manage/category?page=${page}"><span class="layui-btn layui-btn-xs">${page}</span></a>
                                    </#list>
                                    <span class="layui-btn layui-btn-xs">...</span>
                                <#else>
                                    <#list 1..maxPage as page>
                                    <a href="/manage/category?page=${page}"><span class="layui-btn layui-btn-xs">${page}</span></a>
                                    </#list>
                                </#if>
                            <a href="/manage/category?page=${currentPage+1}"><span class="layui-btn layui-btn-xs">下一页</span></a>
                            <a href="/manage/category?page=${maxPage}"><span class="layui-btn layui-btn-xs">尾页</span></a>
                        </div>
                    </div>
                    <div class="layui-row" id="edit-area">
                        <form class="layui-form category-edit-form" action="#">
                            <p class="title">修改分类</p>
                            <input id="editId" type="hidden">
                            <div class="layui-form-item">
                                <label class="layui-form-label">分类名称</label>
                                <div class="layui-input-block">
                                    <input type="text" name="name" id="editName" required  placeholder="请输入分类名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">是否启用</label>
                                <div class="layui-input-block">
                                    <select name="status" id="editStatus">
                                        <option value="">请选择分类状态</option>
                                        <option value="0">禁用</option>
                                        <option value="1">启用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">选择父级</label>
                                <div class="layui-input-block">
                                    <select name="parendId" id="editParentId">
                                        <option value="">请选择父级分类</option>
                                        <option value="0">无</option>
                                            <#list categoryList as category>
                                                <#if category.status = 1 && category.parentId = 0 >
                                                    <option value="${category.id}">${category.name}</option>
                                                </#if>
                                            </#list>
                                    </select>
                                </div>
                            </div>
                            <button type="button" class="layui-btn" onclick="editCategory()">修改分类</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
    </body>
</html>