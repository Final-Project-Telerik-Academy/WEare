/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 99.4335397180872, "KoPercent": 0.5664602819127915};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.39639046238967196, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.39895763281775387, 500, 1500, "Log In User-1"], "isController": false}, {"data": [0.3340618695359785, 500, 1500, "Log In User-0"], "isController": false}, {"data": [0.31368330464716004, 500, 1500, "Create Comment"], "isController": false}, {"data": [0.3337696335078534, 500, 1500, "Create a Post"], "isController": false}, {"data": [0.3446229687785284, 500, 1500, "Register User"], "isController": false}, {"data": [0.179724277067922, 500, 1500, "Log In User"], "isController": false}, {"data": [0.6525205761316872, 500, 1500, "Logout User-1"], "isController": false}, {"data": [0.6486625514403292, 500, 1500, "Logout User-0"], "isController": false}, {"data": [0.06196581196581197, 500, 1500, "Browse Created Public Posts"], "isController": false}, {"data": [0.5833333333333334, 500, 1500, "Logout User"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 22773, 129, 0.5664602819127915, 2793.08264172485, 0, 32185, 1271.0, 8167.800000000003, 10515.95, 15758.87000000002, 122.80257112659348, 1213.813204938216, 55.033033683686185], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Log In User-1", 2974, 0, 0.0, 2297.395090786818, 3, 20059, 1247.0, 6513.5, 8516.0, 10933.25, 16.114350115953965, 446.92778927872837, 3.5744359096967857], "isController": false}, {"data": ["Log In User-0", 2974, 0, 0.0, 2763.6839273705355, 65, 19631, 1510.0, 6816.0, 8542.25, 11000.5, 16.051814869788153, 6.460550617325597, 10.337705353528538], "isController": false}, {"data": ["Create Comment", 1162, 89, 7.6592082616179, 2751.862306368337, 19, 16093, 1446.5, 6947.700000000001, 9339.449999999992, 10993.659999999998, 6.298307804047828, 2.98670049059861, 2.5809718913623207], "isController": false}, {"data": ["Create a Post", 1146, 24, 2.094240837696335, 2721.2966841186685, 3, 14031, 1438.5, 7073.399999999998, 9661.599999999933, 11323.529999999995, 6.219236217011185, 3.6758131118838424, 2.51700932783678], "isController": false}, {"data": ["Register User", 5477, 0, 0.0, 2770.254701478913, 86, 18446, 1386.0, 7074.999999999999, 10110.999999999995, 11400.72000000007, 29.614850141396445, 11.013409977709108, 12.32024039085438], "isController": false}, {"data": ["Log In User", 2974, 0, 0.0, 5061.175184936106, 100, 32185, 2876.5, 13212.5, 16750.75, 20587.25, 16.04835010657529, 451.55645033456545, 13.895269962631195], "isController": false}, {"data": ["Logout User-1", 1944, 0, 0.0, 1313.2700617283945, 2, 8003, 67.5, 4475.5, 5782.5, 7359.849999999999, 10.549965810295985, 112.32063442760737, 2.4623455358015045], "isController": false}, {"data": ["Logout User-0", 1944, 0, 0.0, 1355.1332304526743, 0, 8073, 71.0, 4844.0, 5896.0, 7859.55, 10.549679548056938, 3.5955450803436246, 2.4004641940402993], "isController": false}, {"data": ["Browse Created Public Posts", 234, 16, 6.837606837606837, 7006.358974358975, 42, 21711, 7765.5, 14056.0, 14671.0, 17338.4, 3.2974465926385212, 165.57636178203032, 0.6330509571754692], "isController": false}, {"data": ["Logout User", 1944, 0, 0.0, 2668.4506172839583, 2, 14146, 138.5, 8786.0, 11301.25, 13880.55, 10.549565047511031, 115.91187374676431, 4.862690139087116], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Test failed: text expected to contain /postId/", 16, 12.4031007751938, 0.07025863961709042], "isController": false}, {"data": ["400", 13, 10.077519379844961, 0.05708514468888596], "isController": false}, {"data": ["500", 100, 77.51937984496124, 0.4391164976068151], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 22773, 129, "500", 100, "Test failed: text expected to contain /postId/", 16, "400", 13, "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Create Comment", 1162, 89, "500", 76, "400", 13, "", "", "", "", "", ""], "isController": false}, {"data": ["Create a Post", 1146, 24, "500", 24, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Browse Created Public Posts", 234, 16, "Test failed: text expected to contain /postId/", 16, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
