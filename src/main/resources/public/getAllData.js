
var valuesFromJSON=[];
var dateFromJSON=[];
var sensor=[];
$.getJSON("http://".concat(self.location.host.concat("/data/bySensor")), {
    sessionId: "1"
}).done(function(data) {
    $.each(data,function(k,v) {
        console.log(v);
        var div = document.createElement('div');
        div.id=v.sensor;
        document.body.appendChild(div);


        datavalue = [];
        for (elem in v.dateValue) {
            datavalue.push([v.dateValue[elem].date,v.dateValue[elem].value]);
        }
        console.log(datavalue);

        Highcharts.stockChart(div.id, {
            title: {
                text: div.id
            },
            tooltip: {
                valueSuffix: '°C'
            },
            rangeSelector: {

                buttons: [{
                    type: 'day',
                    count: 1,
                    text: '1d'
                },{
                    type: 'week',
                    count: 1,
                    text: '1w'
                },{
                    type: 'month',
                    count: 1,
                    text: '1m'
                }, {
                    type: 'month',
                    count: 3,
                    text: '3m'
                }, {
                    type: 'month',
                    count: 6,
                    text: '6m'
                }, {
                    type: 'ytd',
                    text: 'YTD'
                }, {
                    type: 'year',
                    count: 1,
                    text: '1y'
                }, {
                    type: 'all',
                    text: 'All'
                }]
            },

            xAxis: {
                ordinal: false
            },

            series: [{
                name: 'Temperature',
                data: datavalue,
                type: 'column',
                tooltip: {
                    valueDecimals: 2
                }
            }]
        });

    });

}).fail(function() {
    alert("Could not reload messages!");
});

