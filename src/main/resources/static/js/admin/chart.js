// https://cobook.tistory.com/36, https://cobook.tistory.com/41
// https://development-crow.tistory.com/11
// https://bright-landscape.tistory.com/203
// https://blog.naver.com/PostView.naver?blogId=pastelkun&logNo=221713137591 방문자 수
// DB는 이거 참고 해보자..
// 지금은 그냥 샘플..(https://canvasjs.com/javascript-charts/solid-dashed-line-chart/)
window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        theme: "light2",
        title: {
            text: "실종 동물"
        },
        axisX: {
            valueFormatString: "MM DD",
            crosshair: {
                enabled: true,
                snapToDataPoint: true
            }
        },
        axisY: {
            // title: "마리",
            crosshair: {
                enabled: true
            }
        },
        toolTip: {
            shared: true
        },
        legend: {
            cursor: "pointer",
            verticalAlign: "bottom",
            horizontalAlign: "left",
            dockInsidePlotArea: true,
            itemclick: toogleDataSeries
        },
        data: [{
            type: "line",
            showInLegend: true,
            name: "강아지",
            markerType: "square",
            xValueFormatString: "MM DD, YYYY",
            color: "#1E7EC2",
            dataPoints: [
                {x: new Date(2023, 0, 3), y: 5},
                {x: new Date(2023, 0, 4), y: 7},
                {x: new Date(2023, 0, 5), y: 7},
                {x: new Date(2023, 0, 6), y: 6},
                {x: new Date(2023, 0, 7), y: 7},
                {x: new Date(2023, 0, 8), y: 9},
                {x: new Date(2023, 0, 9), y: 8},
                {x: new Date(2023, 0, 10), y: 8},
                {x: new Date(2023, 0, 11), y: 8},
                {x: new Date(2023, 0, 12), y: 4},
                {x: new Date(2023, 0, 13), y: 7},
                {x: new Date(2023, 0, 14), y: 6},
                {x: new Date(2023, 0, 15), y: 8},
                {x: new Date(2023, 0, 16), y: 3}
            ]
        },
            {
                type: "line",
                showInLegend: true,
                name: "고양이",
                lineDashType: "dash",
                color: "#FCC71A",
                dataPoints: [
                    {x: new Date(2023, 0, 3), y: 1},
                    {x: new Date(2023, 0, 4), y: 6},
                    {x: new Date(2023, 0, 5), y: 4},
                    {x: new Date(2023, 0, 6), y: 5},
                    {x: new Date(2023, 0, 7), y: 4},
                    {x: new Date(2023, 0, 8), y: 6},
                    {x: new Date(2023, 0, 9), y: 6},
                    {x: new Date(2023, 0, 10), y: 3},
                    {x: new Date(2023, 0, 11), y: 9},
                    {x: new Date(2023, 0, 12), y: 7},
                    {x: new Date(2023, 0, 13), y: 6},
                    {x: new Date(2023, 0, 14), y: 2},
                    {x: new Date(2023, 0, 15), y: 3},
                    {x: new Date(2023, 0, 16), y: 7}
                ]
            }]
    });
    chart.render();

    function toogleDataSeries(e) {
        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        chart.render();
    }
}