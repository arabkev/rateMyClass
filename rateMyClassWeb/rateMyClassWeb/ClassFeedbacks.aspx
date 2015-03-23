<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ClassFeedbacks.aspx.cs" Inherits="rateMyClassWeb.ClassFeedbacks" MasterPageFile="~/Master.master" %>


<asp:Content ID="Content1" ContentPlaceHolderID="Main" runat="Server">
    <!--Load the AJAX API-->
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">

        // Set a callback to run when the Google Visualization API is loaded.
        //google.setOnLoadCallback(drawItems);

        var classid = '<%=Session["class"]%>';

        function drawItems(json) {
            /*var jsonPieChartData = loadData(); $.ajax({
                url: "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_class_feedback.php",
                data: {class: classid},
                dataType: "json",
                async: false,
                success: function (data) {
                    alert("data: " + data[0])
                },
                error: function(data) {
                    alert:("error: " + data[0])
                }
            }).responseText;
  
            document.getElementById('chart_div').innerText = classid;*/

            //alert(json);

            // Create our data table out of JSON data loaded from server.
            var piechartdata = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
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
                }
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
            <br />
        
    
    
            <br />
            <asp:Label CssClass="InfoHeading" runat="server">Number of Feedbacks: </asp:Label><asp:Label runat="server" ID="NoFeedbacks" />
            <br /><br /><br />
        </div>
        <div id="chart_div" class="chart"/>
    
    
    
    
    <hr />
    
    <div id="interesting_chart" class="pie"/>


    <script type="text/javascript">

        var classid = '<%=Session["class"]%>';

        function drawInterestingPie(json) {

            // Create our data table out of JSON data loaded from server.
            var piechartdata = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart = new google.visualization.PieChart(document.getElementById('interesting_chart'));
            chart.draw(piechartdata, {
                width: 500,
                height: 270,
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
                }
            });
        }

        function loadData() {
            var request = new XMLHttpRequest();
            request.onreadystatechange = function () {
                if (request.readyState == 4 && request.status == 200) {
                    drawInterestingPie(request.responseText);
                }
            }
            var url = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_interesting_comments.php?class=" + classid;
            request.open("GET", url, true);
            request.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData });

    </script>



    
    





    
    

</asp:Content>
