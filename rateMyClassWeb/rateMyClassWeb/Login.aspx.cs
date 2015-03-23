using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class Login : System.Web.UI.Page
    {
        String name;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
            else
            {
                name = (String)Session["uname"];
                userLabel.Text = name.Substring(0, name.IndexOf(" "));
                if ((String)Session["role"] != "dean")
                {
                    yearClassesLink.Visible = false;
                    lecturerClassesLink.Visible = false;
                    if ((String)Session["role"] == "lecturer")
                    {
                        editStaffLink.Visible = false;
                        editModulesLink.Visible = false;
                        editTeachingsLink.Visible = false;
                    }
                    else
                    {
                        myClassesLink.Visible = false;
                    }
                }
                else
                {
                    editModulesLink.Visible = false;
                    editStaffLink.Visible = false;
                    editTeachingsLink.Visible = false;
                }
            }
        }
    }
}