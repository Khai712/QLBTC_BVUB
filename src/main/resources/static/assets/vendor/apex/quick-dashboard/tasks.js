var options = {
	chart: {
		height: 320,
		type: 'radialBar',
	},
	plotOptions: {
		radialBar: {
			dataLabels: {
				name: {
					fontSize: '14px',
				},
				value: {
					fontSize: '20px',
				},
				total: {
					show: true,
					label: 2023,
					formatter: function (w) {
						// By default this function returns the average of all series. The below is just an example to show the use of custom formatter function
						return '95%'
					}
				}
			}
		}
	},
	series: [80,85,87],
	labels: [2020,2021,2022],
	colors: ['#1a8e5f', '#262b31', '#434950', '#63686f', '#868a90'],
}

var chart = new ApexCharts(
	document.querySelector("#tasks"),
	options
);
chart.render();