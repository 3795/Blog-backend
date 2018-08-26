<html>
    <#include "../common/Head.ftl"/>
    <body>
        <div class="layui-container">
            <div class="layui-row layui-hide-xs">
                <#include "../common/NavigationBar.ftl" />
                <div class="layui-col-md9 main">
                    <#include "../common/TopBar.ftl"/>
                    <div class="layui-row">
                        <div class="layui-col-md12 ">
                            <div class="not-found">
                                Error: 404<br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <#--移动端-->
            <div class="layui-row layui-hide-lg layui-hide-md layui-hide-sm">
                <#include "../common/mobile/Navigation.ftl" />
                <div class="layui-col-xs12 mobile-main">
                    <#include "../common/mobile/TopBar.ftl"/>
                    <div class="layui-row">
                        <div class="layui-col-xs12 ">
                            <div class="not-found">
                                Error: 404<br/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#include "../common/Foot.ftl"/>
    </body>
</html>