using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Configuration;

namespace rateMyClassWeb
{
    public partial class MyClasses : System.Web.UI.Page
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
                if (!this.IsPostBack)
                {
                    this.BindListView();
                }
            }
            
        }

        private void BindListView()
        {
            String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
            conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
            try
            {
                conn.Open();

                queryStr = "SELECT class.*, staff.* FROM class INNER JOIN staff ON class.Staff_ID=staff.Staff_ID WHERE class.Staff_ID = @id";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                cmd.Parameters.AddWithValue("@id", Session["id"]);

                using (MySql.Data.MySqlClient.MySqlDataAdapter adapter = new MySql.Data.MySqlClient.MySqlDataAdapter(cmd))
                {
                    DataTable dt = new DataTable();
                    adapter.Fill(dt);
                    lvClasses.DataSource = dt;
                    lvClasses.DataBind();
                }
            }
            catch(Exception error)
            {
                Response.Write("Error connecting to database: " + error.ToString());
            }
        }

        protected void OnPagePropertiesChanging(object sender, PagePropertiesChangingEventArgs e)
        {
            (lvClasses.FindControl("DataPager1") as DataPager).SetPageProperties(e.StartRowIndex, e.MaximumRows, false);
            this.BindListView();
        }

        public void View_Feedbacks(object sender, CommandEventArgs e)
        {
            //Response.Write(e.CommandArgument.ToString());
            int class_id = Int32.Parse(e.CommandArgument.ToString());
            Session["class"] = class_id;
            Response.Redirect("ClassFeedbacks.aspx", false);
        }

    }
}