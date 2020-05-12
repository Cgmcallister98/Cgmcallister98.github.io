var ctx = document.getElementById('Graph1').getContext('2d');
var ctx2 = document.getElementById('Graph2').getContext('2d');
var data = Info;

var Chart1 = new Chart(ctx, {
    type: 'horizontalBar',
    data: {
        labels: [data.arr[0].Year, data.arr[1].Year, data.arr[2].Year, 
            data.arr[3].Year, data.arr[4].Year, data.arr[5].Year, 
            data.arr[6].Year, data.arr[7].Year, data.arr[8].Year, 
            data.arr[9].Year, data.arr[10].Year],
        datasets: [{
            label: 'Gallons per Person per Day',
            data: [data.arr[0].PerCapita, data.arr[1].PerCapita, 
                data.arr[2].PerCapita, data.arr[3].PerCapita, 
                data.arr[4].PerCapita, data.arr[5].PerCapita, 
                data.arr[6].PerCapita, data.arr[7].PerCapita, 
                data.arr[8].PerCapita, data.arr[9].PerCapita, data.arr[10].PerCapita],
            backgroundColor: "aqua",
            borderColor: "blue",
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        title: { display: true,
                text: 'Water Consumption Per Capita In Gallons From The Past 10 Years'
                },   
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

var Chart2 = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: [data.arr[0].Year, data.arr[1].Year, data.arr[2].Year, 
            data.arr[3].Year, data.arr[4].Year, data.arr[5].Year, 
            data.arr[6].Year, data.arr[7].Year, data.arr[8].Year, 
            data.arr[9].Year, data.arr[10].Year],
        datasets: [{
            label: 'Million Gallons Per Day',
            data: [data.arr[0].NYCConsum, data.arr[1].NYCConsum, 
                data.arr[2].NYCConsum, data.arr[3].NYCConsum, 
                data.arr[4].NYCConsum, data.arr[5].NYCConsum, 
                data.arr[6].NYCConsum, data.arr[7].NYCConsum, 
                data.arr[8].NYCConsum, data.arr[9].NYCConsum, data.arr[10].NYCConsum],
            borderColor: "aqua",
            borderWidth: 1,
            fill: false
        }]
    },
    options: {
        responsive: false,
         title: { display: true,
                text: 'City Wide Water Consumption In Million Gallons Per Day From The Past 10 Years'
                },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});