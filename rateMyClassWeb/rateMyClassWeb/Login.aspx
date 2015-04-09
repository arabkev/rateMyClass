<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="rateMyClassWeb.Login" MasterPageFile="~/Master.master" %>

<asp:content id="Content1" contentplaceholderid="Main" runat="Server">
    <a href="default.aspx"><- Log Out</a>
    <div>
        <h1>Welcome, 
        <asp:Label ID="userLabel" runat="server" Text=", you are not logged in" />
        !</h1>
        <br />
        <asp:HyperLink NavigateUrl="~/ChangePassword.aspx" runat="server">Change Password</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/MyClasses.aspx" ID="myClassesLink" runat="server">My Classes</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/YearClasses.aspx" ID="yearClassesLink" runat="server">Classes by Year</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/StaffClasses.aspx" ID="lecturerClassesLink" runat="server">Classes by Lecturer</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/EditStaff.aspx" ID="editStaffLink" runat="server">Edit Staff</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/EditModules.aspx" ID="editModulesLink" runat="server">Edit Modules</asp:HyperLink>
        <br />
        <asp:HyperLink NavigateUrl="~/EditTeachings.aspx" ID="editTeachingsLink" runat="server">Edit Teaching Assignments</asp:HyperLink>
    </div>
</asp:content>

