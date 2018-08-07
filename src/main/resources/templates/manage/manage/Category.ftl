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
                                        <a class="layui-btn layui-btn-xs">编辑</a>
                                        <a class="layui-btn layui-btn-danger layui-btn-xs">删除</a>
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
                                    <label class="layui-form-label">启用</label>
                                    <div class="layui-input-block">
                                        <input type="radio" name="status" id="status" value="1" title="启用" checked>
                                        <input type="radio" name="status" id="status" value="0" title="不启用">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">选择父级</label>
                                    <div class="layui-input-block">
                                        <select name="parendId" id="parentId">
                                            <option value="">请选择父级分类</option>
                                            <option value="0">无</option>
                                            <#list categoryList as category>
                                                <#if category.status != 0>
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
                </div>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
        <script>
            layui.use('form', function(){
                var form = layui.form;
            });
        </script>
    </body>
</html>