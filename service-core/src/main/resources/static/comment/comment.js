function startAndStop(id, url) {
    var index = layer.load(1)
    $.ajax({
        //请求方式
        type: "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url: url,
        //数据，json字符串
        data: id,
        //dataType: 'json',
        //请求成功
        success: function (result) {
            layer.close(index);
            if (result.code == 200 && result.data == true) {
                layui.table.reload('currentTableId');//重载父页表格，参数为表格ID
                layer.msg(result.message, {icon: 6, time: 1000});
            } else {
                layer.msg("异常信息：" + result.message, {icon: 5});
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            layer.close(index);
            layer.msg("异常信息：" + e.responseText, {anim: 6, icon: 5});
        }

    });
}