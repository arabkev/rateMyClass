using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class EditTeachings : System.Web.UI.Page
    {
        MySql.Data.MySqlClient.MySqlConnection conn;
        MySql.Data.MySqlClient.MySqlCommand cmd;
        MySql.Data.MySqlClient.MySqlDataReader reader;
        String queryStr;
        List<int> staffids;
        

        protected void Page_Load(object sender, EventArgs e)
        {

            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
            String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
            conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
            try
            {
                List<String> modules = new List<string>();

                conn.Open();

                queryStr = "SELECT * FROM module";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);

                reader = cmd.ExecuteReader();

                while (reader.HasRows && reader.Read())
                {
                    modules.Add(reader.GetString("Module_Code") + " - " + reader.GetString("Module_Name"));
                }

                ddlModule2.DataSource = modules;
                ddlModule2.DataBind();

                reader.Close();

                conn.Close();
            }
            catch (Exception error)
            {
                Response.Write("Error connecting to database: " + error.ToString());
            }
            try
            {
                List<String> staff = new List<string>();
                staffids = new List<int>();

                conn.Open();

                queryStr = "SELECT * FROM staff";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);

                reader = cmd.ExecuteReader();

                while (reader.HasRows && reader.Read())
                {
                    staff.Add(reader.GetString("Forename") + " " + reader.GetString("Surname"));
                    staffids.Add(Convert.ToInt32(reader.GetString("Staff_ID")));
                }

                ddlStaff2.DataSource = staff;
                ddlStaff2.DataBind();

                reader.Close();

                conn.Close();
            }
            catch (Exception error)
            {
                Response.Write("Error connecting to database: " + error.ToString());
            }
        }

        protected void insertBtn_Click(object sender, EventArgs e)
        {
            string module = "";
            int staff = 0;
            module = ddlModule2.SelectedItem.ToString().Substring(0, 7);
            staff = staffids[ddlStaff2.SelectedIndex];
            RateMyClassDB.InsertParameters.Add("Module_Code", module);
            RateMyClassDB.InsertParameters.Add("Staff_ID", TypeCode.Int32, staff.ToString());
            RateMyClassDB.Insert();
            Response.Redirect("EditTeachings.aspx");
        }
    }
}