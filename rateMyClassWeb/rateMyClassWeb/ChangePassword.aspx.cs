using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class ChangePassword : System.Web.UI.Page
    {
        MySql.Data.MySqlClient.MySqlConnection conn;
        MySql.Data.MySqlClient.MySqlCommand cmd;
        MySql.Data.MySqlClient.MySqlDataReader reader;
        String queryStr;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
            else
            {
                change_Button.Attributes.Add("onmouseover", "src='cooltext116143387916064MouseOver.png'");
                change_Button.Attributes.Add("onmouseout", "src='cooltext116143387916064.png'");
            }
        }

        protected void change_Button_Click(object sender, ImageClickEventArgs e)
        {
            passwordMsg.InnerText = "";
            if (newPword.Text != confirmPword.Text)
            {
                passwordMsg.InnerText = "New password confirmation did not match. Please check again";
            }
            else if (oldPword.Text == (String)Session["password"])
            {
                String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
                conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
                try
                {
                    conn.Open();

                    queryStr = "UPDATE staff SET Password=@pword WHERE Staff_ID=@id";
                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@pword", newPword.Text);
                    cmd.Parameters.AddWithValue("@id", Session["id"]);

                    cmd.ExecuteNonQuery();
                    Session["password"] = newPword.Text;

                    conn.Close();
                }
                catch (Exception error)
                {
                    passwordMsg.InnerText = "Could not connect to database";
                }
            }
            else
            {
                passwordMsg.InnerText = "Old password incorrect. Please check again";
            }
        }
    }
}