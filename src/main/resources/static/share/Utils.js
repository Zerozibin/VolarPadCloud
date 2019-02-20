
function getUrl(){
    var url = window.location.pathname;
    // var id = url.substr(8, url.length)
    // $("#" + id).addClass("cur")
    return url;
}

//公用加载头部和尾部的方法
function introduceHeadAndFoot(head,foot){
        //jquery load方法加载公共头部
        $("#"+head).load("templates/share/head.html", function () {//加载完成后设置高亮
            // $("#navigation_1").children().attr("style","color: #fff");
            // $("#navigation_1").attr("class","selected");
        });
        $("#"+foot).load("templates/share/foot.html", function () {//加载完成后设置高亮
        });
}
//div加载页面的方法
function introducePage(id,name){
    $("#"+id).load("templates/home/center/"+name+".html", function () {//加载完成后设置高亮
    });
}


