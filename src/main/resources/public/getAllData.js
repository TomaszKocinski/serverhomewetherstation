
var valuesFromJSON=[];
var dateFromJSON=[];
var sensor=[];

function showOrHideSensorOnClick(evt, sensor) {
    return function () {

        // Declare all variables
        var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(sensor).style.display = "block";
        document.getElementById(sensor+"button").className += " active";

    };
}

$.getJSON("http://".concat(self.location.host.concat("/data/bySensor")), {
    sessionId: "1"
}).done(function(data) {

    $.each(data,function(k,v) {
        var button = document.createElement("button");
        button.innerHTML = v.sensor;
        button.className="tablinks";
        button.id=v.sensor+"button";
        var tab = document.getElementsByClassName("tab")[0];
        tab.appendChild(button);
        button.addEventListener ("click", showOrHideSensorOnClick(event, v.sensor));
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            var width = 100 / tablinks.length;
            tablinks[i].style.width = width + "%";
        }
    });

    $.each(data,function(k,v) {
        console.log(v);

        tempValue = [];
        pressureValue = [];
        humidityValue = [];
        lightValue = [];
        for (elem in v.dateValue) {
            tempValue.push([v.dateValue[elem].date,v.dateValue[elem].temperature]);
            pressureValue.push([v.dateValue[elem].date,v.dateValue[elem].pressure]);
            humidityValue.push([v.dateValue[elem].date,v.dateValue[elem].humidity]);
            lightValue.push([v.dateValue[elem].date,v.dateValue[elem].light]);
        }
        console.log(tempValue);

        tempValuePred = [];
        pressureValuePred = [];
        humidityValuePred = [];
        lightValuePred = [];

        for (elemHigher of v.predictionValue) {
            for (elem of elemHigher.dateValue) {
                tempValuePred.push([elem.date, elem.temperature]);
                pressureValuePred.push([elem.date, elem.pressure]);
                humidityValuePred.push([elem.date, elem.humidity]);
                lightValuePred.push([elem.date, elem.light]);
            }
        }



        var div = document.createElement('div');
        div.className="tabcontent";
        div.id=v.sensor;
        document.body.appendChild(div);


        var div1 = document.createElement('div');
        div1.id=v.sensor + ' Temperature';
        div.appendChild(div1);
        Highcharts.stockChart(div1.id, {
            title: {
                text: div1.id
            },
            tooltip: {
                valueSuffix: '°C'
            },
            rangeSelector: {
                buttons: [{
                    count: 30,
                    type: 'minute',
                    text: '30M'
                }, {
                    type: 'day',
                    count: 1,
                    text: '1d'
                }, {
                    type: 'month',
                    count: 3,
                    text: '3m'
                }, {
                    type: 'year',
                    count: 1,
                    text: '1y'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                selected: 0,
            },

            xAxis: {
                ordinal: false
            },

            series: [{
                name: 'Temperature',
                data: tempValue,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }
            , {
                name: 'Temperature gausse',
                data: tempValuePred,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }
            ]
        });
        var div2 = document.createElement('div');
        div2.id=v.sensor+' Pressure';
        div.appendChild(div2);
        Highcharts.stockChart(div2.id, {
            title: {
                text: div2.id
            },
            tooltip: {
                valueSuffix: 'hPa'
            },
            rangeSelector: {
                buttons: [{
                    count: 30,
                    type: 'minute',
                    text: '30M'
                }, {
                    type: 'day',
                    count: 1,
                    text: '1d'
                }, {
                    type: 'month',
                    count: 3,
                    text: '3m'
                }, {
                    type: 'year',
                    count: 1,
                    text: '1y'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                selected: 0,
            },

            xAxis: {
                ordinal: false
            },

            series: [{
                name: 'Temperature',
                data: pressureValue,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }, {
                name: 'Pressure gausse',
                data: pressureValuePred,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }
            ]
        });
        var div3 = document.createElement('div');
        div3.id=v.sensor+' Humidity';
        div.appendChild(div3);
        Highcharts.stockChart(div3.id, {
            title: {
                text: div3.id
            },
            tooltip: {
                valueSuffix: '%'
            },
            rangeSelector: {
                buttons: [{
                    count: 30,
                    type: 'minute',
                    text: '30M'
                }, {
                    type: 'day',
                    count: 1,
                    text: '1d'
                }, {
                    type: 'month',
                    count: 3,
                    text: '3m'
                }, {
                    type: 'year',
                    count: 1,
                    text: '1y'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                selected: 0,
            },

            xAxis: {
                ordinal: false
            },

            series: [{
                name: 'Humidity',
                data: humidityValue,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }, {
                name: 'Humidity gausse',
                data: humidityValuePred,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }]
        });
        var div4 = document.createElement('div');
        div4.id=v.sensor+' Light';
        div.appendChild(div4);
        Highcharts.stockChart(div4.id, {
            title: {
                text: div4.id
            },
            tooltip: {
                valueSuffix: 'Lumen'
            },
            rangeSelector: {
                buttons: [{
                    count: 30,
                    type: 'minute',
                    text: '30M'
                }, {
                    type: 'day',
                    count: 1,
                    text: '1d'
                }, {
                    type: 'month',
                    count: 3,
                    text: '3m'
                }, {
                    type: 'year',
                    count: 1,
                    text: '1y'
                }, {
                    type: 'all',
                    text: 'All'
                }],
                selected: 0,
            },

            xAxis: {
                ordinal: false
            },

            series: [{
                name: 'Light',
                data: lightValue,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }, {
                name: 'Light gausse',
                data: lightValuePred,
                type: 'line',
                tooltip: {
                    valueDecimals: 2
                }
            }]
        });

    });

}).fail(function() {
    alert("Could not reload messages!");
});

