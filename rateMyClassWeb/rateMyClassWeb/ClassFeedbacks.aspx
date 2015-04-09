<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ClassFeedbacks.aspx.cs" Inherits="rateMyClassWeb.ClassFeedbacks" MasterPageFile="~/Master.master" %>


<asp:Content ID="Content1" ContentPlaceHolderID="Main" runat="Server">

    <a href="Login.aspx"><- Back</a>

    <!--Load the AJAX API-->
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js" type="text/javascript"></script>

    
    z

    <script type="text/javascript">

        // Set a callback to run when the Google Visualization API is loaded.
        google.setOnLoadCallback(drawItems);

        var classid = '<%=Session["class"]%>';

        function drawItems(json) {
            
            // Create our data table out of JSON data loaded from server.
            var piechartdata = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('Main_chart_div'));
            chart.draw(piechartdata, {
                width: 1000,
                height: 563,
                title: 'Overall Class Feedback',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData() {
            var request = new XMLHttpRequest();
            request.onreadystatechange = function () {
                if (request.readyState == 4 && request.status == 200) {
                    drawItems(request.responseText);
                }
            }
            var url = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_class_feedback.php?class=" + classid;
            request.open("GET", url, true);
            request.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData });

        function drawInterestingPie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata2 = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart2 = new google.visualization.PieChart(document.getElementById('Main_interesting_chart'));
            chart2.draw(piechartdata2, {
                width: 334,
                height: 180,
                title: 'Interesting Comments',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData2() {
            var request2 = new XMLHttpRequest();
            request2.onreadystatechange = function () {
                if (request2.readyState == 4 && request2.status == 200) {
                    drawInterestingPie(request2.responseText);
                }
            }
            var url2 = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_interesting_comments.php?class=" + classid;
            request2.open("GET", url2, true);
            request2.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData2 });


        function drawInformativePie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata3 = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart3 = new google.visualization.PieChart(document.getElementById('Main_informative_chart'));
            chart3.draw(piechartdata3, {
                width: 334,
                height: 180,
                title: 'Informative Comments',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData3() {
            var request3 = new XMLHttpRequest();
            request3.onreadystatechange = function () {
                if (request3.readyState == 4 && request3.status == 200) {
                    drawInformativePie(request3.responseText);
                }
            }
            var url3 = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_informative_comments.php?class=" + classid;
            request3.open("GET", url3, true);
            request3.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData3 });

        function drawInteractivePie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata4 = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart4 = new google.visualization.PieChart(document.getElementById('Main_interactive_chart'));
            chart4.draw(piechartdata4, {
                width: 334,
                height: 180,
                title: 'Interactive Comments',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData4() {
            var request4 = new XMLHttpRequest();
            request4.onreadystatechange = function () {
                if (request4.readyState == 4 && request4.status == 200) {
                    drawInteractivePie(request4.responseText);
                }
            }
            var url4 = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_interactive_comments.php?class=" + classid;
            request4.open("GET", url4, true);
            request4.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData4 });

        function drawIntelligiblePie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata5 = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart5 = new google.visualization.PieChart(document.getElementById('Main_intelligible_chart'));
            chart5.draw(piechartdata5, {
                width: 334,
                height: 180,
                title: 'Intelligible Comments',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData5() {
            var request5 = new XMLHttpRequest();
            request5.onreadystatechange = function () {
                if (request5.readyState == 4 && request5.status == 200) {
                    drawIntelligiblePie(request5.responseText);
                }
            }
            var url5 = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_intelligible_comments.php?class=" + classid;
            request5.open("GET", url5, true);
            request5.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData5 });

        function drawInnovativePie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata6 = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart6 = new google.visualization.PieChart(document.getElementById('Main_innovative_chart'));
            chart6.draw(piechartdata6, {
                width: 334,
                height: 180,
                title: 'Innovative Comments',
                legend: 'none',
                backgroundColor: 'transparent',
                hAxis: {
                    title: 'Category'
                },
                vAxis: {
                    title: 'Score',
                    viewWindow: {
                        min: 0,
                        max: 100
                    }
                },
                is3D: true
            });
        }

        function loadData6() {
            var request6 = new XMLHttpRequest();
            request6.onreadystatechange = function () {
                if (request6.readyState == 4 && request6.status == 200) {
                    drawInnovativePie(request6.responseText);
                }
            }
            var url6 = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_innovative_comments.php?class=" + classid;
            request6.open("GET", url6, true);
            request6.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData6 });

    </script>

    
        <div class="comments">
            <h1>Class Information</h1>
            <br /><br />
            <asp:Label CssClass="InfoHeading" runat="server">Module: </asp:Label><asp:Label runat="server" ID="Module" />
            <br /><br />
            <asp:Label CssClass="InfoHeading" runat="server">Lecturer: </asp:Label><asp:Label runat="server" ID="Lecturer" />
            <br /><br />
            <asp:Label CssClass="InfoHeading" runat="server">Class Type: </asp:Label><asp:Label runat="server" ID="Type" />
            <br /><br />
            <asp:Label CssClass="InfoHeading" runat="server">Date & Time: </asp:Label><asp:Label runat="server" ID="DateTime" />
            <br /><br />
            <asp:Label CssClass="InfoHeading" runat="server">Number of Feedbacks: </asp:Label><asp:Label runat="server" ID="NoFeedbacks" />
            <br /><br /><br />
        </div>
    <div id="chart_div" runat="server" class="chart"/>
    <br /><br />
    <div id="interesting_chart" runat="server" class="pie"/>
    <div id="informative_chart" runat="server" class="pie"/>
    <div id="interactive_chart" runat="server" class="pie"/>
    <div id="intelligible_chart" runat="server" class="pie"/>
    <div id="innovative_chart" runat="server" class="pie"/>

        
        
    
    
    
    
    <hr />
    
    


 



    
    





    
    

</asp:Content>
