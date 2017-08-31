
var valuesFromJSON=[];
var dateFromJSON=[];
var sensor=[];
$.getJSON("http://localhost:9988/data/bySensor", {
    sessionId: "1"
}).done(function(data) {
    $.each(data,function(k,v) {
        console.log(v);
        var div = document.createElement('div');
        div.id=v.sensor;
        document.body.appendChild(div);
        Highcharts.chart(div.id, {
            title: {
                text: div.id
            },
            xAxis: {
                title: {
                    text: 'Data'
                },
                categories: v.date
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
                data: v.value
            }]
        });

    });

}).fail(function() {
    alert("Could not reload messages!");
});
function replace_ID_and_init_Container() {
    container.setAttribute("id",'b');
    init_container();
}
