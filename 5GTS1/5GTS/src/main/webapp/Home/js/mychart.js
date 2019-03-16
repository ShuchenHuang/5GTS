$(function() {
	datatrans();

		
	
	
})
function datatrans()
	{
		
		$.post("index.php/index/chartdata",function(data){
			var delayTmp = "";
			var speedTmp = "";
			var linknumTmp = "";
			var bitTmp = "";
			var blockTmp = "";
			data = $.parseJSON(data);
			$.each(data,function(i,record){
				delayTmp += record.delay+",";
				speedTmp += record.transspeed+",";
				linknumTmp += record.linknum+",";
				bitTmp += record.biterror+",";
				blockTmp += record.blockerror+",";

			});
			var dataTmp = new Array(delayTmp,speedTmp,linknumTmp,bitTmp,blockTmp);
			for (var i = 0; i < 5; i++) {
				dataTmp[i] = dataTmp[i].substring(0,dataTmp[i].length-1);
				dataTmp[i] = "["+dataTmp[i]+"]";
			}

			
			// dataTmp = dataTmp.substring(0,dataTmp.length-1);
			// dataTmp = "[" + dataTmp +"]";
			chartmake(dataTmp);
			// chartmake();
			
		});
		
	}
function chartmake(dataTmp)
	{
		var chart = new Highcharts.Chart('delaychart', {
		title: {
			text: '端到端时延',
			x: -20,
			style: {
				color: '#4876FF',
				fontFamily: '微软雅黑',
				fontSize: '20px'
			}
		},
		xAxis: {
			categories: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00', '24:00'],
			title: {
				text: '时间',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			}

		},
		yAxis: {
			title: {
				text: '端到端时延 (ms)',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			valueSuffix: 'ms'
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: [{
			name: '实际监测数据',
			data: eval(dataTmp[0]),
			color: 'green'
		}, {
			name: '5G标准',
			data: [8.0, 8.0, 8.0, 8.0, 8.0, 8.0, 8.0]
		}]
	});
		var chart = new Highcharts.Chart('speedchart', {
		title: {
			text: '信息传输速率',
			x: -20,
			style: {
				color: '#4876FF',
				fontFamily: '微软雅黑',
				fontSize: '20px'
			}
		},
		xAxis: {
			categories: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00', '24:00'],
			title: {
				text: '时间',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			}

		},
		yAxis: {
			title: {
				text: '信息传输速率 (bps)',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			valueSuffix: 'bps'
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: [{
			name: '实际监测数据',
			data: eval(dataTmp[1]),
			color: 'green'
		}, {
			name: '5G标准',
			data: [300, 300, 300, 300, 300, 300, 300]
		}]
	});
	var chart = new Highcharts.Chart('linknumchart', {
		title: {
			text: '连接数',
			x: -20,
			style: {
				color: '#4876FF',
				fontFamily: '微软雅黑',
				fontSize: '20px'
			}
		},
		xAxis: {
			categories: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00', '24:00'],
			title: {
				text: '时间',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			}

		},
		yAxis: {
			title: {
				text: '连接数',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			valueSuffix: '路'
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: [{
			name: '实际监测数据',
			data: eval(dataTmp[2]),
			color: 'green'
		}, {
			name: '5G标准',
			data: [20,20,20,20,20,20,20]
		}]
	});
	var chart = new Highcharts.Chart('biterchart', {
		title: {
			text: '误码率',
			x: -20,
			style: {
				color: '#4876FF',
				fontFamily: '微软雅黑',
				fontSize: '20px'
			}
		},
		xAxis: {
			categories: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00', '24:00'],
			title: {
				text: '时间',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			}

		},
		yAxis: {
			title: {
				text: '误码率',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			valueSuffix: ''
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: [{
			name: '实际监测数据',
			data: eval(dataTmp[3]),
			color:'green',
			zones:[{value:0.06,color:'green'},{value:1,color:'red'}]
			
		}, {
			name: '5G标准',
			data: [0.06,0.06,0.06,0.06,0.06,0.06,0.06]
		}]
	});
	var chart = new Highcharts.Chart('blockerchart', {
		title: {
			text: '误块率',
			x: -20,
			style: {
				color: '#4876FF',
				fontFamily: '微软雅黑',
				fontSize: '20px'
			}
		},
		xAxis: {
			categories: ['00:00', '04:00', '08:00', '12:00', '16:00', '20:00', '24:00'],
			title: {
				text: '时间',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			}

		},
		yAxis: {
			title: {
				text: '误块率',
				style: {
					color: '#4876FF',
					fontFamily: '微软雅黑',
					fontSize: '16px'
				}
			},

			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		tooltip: {
			valueSuffix: ''
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: [{
			name: '实际监测数据',
			data: eval(dataTmp[4]),
			color: 'green'
		}, {
			name: '5G标准',
			data: [0.005,0.005,0.005,0.005,0.005,0.005,0.005]
		}]
	});
	}