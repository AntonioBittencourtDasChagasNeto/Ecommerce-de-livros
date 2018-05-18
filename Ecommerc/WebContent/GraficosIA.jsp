<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#grafico {
	min-width: 310px;
	max-width: 800px;
	height: 400px;
	margin: 0 auto
}

.menu {
	width: 1600px;
	height: 50px;
	background-color: #0411f7;
	margin-top: -16px;
	margin-left: -8px;
}

.menu ul {
	font: 16px arial;
	list-style: none;
	position: relative;
}

.menu ul li {
	width: 150px;
	float: left;
}

.menu a {
	padding: 15px;
	display: block;
	text-decoration: none;
	text-align: center;
	background-color: blue;
	color: white;
}

.menu ul ul {
	position: absolute;
	visibility: hidden;
}

.menu ul li:hover ul {
	visibility: visible;
}

.menu a:hover {
	background-color: #6a71f2;
	color: #555;
}

.menu ul ul li {
	float: none;
	border-bottom: solid 1px #ccc;
}

.menu ul ul li a {
	background-color: #6a71f2;
}
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
$(function () {
	Highcharts.chart('grafico', {

		  title: {
		    text: 'Livros vendidos entre, 2010-2017'
		  },

		  subtitle: {
		    text: 'Fonte: ECL.com'
		  },

		  yAxis: {
		    title: {
		      text: 'Número de livros vendidos'
		    }
		  },
		  legend: {
		    layout: 'vertical',
		    align: 'right',
		    verticalAlign: 'middle'
		  },

		  plotOptions: {
		    series: {
		      label: {
		        connectorAllowed: false
		      },
		      pointStart: 2010
		    }
		  },

		  series: [{
		    name: 'Aventura',
		    data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
		  }, {
		    name: 'Drama',
		    data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
		  }, {
		    name: 'Romance',
		    data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
		  }, {
			name: 'Psicologia',
			data: [2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000]
		  }, {
		    name: 'Informática',
		    data: [3455, 4566, 7988, 12169, 15112, 22452, 34400, 34227]
		  }, {
		    name: 'Outros',
		    data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
		  }],

		  responsive: {
		    rules: [{
		      condition: {
		        maxWidth: 500
		      },
		      chartOptions: {
		        legend: {
		          layout: 'horizontal',
		          align: 'center',
		          verticalAlign: 'bottom'
		        }
		      }
		    }]
		  }

		});
});
</script>
<script type="text/javascript">
$(function () {
	// Radialize the colors
	Highcharts.setOptions({
	    colors: Highcharts.map(Highcharts.getOptions().colors, function (color) {
	        return {
	            radialGradient: {
	                cx: 0.5,
	                cy: 0.3,
	                r: 0.7
	            },
	            stops: [
	                [0, color],
	                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	            ]
	        };
	    })
	});

	// Build the chart
	Highcharts.chart('grafico2', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Livros vendidos em, 2018'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                style: {
	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                },
	                connectorColor: 'silver'
	            }
	        }
	    },
	    series: [{
	        name: 'Share',
	        data: [
	            { name: 'Aventura', y: 61.41 },
	            { name: 'Romance', y: 11.84 },
	            { name: 'Drama', y: 10.85 },
	            { name: 'Psicologia', y: 4.67 },
	            { name: 'Informática', y: 4.18 },
	            { name: 'Outros', y: 7.05 }
	        ]
	    }]
	});
});

</script>
</head>
<body>




	<jsp:include page="Menu.jsp" />
	<div id="grafico"></div>
	<div id="grafico2"
		style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
</body>
</html>