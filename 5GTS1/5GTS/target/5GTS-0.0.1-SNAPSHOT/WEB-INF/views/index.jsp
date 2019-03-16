<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Telemedicine</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>


<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${APP_PATH }/Home/css/telemedicine.css">



</head>

<body>

	<header> <!--"响应式导航条--> <!--固定导航栏-->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="row">
				<div class="navbar-header">
					<!-- 响应式对应的下拉键写在brand之前，内容固定 。注意class,data-toggle.
							data-target是需要隐藏的列表容器的class/id名-->
					<button class="navbar-toggle" type="button" data-toggle="collapse"
						data-target="#mynav">
						<!--sr-only屏幕阅读器-->
						<span class="sr-only">Toggle Navigation</span> <span
							class="icon-bar" style="background-color: dodgerblue;"></span> <span
							class="icon-bar" style="background-color: dodgerblue;"></span> <span
							class="icon-bar" style="background-color: dodgerblue;"></span>
					</button>
					<a class="navbar-brand" href="home.html"> <img
                            src="../../Home/img/brand.png" width="100px" />
						<h3>终端模拟器</h3>
					</a>
				</div>
				<div class="collapse navbar-collapse navbar-right" id="mynav">
					<ul class="nav navbar-nav">
						<li name="navcon"><a href="#serviceem">业务展示</a> <!--<p class="line-bottom"></p>-->
						</li>
						<li name="navcon"><a href="#simupara">模拟参数</a> <!--<p class="line-bottom"></p>-->
						</li>
						<li name="navcon"><a href="#monitor">性能监测</a> <!--<p class="line-bottom"></p>-->
						</li>
						<li name="navcon"><a href="#">返回首页</a> <!--<p class="line-bottom"></p>-->
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!--轮播图片-->
	<div id="slidershow" class="carousel slide" data-ride="carousel"
		data-interval="3000">
		<ol class="carousel-indicators">
			<li class="active" data-target="#slidershow" data-slide-to="0"></li>
			<li data-target="#slidershow" data-slide-to="1"></li>
		</ol>
		<!-- 设置轮播图片 -->

		<div class="carousel-inner">
			<div class="item active">
				<a href="##"><img src="../../Home/img/carouselone.jpg" width="100%"
                                  alt=""></a>
			</div>
			<div class="item">
				<a href="##"><img src="../../Home/img/carouseltwo.jpg" width="100%"
                                  alt=""></a>
				<div class="carousel-caption"></div>
			</div>
		</div>
		<!-- 设置轮播图片控制器 -->
		<a class="left carousel-control" href="#slidershow" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"="color:lightskyblue;font-size: 50px;"></span>
		</a> <a class="right carousel-control" href="#slidershow" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"
			style="color: lightskyblue;font-size: 50px;"></span>
		</a>
	</div>
	</header>
	<main> <!--说明栏，说明三个版块的主要功能-->
	<div id="explain">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<a href="#serviceem"><img src="../../Home/icon/iconone.png" /></a>
					<h4>业务展示</h4>
					<p>
						远程会诊业务界面<br />展示远程医疗会诊主要组成部分
					</p>
				</div>
				<div class="col-md-4">
					<a href="#simupara"><img src="../../Home/icon/icontwo.png" /></a>
					<h4>模拟参数</h4>
					<p>
						5G终端模拟远程医疗场景<br />模拟终端传输数据的模拟参数以表格形式呈现
					</p>
				</div>
				<div class="col-md-4">
					<a href="#monitor"><img src="../../Home/icon/iconthree.png" /></a>
					<h4>性能监测</h4>
					<p>
						实时监测数据传输性能<br />将实时性能监测数据与5G传输标准比较
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--<img src="img/test1.jpg" width="100%" />--> <!--业务展示-->
	<div id="serviceem">
		<div class="conheader">
			<h3>业务展示</h3>
		</div>

		<div class="container">
			<div class="row">

				<div class="col-md-6">
					<h4>视频通话</h4>
					<video width="100%" autoplay="autoplay" loop="loop"> <source
						src="../../Home/img/myvideo.mp4"></source> </video>

					<!--视频通话-->
				</div>
				<div class="col-md-6">
					<h4>诊断结果</h4>
						<textarea id="result" rows="12">显示帧序列...</textarea>
						<button class="btn-info" type="submit" id="frame_getReal_btn">获取真实帧序列</button>	
						<button class="btn-info" type="submit" id="frame_getSimulated_btn">获取模拟帧序列</button>	
						<button class="btn-info" type="submit" id="frame_getmap_btn">获取概率分布map</button>	
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<h4>诊断图片</h4>
					<div class="piccon">
						<div class="container">
							<div class="row">
								<div class="col-sm-6">
									<img src="../../Home/img/ct1.jpg" />
									<h5>图一</h5>
								</div>
								<div class="col-sm-6">

									<img src="../../Home/img/ct2.jpg" />
									<h5>图二</h5>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<img src="../../Home/img/ct3.jpg" />
									<h5>图三</h5>
								</div>
								<div class="col-sm-6">
									<img src="../../Home/img/ct4.jpg" />
									<h5>图四</h5>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<img src="../../Home/img/ct5.jpg" />
									<h5>图五</h5>
								</div>
								<div class="col-sm-6">
									<img src="../../Home/img/ct6.jpg" />
									<h5>图六</h5>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-6">
					<h4>历史病历</h4>
					<div class="listcon"
						style="width: 100%;height: 600px;overflow: scroll;">
						<img src="../../Home/img/list.jpg" width="100%" />
					</div>


				</div>
			</div>


		</div>
		<!--查看具体历史数据传输前后弹出的modal对话框-->
		<div class="modal fade" id="datamodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">数据详情:</div>
					<div class="modal-body">
						<div class="tableinfo">
							<span class="rt"></span> 正确 <span class="wr"></span> 错误
						</div>
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>数据编号</th>
										<th>用户ID</th>
										<th>收缩压(mmHg)</th>
										<th>舒张压(mmHg)</th>
										<th>血氧(%)</th>
										<th>额温(℃)</th>
										<th>心率(次/分)</th>
									</tr>
								</thead>
								<tbody>
									<tr class="info">
										<td>传输前</td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>
										<td class="tdbefore"></td>

									</tr>
									<tr class="info">
										<td>传输后</td>
										<td class="tdafter"></td>
										<td class="tdafter"></td>
										<td class="tdafter"><span class="wr"></span></td>
										<td class="tdafter"></td>
										<td class="tdafter"></td>
										<td class="tdafter"></td>
										<td class="tdafter"></td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>
					<div class="modal-footer">
						<button class="btn btn-info" type="button" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模拟参数-->
	<div id="simupara">
		<div class="conheader">
			<h3>模拟参数</h3>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-5 description">
					<img src="../../Home/icon/icontwo.png" width="60px"
                         style="float: left;margin-right: 20px;" />
					<div>
						<h4>5G终端模拟远程医疗场景</h4>
						<p>传输数据模拟参数记录</p>
					</div>
				</div>
				<div class="col-md-7 datacon"
					style="height: 500px;overflow: scroll;">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>数据编号</th>
									<th>业务类型</th>
									<th>数据包大小(字节)</th>
									<th>发送频率(HZ)</th>
									<th>路数</th>
								</tr>
							</thead>
							<tbody id="showsimupara">								
								<tr class="info">
									<td>1</td>
									<td>远程医疗</td>									
								</tr>

							</tbody>
						</table>
					</div>
					<button class="btn btn-info btn-sm" type="button" id="moresimupara">查看更多</button>
				</div>
			</div>
		</div>

	</div>
	<!--性能检测-->
	<div id="monitor">
		<div class="conheader">
			<h3>性能监测</h3>
		</div>
		<!--选项卡-->
		<div class="container">
			<div class="row">
				<ul id="monitortab" class="nav nav-tabs">
					<li class="active"><a href="#delay" data-toggle="tab">端到端时延</a>
					</li>
					<li><a href="#transspeed" data-toggle="tab">信息传输速率</a></li>
					<li><a href="#linknum" data-toggle="tab">连接数</a></li>
					<li><a href="#biter" data-toggle="tab">误码率</a></li>
					<li><a href="#blocker" data-toggle="tab">误块率</a></li>
				</ul>
				<div id="tabcontent" class="tab-content">
					<div class="tab-pane fade in active" id="delay">
						<div id="delaychart" style="min-width:400px;height: 400px ;"></div>
					</div>
					<div class="tab-pane fade" id="transspeed">
						<div id="speedchart" style="min-width:400px;height: 400px ;"></div>
					</div>
					<div class="tab-pane fade" id="linknum">
						<div id="linknumchart" style="min-width:400px;height: 400px ;"></div>
					</div>
					<div class="tab-pane fade" id="biter">
						<div id="biterchart" style="min-width:400px;height: 400px ;"></div>
					</div>
					<div class="tab-pane fade" id="blocker">
						<div id="blockerchart" style="min-width:400px;height: 400px ;"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	</main>
	
	<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<!-- 放在body中生效 -->

	
	<script type="text/javascript">	
	$("#frame_getReal_btn").click(function(){
		$.ajax({
			url:"${APP_PATH}/frames",
			data:"count=200",
			type:"GET",
			success:function(result){
				$("#frame_getReal_btn").parent().find("textarea").empty();
				var frames = result.returnData.frames;
				if(result.code == 100){
					$.each(frames, function(index, item){
						$("#frame_getReal_btn").parent().find("textarea").append(item.pRealFrame1).append(" ");											
					});
				}
			}
		});
	});
	$("#frame_getSimulated_btn").click(function(){
		$.ajax({
			url:"${APP_PATH}/simulatedFrames",
			data:"count=200",
			type:"GET",
			success:function(result){
				$("#frame_getSimulated_btn").parent().find("textarea").empty();
				var frames = result.returnData.frames;
				if(result.code == 100){		
					$.each(frames, function(index, item){
						$("#frame_getSimulated_btn").parent().find("textarea").append(item).append(" ");									
					});			
				}
			}
		});
	});
	
	$("#frame_getmap_btn").click(function(){
		$.ajax({
			url:"${APP_PATH}/map",
			data:"intervalCount=25",
			type:"GET",
			success:function(result){
				$("#frame_getSimulated_btn").parent().find("textarea").empty();
				var xSimulation = result.returnData.xSimulation;
				var ySimulation = result.returnData.ySimulation;
				var xReal = result.returnData.xReal;
				var yReal = result.returnData.yReal;
				if(result.code == 100){		
					$.each(xSimulation, function(index, item){
						$("#frame_getSimulated_btn").parent().find("textarea").append(item).append(" ");									
					});
					$("#frame_getSimulated_btn").parent().find("textarea").append('\n');			
					$.each(ySimulation, function(index, item){
						$("#frame_getSimulated_btn").parent().find("textarea").append(item).append(" ");									
					});	
					$("#frame_getSimulated_btn").parent().find("textarea").append('\n');	
					$.each(xReal, function(index, item){
						$("#frame_getSimulated_btn").parent().find("textarea").append(item).append(" ");									
					});
					$("#frame_getSimulated_btn").parent().find("textarea").append('\n');
					$.each(yReal, function(index, item){
						$("#frame_getSimulated_btn").parent().find("textarea").append(item).append(" ");									
					});
				}
			}
		});
	});
	
	
	</script>

</body>
</html>
