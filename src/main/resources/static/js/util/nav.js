/*
*@Name: 母婴商城
*@Author: xuzhiwen
*@Copyright:layui.com
*/


layui.define(['jquery', 'layer'], function (exports) {
    let $ = layui.$, layer = layui.layer;
    let _nav = {
        logout: function () {
            $.get("/user/logout", function (res) {
                if (res.resultCode === 200){
                    layer.msg("注销成功", {
                        time: 1000,
                        end: function () {
                            $('.nav-layui').load("/refreshNav");
                        }
                    });
                }else{
                    layer.msg("操作失败！");
                }
            });
        }
    };
    exports('nav', _nav)
});