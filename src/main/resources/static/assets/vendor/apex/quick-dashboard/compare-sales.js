var options = {
	chart: {
		height: 180,
		type: 'line',
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
		name: 'Total Score of Applicable Criteria (385 Score): ',
		data: [250, 300, 320, 357]
	}],
	grid: {
		row: {
			colors: ['#ffffff'], // takes an array which will be repeated on columns
			opacity: 0.5,
		},
		xaxis: {
      lines: {
        show: false
      }
    },   
    yaxis: {
      lines: {
        show: false
      }
    },
	},
	xaxis: {
		categories: [2020,2021,2022,2023],
		// labels: {
	 //    show: false
	 //  }
	},
	colors: ['#1a8e5f', '#262b31', '#434950', '#63686f', '#868a90'],
	markers: {
		size: 5,
		opacity: 0.2,
		colors: ["#1a8e5f"],
		strokeColor: "#ffffff",
		strokeWidth: 2,
		hover: {
			size: 7,
		}
	},
	tooltip: {
		y: {
			formatter: function(val) {
				return  val
			}
		}
	},
}

var chart = new ApexCharts(
	document.querySelector("#compare-sales"),
	options
);

chart.render();
