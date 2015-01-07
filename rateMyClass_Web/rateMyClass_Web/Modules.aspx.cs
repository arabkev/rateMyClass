using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.Odbc;
using System.Configuration;
using MySql.Data.MySqlClient;

namespace rateMyClass_Web
{
    public partial class Modules : Page
    {

        
        protected void Page_Load(object sender, EventArgs e)
        {
            MySqlConnection conn = new MySqlConnection();
            try
            {
                conn.ConnectionString = "server=sql3.freemysqlhosting.net;database=sql363431;UID=sql363431;PASSWORD=jF8%mF6%";
                conn.Open();
                MySqlCommand cmd = new MySqlCommand("SELECT Module_Name FROM module", conn);
                MySqlDataReader reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    info.InnerHtml = reader["Module_Name"].ToString();
                }
            }
            catch (Exception ex)
            {
                Response.Write("An error occured: " + ex.Message);
            }
            conn.Close();
        }
    }
}