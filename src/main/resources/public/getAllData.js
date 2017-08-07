



var valuesFromJSON=[];
var dateFromJSON=[];
var sensor=[];
$.getJSON("http://localhost:9988/data", {
    sessionId: "1"
}).done(function(data) {
    $.each(data,function(k,v) {
        valuesFromJSON.push(v.value);
        dateFromJSON.push(v.data)
    });
    console.log(valuesFromJSON);
    Highcharts.chart('container', {
        title: {
            text: 'Solar Employment Growth by Sector, 2010-2016'
        },
        xAxis: {
            title: {
                text: 'Data'
            },
            categories: dateFromJSON
        },
        yAxis: {
            title: {
                text: 'Temperatura'
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },
        series: [{
            name: 'Installation',
            data: valuesFromJSON
        }]
    });
}).fail(function() {
    alert("Could not reload messages!");
});
