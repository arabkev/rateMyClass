<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="rateMyClassWeb.Default" MasterPageFile="~/Master.master" %>

<asp:content id="Content1" contentplaceholderid="Main" runat="Server">
    <h2>Please log in with your University of Dundee credentials:</h2>
    <form id="form1" runat="server">
    <div>
        <br />
        <br />
        <asp:Label runat="server">Email Address:</asp:Label>
        <asp:TextBox ID="emailTextBox" runat="server" class="login_email"/>
        <br />
        <asp:Label runat="server">Password:</asp:Label>
        <asp:TextBox ID="passwordTextBox" runat="server" TextMode="Password" class="login_password" />
        <p id="passwordMsg" runat="server"></p>
        <br />
        <asp:ImageButton ID="login_Button" OnClick="loginButton_Click" runat="server" ImageUrl="~/cooltext116142327976605.png" />
        <!--<asp:Button ID="loginButton" text="Log In" runat="server" OnClick="loginButton_Click" class="login_button" />-->
        
    </div>
    </form>
</asp:content>
