<html>
    <#include "../common/Head.ftl"/>
    <body class="layui-layout-body">
        <div class="layui-layout layui-layout-admin">
        <#include "../common/NavigationBar.ftl" />
            <div class="layui-body">
                <!-- 内容主体区域 -->
                <div style="padding: 15px;">
                    <div class="layui-row">
                        <div class="layui-col-md9">
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
                                    <th>优先级</th>
                                    <th>链接地址</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list navigationList as navigation>
                                <tr>
                                    <td>${navigation.id}</td>
                                    <td>${navigation.name}</td>
                                    <td>${navigation.priority}</td>
                                    <td>${navigation.link}</td>
                                    <td>
                                        <#if navigation.status = 0>
                                            未启用
                                            <#else>
                                            启用
                                        </#if>
                                    </td>
                                <td>
                                    <a class="layui-btn layui-btn-primary layui-btn-xs" onclick="changeNavigationStatus(${navigation.id})">
                                        <#if navigation.status = 0>
                                            启用
                                        <#else>
                                            禁用
                                        </#if>
                                    </a>
                                    <a class="layui-btn layui-btn-xs" onclick="showNavigationArea(${navigation.id}, '${navigation.name}', ${navigation.priority}, '${navigation.link}')">编辑</a>
                                    <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="deleteNavigation(${navigation.id})">删除</a>
                                </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-col-md3">
                            <form class="layui-form category-add-form" action="#">
                                <p class="title">添加导航</p>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">导航名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" id="name" required  placeholder="请输入导航名称" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">优先级</label>
                                    <div class="layui-input-block">
                                        <input type="number" name="priority" id="priority" required  placeholder="请输入优先级" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">链接地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="link" id="link" required  placeholder="请输入链接地址" autocomplete="off" class="layui-input">
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
                                <button type="button" class="layui-btn" onclick="addNavigation()">添加导航</button>
                            </form>
                        </div>
                    </div>
                    <div class="layui-row" id="navigation-edit-area">
                        <form class="layui-form category-edit-form" action="#">
                            <p class="title">修改导航</p>
                            <input id="editId" type="hidden">
                            <div class="layui-form-item">
                                <label class="layui-form-label">导航名称</label>
                                <div class="layui-input-block">
                                    <input type="text" name="name" id="editName" required  placeholder="请输入导航名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">优先级</label>
                                <div class="layui-input-block">
                                    <input type="number" name="editPriority" id="editPriority" required  placeholder="请输入优先级" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">链接地址</label>
                                <div class="layui-input-block">
                                    <input type="text" name="editLink" id="editLink" required  placeholder="请输入链接地址" autocomplete="off" class="layui-input">
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
                            <button type="button" class="layui-btn" onclick="editNavigation()">修改导航</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <#include "../common/Foot.ftl"/>
    </body>
</html>