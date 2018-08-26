<div class="layui-col-xs5 mobile-navigation " id="mobile-navigation" xmlns="http://www.w3.org/1999/html">
    <div class="layui-row">
        <div class="layui-col-xs12 user">
            <img src="${user.avatar}" title="${user.username}" alt="头像" class="avatar">
            <br/><br/><h2>${user.username}</h2><br/>
            <p>
                <span><a href="https://blog.csdn.net/zai_xia" target="_blank">CSDN</a></span>
                <span><a href="https://github.com/2471554649" target="_blank">GitHub</a></span>
            </p>
        </div>
        <#list navigationList as navigation>
        <a href="${navigation.link!""}">
            <div class="layui-col-xs12 item"  onmouseover="this.style.backgroundColor='#FFFFFF'"  onmouseout="this.style.backgroundColor='rgba(232, 238, 242, 0.91)'">
                ${navigation.name}
            </div>
        </a>
        </#list>
        <div class="copyright">
            Designed by ${user.username!Seven.wk}
        </div>
    </div>
</div>
