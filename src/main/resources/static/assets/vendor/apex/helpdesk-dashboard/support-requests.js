var options = {
	chart: {
		height: 150,
		type: 'area',
		toolbar: {
			show: false,
		},
	},
	dataLabels: {
		enabled: false
	},
	stroke: {
		curve: 'smooth',
		width: 3
	},
	series: [{
		name: 'Overall Average Score of Criteria',
		data: [4.2,4.25,4.3,4.48]
	}],
	grid: {
		row: {
			colors: ['#ffffff'], // takes an array which will be repeated on columns
			opacity: 0.5
		},
		padding: {
			left: 10,
			right: 10,
		},		
	},
	xaxis: {
		categories: [2020,2021,2022,2023],
	},
	yaxis: {
		labels: {
			show: false,
		}
	},
	theme: {
		monochrome: {
			enabled: true,
			color: '#1a8e5f',
			shadeIntensity: 0.1
		},
	},
	markers: {
		size: 0,
		opacity: 0.2,
		colors: ["#1a8e5f"],
		strokeColor: "#fff",
		strokeWidth: 2,
		hover: {
			size: 7,
		}
	},
	tooltip: {
		x: {
			format: 'dd/MM/yy'
		},
	}
}

var chart = new ApexCharts(
	document.querySelector("#support-requests"),
	options
);

chart.render();
