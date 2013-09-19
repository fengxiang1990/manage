/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function loadLeftMenu(id) {
    $("#maindiv").text("");
    $.get("./getAction.interface", {id: id},
    function(data) {
        $("#menuManage").load("" + data + "", {id: id});
    });
}
function changeBtnBackGround(id) {
    $(id).css({'font-weight': 'normal', 'background': 'url(images/select.jpg) no-repeat bottom left'
    });
}
function recoverBtnBackGround(id) {
    $(id).css({'font-weight': 'normal', 'background': 'url(images/btn.png) no-repeat bottom left'
    });
}
function loadLevel2Menu(id) {
    $("#lev2").text("");
    $.post("./menu2.interface", {id: id},
    function(data) {
         $("#lev2").text("");
        for (var i = 0; i < data.length; i++) {
            $("#lev2").append("<span  class='bg-button' onmouseout='recoverBtnBackGround(this)' onmouseover='changeBtnBackGround(this)' style='background:url(./images/btn.png) no-repeat bottom left' id='" + data[i].id + "'   onclick='loadLeftMenu(" + data[i].id + ")'><strong>" + data[i].name + "</strong></span>");
        }
    });
}

function loadLevel1Menu(pname) {
    $("#lev2").text("");
    $.post("./menu1.interface", {username: pname},
    function(data) {
        for (var i = 0; i < data.length; i++) {
            $("#lev1").append("<span class='bg-button' onmouseout='recoverBtnBackGround(this)' onmouseover='changeBtnBackGround(this);loadLevel2Menu(" + data[i].id + ")' style='background:url(./images/btn.png) no-repeat bottom left' id='" + data[i].id + "'onclick='loadLevel2Menu(" + data[i].id + ")'><strong>" + data[i].name + "</strong></span>");
        }
    });
}