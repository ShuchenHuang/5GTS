$(function(){

        

        // $("li[name='navcon']").mouseover(function(){
        //     $(this).style("color","black");
        //     // $(this).addClass("active").siblings().removeClass("active");
        //     // $(this).siblings().children(":last-child").css("display","none");
        //     $(this).children(":last-child").css("display","block");


        // })
//      $("#clickinfo").click(function(){
//      	$("#datamodal").modal();
//      })
//       

        $("#moredata").click(function(){
//			取得目前最后一条被加载的数据的id,传递到控制器index下的moredata,取得从stratid以后的十条数据并显示
            var startid = parseInt($(".dataid:last").html());
//			post异步加载数据
            $.post("index.php/index/moredata",{startid:startid},function(data){

                data = $.parseJSON(data);
                
               
                $.each(data,function(i,record){
//              	添加表格行数,内容为新加载获得的数据
                    var newrow="<tr class='info' class='datarow'>"+
                            "<td class='dataid'>"+record.dataid+"</td><td>"+
                            record.userid+"</td><td>"+record.sbp+
                            "</td><td>"+record.dbp+"</td><td>"+record.spo2+"</td><td>"+
                            record.temperature+"</td><td>"+record.heartrate+
                            "</td><td class='datacompare'>查看</td></tr>";
                    $(newrow).appendTo($("#showdata"));

                });
                modalclick();
            });
            
            
            // $(".datacompare").on('click',function(){
            //     alert("hu");
            // });


            
        })
        $("#moresimupara").click(function(){
//			取得目前最后一条被加载的数据的id,传递到控制器index下的moresimupara,取得从stratid以后的十条数据并显示
            var startid = parseInt($(".simudataid:last").html());

            $.post("index.php/index/moresimupara",{startid:startid},function(data){

                data = $.parseJSON(data);
                
               
                $.each(data,function(i,record){
                    var newrow="<tr class='info'>"+
                            "<td class='simudataid'>"+record.dataid+"</td><td>"+
                            "远程医疗"+"</td><td>"+record.datapacket+
                            "</td><td>"+record.transfreq+"</td><td>"+record.sendnum+"</td>";
                    $(newrow).appendTo($("#showsimupara"));

                });
                
            });
            
            
            // $(".datacompare").on('click',function(){
            //     alert("hu");
            // });


            
        })
        modalclick();
        

    })
function modalclick()
{
     $(".datacompare").click(function(){
//          取到当前点击数据的id,传递到控制器index下的datacompare,获得传输前后的数据
            var myid=parseInt($(this).parent().children(":first").html());
            $.post("index.php/index/datacompare",{dataid:myid},
                function(data){
                var databefore = $.parseJSON(data.databefore);
                var dataafter = $.parseJSON(data.dataafter);
                modaldata(databefore,dataafter);
            },"json")
        })
}
function modaldata(databefore,dataafter)
{
//	将查询到的数据写到modal中的表格中
    var modaldatabefore = document.getElementsByClassName("tdbefore");
    modaldatabefore[0].innerHTML=databefore.dataid;
    modaldatabefore[1].innerHTML=databefore.userid;
    modaldatabefore[2].innerHTML=databefore.sbp;
    modaldatabefore[3].innerHTML=databefore.dbp;
    modaldatabefore[4].innerHTML=databefore.spo2;
    modaldatabefore[5].innerHTML=databefore.temperature;
    modaldatabefore[6].innerHTML=databefore.heartrate;
    var modaldataafter = document.getElementsByClassName("tdafter");
    modaldataafter[0].innerHTML=dataafter.dataid;
    modaldataafter[1].innerHTML=dataafter.userid;
    modaldataafter[2].innerHTML=dataafter.sbp;
    modaldataafter[3].innerHTML=dataafter.dbp;
    modaldataafter[4].innerHTML=dataafter.spo2;
    modaldataafter[5].innerHTML=dataafter.temperature;
    modaldataafter[6].innerHTML=dataafter.heartrate;
   
//	如果传输前后数据不一致,用红色圆点标出
    for (var i = 0; i < 7; i++) {
        if (modaldataafter[i].innerHTML !== modaldatabefore[i].innerHTML) {
            modaldataafter[i].innerHTML='<span class="wr"></span>';
        }
    }
//  调用modal
    $("#datamodal").modal();
}