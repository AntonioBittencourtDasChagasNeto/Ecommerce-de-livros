<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ranking de Cliente</title>
<style>
table {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
	margin: 5px;
}

table td, table th {
	border: 1px solid #ddd;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

table tr:hover {
	background-color: #ddd;
}

table th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	color: white;
	background-color: #ef2b2b;
}

.input1 {
	width: 100px;
	border: none;
	background-color: transparent;
}

.input {
	border: none;
	font-size: 15px;
	background-color: transparent;
}

#grafico {
	min-width: 310px;
	height: 400px;
	margin: 0px auto;
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
		Highcharts.chart('container', {
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: 'Ranking dos clientes'
		    },
		    xAxis: {
		        type: 'category',
		        labels: {
		            rotation: -45,
		            style: {
		                fontSize: '13px',
		                fontFamily: 'Verdana, sans-serif'
		            }
		        }
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: 'Livros (unidades)'
		        }
		    },
		    legend: {
		        enabled: false
		    },
		    tooltip: {
		        pointFormat: 'Compras em 2017: <b>{point.y:.1f} unidades</b>'
		    },
		    series: [{
		        name: 'Unidade(s)',
		        data: [
		            ['Alvaro Da Silva', 50],
		            ['Joana Tavares', 42],
		            ['Cecillia Pires', 38],
		            ['Pedro Costa', 33],
		            ['Roberto Telles', 14],
		            ['Você', 5],
		        ],
		        dataLabels: {
		            enabled: true,
		            rotation: -90,
		            color: '#FFFFFF',
		            align: 'right',
		            format: '{point.y:.1f}', // one decimal
		            y: 10, // 10 pixels down from the top
		            style: {
		                fontSize: '13px',
		                fontFamily: 'Verdana, sans-serif'
		            }
		        }
		    }]
		});
    });
			</script>
</head>

<body>
	<jsp:include page="MenuCli.jsp" />
	<!-- p>Todays Date: <%= new java.util.Date() %></p -->

	<div class="col-md-12">

		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/modules/export-data.js"></script>

		<div id="container"
			style="min-width: 300px; height: 400px; margin: 0 auto"></div>

		<table>
			<tr>
				<th>Posição</th>
				<th>Nome</th>
				<th>Gênero Preferencial</th>
				<th>Qtde Livros Comprados</th>
			</tr>

			<tr>
				<td>1</td>
				<td>Alvaro Da Silva</td>
				<td>Ficção</td>
				<td>50</td>
			</tr>

			<tr>
				<td>2</td>
				<td>Joana Tavares</td>
				<td>Romance</td>
				<td>42</td>
			</tr>

			<tr>
				<td>3</td>
				<td>Cecilia Pires</td>
				<td>Aventura</td>
				<td>38</td>

			</tr>

			<tr>
				<td>4</td>
				<td>Pedro Costa</td>
				<td>Informática</td>
				<td>33</td>

			</tr>

			<tr>
				<td>5</td>
				<td>Roberto Telles</td>
				<td>Ação</td>
				<td>14</td>

			</tr>

			<tr>
				<td>6</td>
				<td>Você</td>
				<td>Ação</td>
				<td>5</td>

			</tr>


		</table>
	</div>
</body>
</html>

