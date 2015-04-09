<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ChangePassword.aspx.cs" Inherits="rateMyClassWeb.ChangePassword" MasterPageFile="~/Master.master" %>

<asp:content id="Content1" contentplaceholderid="Main" runat="Server">
    <a href="Login.aspx"><- Back</a>
    <form id="form1" runat="server">
        <br />
        <br />
        <asp:Label runat="server">Old Password:</asp:Label>
        <asp:TextBox ID="oldPword" runat="server" class="login_email"/>
        <br />
        <asp:Label runat="server">New Password:</asp:Label>
        <asp:TextBox ID="newPword" runat="server" class="login_email"/>
        <br />
        <asp:Label runat="server">Confirm New Password:</asp:Label>
        <asp:TextBox ID="confirmPword" runat="server" class="login_email"/>
        <p id="passwordMsg" runat="server"></p>
        <br />
        <asp:ImageButton ID="change_Button" OnClick="change_Button_Click" runat="server" ImageUrl="~/cooltext116143387916064.png" />
    </form>
</asp:content>
