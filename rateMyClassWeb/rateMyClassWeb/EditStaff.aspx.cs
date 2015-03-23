using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class EditStaff : System.Web.UI.Page
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
            string role = "";
            role = ddlRole.SelectedItem.Text;
            RateMyClassDB.InsertParameters.Add("Role", role);
            RateMyClassDB.Insert();
            Response.Redirect("EditStaff.aspx");
        }

        protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName.Equals("Update"))
            {
                string role = "";
                int index = int.Parse(e.CommandArgument.ToString());
                GridViewRow row = grid.Rows[index];
                DropDownList lstStatus = (DropDownList)row.FindControl("ddlRole");
                role = lstStatus.SelectedItem.Text;
                RateMyClassDB.UpdateParameters.Add("Role", role);
            }
        }
    }
}