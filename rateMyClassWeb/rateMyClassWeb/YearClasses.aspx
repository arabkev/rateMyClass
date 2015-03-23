<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="sd.aspx.cs" Inherits="rateMyClassWeb.YearClasses" MasterPageFile="~/Master.master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Main" runat="Server">
    <form runat="server">
        <div>
            <h1>Classes by Year</h1>
        </div>
        <div>
            <asp:DropDownList ID="yearList" runat="server" OnSelectedIndexChanged="ChangeYear" AutoPostBack="true">
                <asp:ListItem>1</asp:ListItem>
                <asp:ListItem>2</asp:ListItem>
                <asp:ListItem>3</asp:ListItem>
                <asp:ListItem>4</asp:ListItem>
                <asp:ListItem>5</asp:ListItem>
            </asp:DropDownList>
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
</asp:Content>
