using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class Default : System.Web.UI.Page
    {
        MySql.Data.MySqlClient.MySqlConnection conn;
        MySql.Data.MySqlClient.MySqlCommand cmd;
        MySql.Data.MySqlClient.MySqlDataReader reader;
        String queryStr;
        String name, role, email;
        int id;

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void loginButton_Click(object sender, EventArgs e)
        {
            String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
            conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
            try
            {
                conn.Open();

                queryStr = "SELECT * FROM staff WHERE Email_Address=@email AND Password=@pword";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                cmd.Parameters.AddWithValue("@email", emailTextBox.Text);
                cmd.Parameters.AddWithValue("@pword", passwordTextBox.Text);

                reader = cmd.ExecuteReader();
                
                while (reader.HasRows && reader.Read())
                {
                    name = reader.GetString("Forename") + " " + reader.GetString("Surname");
                    role = reader.GetString("Role");
                    email = reader.GetString("Email_Address");
                    id = reader.GetInt32("Staff_ID");
                }

                if (reader.HasRows)
                {
                    Session["uname"] = name;
                    Session["role"] = role;
                    Session["email"] = email;
                    Session["id"] = id;
                    Response.BufferOutput = true;
                    Response.Redirect("Login.aspx", false);
                }
                else
                {
                    passwordMsg.InnerText = "Invalid email address or password. Please try again";
                }

                reader.Close();

                conn.Close();
            }
            catch(Exception error)
            {
                Response.Write("Could not connect to database");
            }
        }
    }
}