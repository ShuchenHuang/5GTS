<!--  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>   -->
<%! int number=0; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>remoteDetection</title>
    <!--Public解析为/test/public-->
    <link rel="stylesheet" type="text/css" href="Home/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Home/css/telemedicine.css">

    <script type="text/javascript" src="Home/js/echarts.js"></script>
    <script type="text/javascript" src="Home/js/echarts.min.js"></script>

</head>

<body>
<header>
    <!--响应式导航条-->
    <!--固定导航栏-->
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <!-- 响应式对应的下拉键写在brand之前，内容固定 。注意class,data-toggle.
                    data-target是需要隐藏的列表容器的class/id名-->
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#mynav">
                        <!--sr-only屏幕阅读器-->
                        <span class="sr-only">Toggle Navigation</span>
                        <span class="icon-bar" style="background-color: dodgerblue;"></span>
                        <span class="icon-bar" style="background-color: dodgerblue;"></span>
                        <span class="icon-bar" style="background-color: dodgerblue;"></span>
                    </button>
                    <a class="navbar-brand" href="home.html">
                        <img src="Home/img/brand.png" width="100px" />
                        <h3>终端模拟器</h3>
                    </a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="mynav">
                    <ul class="nav navbar-nav">
                        <li name="navcon">
                            <a href="telemedicine.html">远程医疗</a>
                            <!--<p class="line-bottom"></p>-->
                        </li>
                        <li name="navcon">
                            <a href="remoteDetection.jsp">远程监测</a>
                            <!--<p class="line-bottom"></p>-->
                        </li>
                        <li name="navcon">
                            <a href="smartHome.jsp">智能家居</a>
                            <!--<p class="line-bottom"></p>-->
                        </li>
                        <li name="navcon">
                            <a href="ARVR.html">AR/VR</a>
                        </li>
                        <li name="navcon">
                            <a href="index.html">返回首页</a>
                            <!--<p class="line-bottom"></p>-->
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!--轮播图片-->
    <div id="slidershow" class="carousel slide" data-ride="carousel" data-interval="3000">
        <ol class="carousel-indicators">
            <li class="active" data-target="#slidershow" data-slide-to="0"></li>
        </ol>
        <!-- 设置轮播图片 -->

        <div class="carousel-inner">
            <div class="item active">
                <a href="##"><img src="Home/img/carouselthree.jpg" width="100%" alt=""></a>
            </div>
        </div>
        <!-- 设置轮播图片控制器 -->
        <a class="left carousel-control" href="#slidershow" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"="color: lightskyblue;font-size: 50px;"></span>
        </a>
        <a class="right carousel-control" href="#slidershow" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" style="color: lightskyblue;font-size: 50px;"></span>
        </a>
    </div>
</header>
<main>
    <!--业务展示-->
    <div id="serviceem">
        <div class="conheader">
            <h3>远程监测业务展示</h3>
        </div>

        <div class="container">
            <div class="row">

                <div class="col-md-7 datacon">
                    <h4>用户接入数目</h4>
                    <p id="eee"></p>
                    <div id="main" style="width: 600px;height: 400px;"></div>
                </div>

                <div class="col-md-5 description">
                    <form>
                        <p> 请输入总用户数：</p>
                        <input id = "result" onkeyup = "this.value=this.value.replace(/\D/g,'')" onafterpaste = "this.value=this.value.replace(/\D/g,'')" type = "text">
                        <!--  <input type = "button" id = "addButton" value = "确认提交总用户数" onclick = "getResult()">  -->
                        <button class = "btn-info" type="submit" id = "getNumber_btn" onclick = "getResult()">确认提交总用户数</button>
                        <ul id =  "playlist">
                    </form>
                </div>

            </div>
        </div>
    </div>


    <script type="text/javascript" src="Home/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="Home/dist/js/bootstrap.min.js"></script>
    <!-- 放在body中生效 -->
    <script type="text/javascript" src="Home/js/telemedicine.js"></script>
    <script src="Home/js/mychart.js"></script>
    <script src="Home/js/echarts.js"></script>
    <script src="Home/js/smartHome.js"></script>
    <script type="text/javascript">


        $(document).ready(
            function(){
                // getdata();
                setInterval(function () {
                    getdata();
                    // refreshData();
                }, 1000);
            }

        )
        var number=50;
        function getdata(){
            var eve = new Array();
            var v = document.getElementById("result");
            var number = v.value;
            $.ajax({
                url:"/smartHome",
                data:{'count':number},
                type:"GET",
                success:function(result){
                    // $("#frame_getReal_btn").parent().find("textarea").empty();
                    var frames = result.returnData.frames;
                    if(result.code == 100){
                        $.each(frames, function(index, item){
                            eve.push(item);
                        });
                    }

                    console.log(eve);
                    var myChart = echarts.init(document.getElementById("main"));
                    var data1 = new Array();
                    for (i=0;i<200;i++) {
                        data1.push(i);
                    }
                    // document.getElementById("eee").innerHTML='eve';
                    var option = {
                        title:{
                            text:"数据量"
                        },
                        tooltip:{
                            text:"this is tool tip",
                            trigger: 'axis'
                        },
                        legend:{
                            data:['接入数目']
                        },
                        xAxis:{
                            // data:["1","2","3","4","5","6"]
                            data:data1
                        },
                        yAxis:{},
                        dataZoom: [
                            {
                                type: 'slider',
                                show: true,
                                xAxisIndex: [0],
                                start: 1,
                                end: 35
                            },
                            {
                                type: 'inside',
                                xAxisIndex: [0],
                                start: 1,
                                end: 35
                            },
                            {
                                type: 'slider',
                                show: true,
                                yAxisIndex: 0,
                                filterMode: 'empty',
                                width: 12,
                                height: '70%',
                                handleSize: 8,
                                showDataShadow: false,
                                left: '93%'
                            }
                        ],
                        series:[{
                            name:["帧"],
                            type:"line",
                            // data:[926,845,919,824,976,740],
                            data: eve,
                            smooth:true
                        }]
                    };
                    myChart.setOption(option);
                    // alert(eve);
                }
            });


        }


        function getResult() {
            var v = document.getElementById("result");
            number = v.value;
            if (number == ""){
                alert("请输入总用户数");
            }
            else {
                var li = document.createElement("li");
                li.innerHTML = number;
                var ul = document.getElementById("playlist");
                ul.appendChild(li);

            }
            /*var postData={"number":number};
            $.ajax({
                // async : false,
                // cache : false,
                type : 'GET',
                url : 'delete?number='+number,
                dataType : "text",
                contentType : 'application/json',
                error : function() {
                    console.log("fail");
                    alert('请求失败');
                },
                success : function(data) {
                    alert(data);
                }

            });*/

        }
        /*                $("#frame_getReal_btn").click(function(){
                            alert(eve) ;
                        });*/

    </script>

</body>

</html>