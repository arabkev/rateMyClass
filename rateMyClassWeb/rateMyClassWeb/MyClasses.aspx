<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MyClasses.aspx.cs" Inherits="rateMyClassWeb.MyClasses" MasterPageFile="~/Master.master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Main" runat="Server">
    <a href="Login.aspx"><- Back</a>

    <!--Load the AJAX API-->
    <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.js" type="text/javascript"></script>

    <script type="text/javascript">

        // Set a callback to run when the Google Visualization API is loaded.
        google.setOnLoadCallback(drawItems);

        var staffid = '<%=Session["id"]%>';

        function drawItems(json) {
            
            // Create our data table out of JSON data loaded from server.
            var piechartdata = new google.visualization.DataTable(json);

            // Instantiate and draw our pie chart, passing in some options.
            var chart = new google.visualization.ColumnChart(document.getElementById('Main_chart_div'));
            chart.draw(piechartdata, {
                width: 1000,
                height: 563,
                title: 'Overall Staff Feedback',
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
            var url = "https://zeno.computing.dundee.ac.uk/2014-projects/kevinmckenzie/get_staff_feedback.php?staff_id=" + staffid;
            request.open("GET", url, true);
            request.send();
        }

        // Load the Visualization API and the piechart,table package.
        google.load('visualization', '1', { 'packages': ['corechart'], callback: loadData });

    </script>

    <form runat="server">
        <div>
            <h1>My Classes</h1>
        </div>
        <div class="CSSTableGenerator">
            <asp:ListView ID="lvClasses" runat="server" GroupPlaceholderID="groupPlaceHolder1"
                ItemPlaceholderID="itemPlaceHolder1" OnPagePropertiesChanging="OnPagePropertiesChanging">
                <LayoutTemplate>
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <th>Date & Time
                            </th>
                            <th>Class Type
                            </th>
                            <th>Module
                            </th>
                            <th>Lecturer
                            </th>
                            <th></th>
                        </tr>
                        <asp:PlaceHolder runat="server" ID="groupPlaceHolder1"></asp:PlaceHolder>
                        <tr>
                            <td colspan="4">
                                <asp:DataPager ID="DataPager1" runat="server" PagedControlID="lvClasses" PageSize="10">
                                    <Fields>
                                        <asp:NextPreviousPagerField ButtonType="Link" ShowFirstPageButton="false" ShowPreviousPageButton="true"
                                            ShowNextPageButton="false" />
                                        <asp:NumericPagerField ButtonType="Link" />
                                        <asp:NextPreviousPagerField ButtonType="Link" ShowNextPageButton="true" ShowLastPageButton="false" ShowPreviousPageButton="false" />
                                    </Fields>
                                </asp:DataPager>
                            </td>
                        </tr>
                    </table>
                </LayoutTemplate>
                <GroupTemplate>
                    <tr>
                        <asp:PlaceHolder runat="server" ID="itemPlaceHolder1"></asp:PlaceHolder>
                    </tr>
                </GroupTemplate>
                <ItemTemplate>
                    <td>
                        <%# Eval("DateTime") %>
                    </td>
                    <td>
                        <%# Eval("Class_Type") %>
                    </td>
                    <td>
                        <%# Eval("Module_Code") %>
                    </td>
                    <td>
                        <%# Eval("Forename") + " " + Eval("Surname") %>
                    </td>
                    <td>
                        <asp:LinkButton ID="viewFeedbacksLink" runat="server" OnCommand="View_Feedbacks" CommandArgument='<%# string.Format("{0}", Eval("Class_ID")) %>'>View Feedbacks</asp:LinkButton>
                    </td>
                </ItemTemplate>
            </asp:ListView>
        </div>
    </form>
    <div runat="server" id="chart_div" class="=chart"/>
</asp:Content>
