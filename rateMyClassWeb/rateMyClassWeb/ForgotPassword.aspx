<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ForgotPassword.aspx.cs" Inherits="rateMyClassWeb.ForgotPassword" MasterPageFile="~/Master.master" %>

<asp:content id="Content1" contentplaceholderid="Main" runat="Server">
    <a href="Default.aspx"><- Back</a>
    <form id="form1" runat="server">
        <br />
        <br />
        <asp:Label runat="server">Email Address:</asp:Label>
        <asp:TextBox ID="email" runat="server" class="login_email"/>
        <p id="msg" runat="server"></p>
        <br />
        <asp:ImageButton ID="forgot_Button" OnClick="change_Button_Click" runat="server" ImageUrl="~/cooltext116476408410091.png" />
    </form>
</asp:content>
