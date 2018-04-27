//显示左边笔记导航
function getAllNotes(){
    $.ajax({
        type:'post',
        url:"/GetAllNotesServlet",
        dataType: 'json',
        success:function(data){
            console.log(data);
            var box = `<div class="panel panel-default" style="margin-bottom: 0px">
                            <div class="panel-heading">
                                    <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                            创建的笔记 <span class="badge col-md-offset-1" >${data.create.length}</span>
                                                </a>
                                        </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body" style="padding: 0em">
            
                        <div id="sidebar-wrapper">
                                <ul class="list-group" style="margin: 0em">`;
            for(var i = 0; i < data.create.length; i++){
                box += `
                    <li class="list-group-item" id = ${data.create[i].nid}><h4>${data.create[i].nname}</h4><p>${data.create[i].createTime}</p></li>
                `;
            }
            box += `</ul>
			       </div>
                    </div>
                    </div>
                    </div>`;

            var box1 = `<div class="panel panel-default" style="margin-top: 0px">
                            <div class="panel-heading">
                                    <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                            收到的笔记 <span class="badge col-md-offset-1" >${data.receive.length}</span>
                                                </a>
                                        </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse in">
                                    <div class="panel-body" style="padding: 0em">
                        <div id="sidebar-wrapper">
                                <ul class="list-group" style="margin: 0em">`;
            for(var i = 0; i < data.receive.length; i++){
                box1 += `
                        <li class="list-group-item" id = ${data.receive[i].nid}><h4>${data.receive[i].nname}</h4><p>${data.receive[i].createTime}</p></li>
                            `;
            }
            box1 += `</ul>
			       </div>
                    </div>
                    </div>
                    </div>`;
            $("#create-note").html(box);
            $("#received-note").html(box1);
            $(".list-group-item").click(function(){
                var index = jQuery(this).attr("id");
                console.log(index);
                $(".list-group-item").css('background','#fff');
                $(this).css('background','#bdc3c7');
                printNote(index);
            });
        }
    });

}

//显示笔记
function printNote(nid) {
    $.ajax({
        type:"post",//请求方式
        url:"/GetNoteInfoServlet",
        data:{nid:nid},
        timeout:30000,//超时时间：30秒
        dataType:"json",//设置返回数据的格式
        success:function(data){
            var temp = `
            <div>
                <div class="row">
                    <div class="col-md-2" id="version">
                    </div>
                    <div class="col-md-8">
                        <h1 id="content-title" contenteditable="true">${data.nname}</h1>
                        <h5>作者：${data.uname} 创建时间：${data.createTime}</h5>
                    </div>
                    <div class="col-md-2 right-content-button">
                        <button class="btn btn-danger" onclick='delNote(${data.nid})'><span class="glyphicon glyphicon-remove"></span> 删除</button>
                        <button class="btn btn-default" id="save-edit"><span class="glyphicon glyphicon-save"></span> 保存</button>
                        <button class="btn btn-success" onclick="newNote()"><span class="glyphicon glyphicon-plus"></span> 新建</button>
                    </div>
                </div>
                <div class="row">
                    <div id="text-content" class=" col-md-offset-2 col-md-8 contentArea" contenteditable="true">
                        ${data.content}
                    </div>
                </div>
            </div>
            `;
            $("#right-content").html(temp);
            //添加修改监控
            $("#save-edit").click(function() {
                var contents = $("#text-content").html();
                console.log(contents);
                var title = $('#content-title').text();
                saveNote(title,contents);
            });
            //显示历史版本和多人协作按钮
            var nname = `${data.nname}`;
            var uid = `${data.aid}`;
            $.ajax({
                type: "post",//请求方式
                url: "/GetAllVersionServlet",
                data: {nid: nid,nname: nname,uid: uid},
                timeout: 30000,//超时时间：30秒
                dataType: "json",//设置   返回数据的格式
                //请求成功后的回调函数 data为json格式
                success:function(data) {
                    var button = "<br><div class=\"btn-group\">\n" +
                        " <button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
                        "历史版本<span class=\"caret\"></span>\n" +
                        "  </button>\n" +
                        "  <ul class=\"dropdown-menu\">\n";
                    for(var i = 0; i < data.lists.length; i++){
                        button += `<li><a onclick='printNote(${data.lists[i].nid})'>${data.lists[i].createTime}</a></li>`
                    }
                    button+="  </ul>\n" + "</div>";
                    button+=`<button id="send-note" class="btn btn-default" style="margin-left: 3px"><span class="glyphicon glyphicon-send"></span> 分享</button>`;
                    $("#version").html(button);
                    //多人协作
                    $("#send-note").click(function() {
                        var uname = prompt("请输入对方的名字", "");
                        $.ajax({
                            type: "post",//请求方式
                            url: "/SendNoteServlet",
                            data: {nid: nid,uname: uname},
                            timeout: 30000,//超时时间：30秒
                            dataType: "json",//设置   返回数据的格式
                            //请求成功后的回调函数 data为json格式
                            success:function(data){
                                console.log(data.message);
                                if(data.message=="1") {
                                    alert("发送成功");
                                }
                            },
                            //请求出错的处理
                            error:function(){
                                alert("发送出错");
                            }
                        });
                    });
                },
                //请求出错的处理
                error:function(){
                    alert("显示出错");
                }

            });
        },
        //请求出错的处理
        error:function(){
            // alert("显示出错");
        }
    });
}

//跳转到新建页面
function newNote(){
    var a = `<div>
                <div class="row">
                    <div class="col-md-2" id="version">
                        
                    </div>
                    <div class="col-md-8 content-title" >
                        <h1><input id="content-title" placeholder="请输入标题"></h1>
                    </div>
                    <div class="col-md-2 right-content-button">
                        <button class="btn btn-default" id="add-note"><span class="glyphicon glyphicon-plus"></span> 保存</button>
                    </div>
                </div>
                <div class="row">
                    <div id="text-content" class=" col-md-offset-2 col-md-8 contentArea" contenteditable="true" style="border-width: 0 0 1px 0;border-style: solid;border-color: black;">
                        
                    </div>
                </div>
            </div>
    `
    $.ajax({
        success:function(){
            $("#right-content").html(a);
            //添加保存监控
            $("#add-note").click(function() {
                var contents = $("#text-content").html();
                console.log(contents);

                var title = $('#content-title').val();
                console.log(title);
                addNotePost(title,contents);
            });
        },
        //请求出错的处理
        error:function(){
            alert("跳转出错");
        }
    });
}
//删除笔记
function delNote(nid) {
    var msg = "您真的确定要删除吗？\n\n请确认！";
    if (confirm(msg)==true){
        $.ajax({
            type:"post",//请求方式
            url:"/DelNoteServlet?nid="+encodeURI(encodeURI(nid)),
            timeout:30000,//超时时间：30秒
            dataType:"json",//设置返回数据的格式
            //请求成功后的回调函数 data为json格式
            success:function(data){
                alert(data.message);
                getAllNotes();
                newNote();
            },
            //请求出错的处理
            error:function(){
                alert("删除出错");
            }
        });
    }else{

    }

}

//编辑保存笔记
function saveNote(nname, content) {
    var noteNameObj=nname.replace(/</g,"&lt;");
    var contentObj=content;
    console.log(contentObj);
    $.ajax({
        type:"post",//请求方式
        url:"/EditNoteServlet",
        data:{nname:noteNameObj,content:contentObj},
        timeout:30000,//超时时间：30秒
        dataType:"json",//设置返回数据的格式
        //请求成功后的回调函数 data为json格式
        success:function(data){
            getAllNotes();
            printNote(`${data.nid}`);
            alert("修改成功");
        },
        //请求出错的处理
        error:function(){
            alert("修改出错");
        }
    });
}
//添加笔记到数据库
function addNotePost(nname, content){
    var noteNameObj=nname.replace(/</g,"&lt;");
    console.log(noteNameObj);
    var contentObj=content;
    $.ajax({
        type:"post",//请求方式
        url:"/AddNoteServlet",
        data:{nname:noteNameObj,content:contentObj},
        timeout:30000,//超时时间：30秒
        dataType:"json",//设置返回数据的格式
        //请求成功后的回调函数 data为json格式
        success:function(data){
            if(data.message=="1"){
                alert("已经存在同名笔记");
            }
            getAllNotes();
        },
        //请求出错的处理
        error:function(){
            // alert("添加出错");
            getAllNotes();
        }
    });
}

