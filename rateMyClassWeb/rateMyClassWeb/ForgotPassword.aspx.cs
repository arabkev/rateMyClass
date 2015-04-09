using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class ForgotPassword : System.Web.UI.Page
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
                forgot_Button.Attributes.Add("onmouseover", "src='cooltext116476408410091MouseOver.png'");
                forgot_Button.Attributes.Add("onmouseout", "src='cooltext116476408410091.png'");
            }
        }

        protected void change_Button_Click(object sender, ImageClickEventArgs e)
        {
            msg.InnerText = "";
            String pword = "";

            String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
            conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
            try
            {
                conn.Open();

                queryStr = "SELECT Password FROM staff WHERE Email_Address=@email";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                cmd.Parameters.AddWithValue("@email", email.Text);

                reader = cmd.ExecuteReader();

                while (reader.HasRows && reader.Read())
                {
                    pword = reader.GetString("Password");
                }

                conn.Close();
            }
            catch (Exception error)
            {
                msg.InnerText = "Could not connect to database";
            }
            
        }
    }
}