using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class EditModules : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
        }

        protected void insertBtn_Click(object sender, EventArgs e)
        {
            RateMyClassDB.Insert();
            Response.Redirect("EditModules.aspx");
        }
    }
}