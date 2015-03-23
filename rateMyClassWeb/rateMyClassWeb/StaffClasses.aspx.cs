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
    public partial class StaffClasses : System.Web.UI.Page
    {
        MySql.Data.MySqlClient.MySqlConnection conn;
        MySql.Data.MySqlClient.MySqlCommand cmd;
        MySql.Data.MySqlClient.MySqlDataReader reader;
        String queryStr;
        List<int> lecturerIds;
        List<String> lecturers = new List<string>();
        

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!this.IsPostBack)
            {
                Session["listLoaded"] = false;
                lecturerIds = new List<int>();
            }
            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
            else
            {
                if ((Boolean)Session["listLoaded"] == false)
                {
                    String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
                    conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
                    try
                    {
                        conn.Open();

                        queryStr = "SELECT * FROM staff WHERE Role !='admin'";
                        cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);

                        reader = cmd.ExecuteReader();

                        while (reader.HasRows && reader.Read())
                        {
                            lecturerList.Items.Add(reader.GetString("Forename") + " " + reader.GetString("Surname"));
                            lecturerIds.Add(reader.GetInt32("Staff_ID"));
                        }

                        Session["lecturerIds"] = lecturerIds;
                        Session["listLoaded"] = true;

                        reader.Close();

                        conn.Close();
                    }
                    catch (Exception error)
                    {
                        Response.Write("Error connecting to database: " + error.ToString());
                    }
                }
                if (!this.IsPostBack)
                {
                    
                    
                    this.BindListView();
                }
            }

        }

        private void BindListView()
        {
            List<int> ids = (List<int>)Session["lecturerIds"];
            int staffid = ids[lecturerList.SelectedIndex];

            String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
            conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
            try
            {
                conn.Open();

                queryStr = "SELECT class.*, staff.* FROM class INNER JOIN staff ON class.Staff_ID=staff.Staff_ID WHERE class.Staff_ID = @id";
                cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                cmd.Parameters.AddWithValue("@id", staffid);

                using (MySql.Data.MySqlClient.MySqlDataAdapter adapter = new MySql.Data.MySqlClient.MySqlDataAdapter(cmd))
                {
                    DataTable dt = new DataTable();
                    adapter.Fill(dt);
                    lvClasses.DataSource = dt;
                    lvClasses.DataBind();
                }
            }
            catch (Exception error)
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

        public void ChangeLecturer(object sender, System.EventArgs e)
        {
            BindListView();
        }

    }
}