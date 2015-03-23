using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace rateMyClassWeb
{
    public partial class ClassFeedbacks : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["uname"] == null)
            {
                Response.Redirect("Default.aspx");
            }
            else
            {
                int classid = (int)Session["class"];
                MySql.Data.MySqlClient.MySqlConnection conn;
                MySql.Data.MySqlClient.MySqlCommand cmd;
                MySql.Data.MySqlClient.MySqlDataReader reader;
                String queryStr;

                String connString = System.Configuration.ConfigurationManager.ConnectionStrings["connString"].ToString();
                conn = new MySql.Data.MySqlClient.MySqlConnection(connString);
                try
                {
                    conn.Open();

                    queryStr = "SELECT class.*, module.*, staff.*, COUNT(Feedback_ID) AS Count_Classes FROM class INNER JOIN module ON class.Module_Code=module.Module_Code INNER JOIN staff ON class.Staff_ID=staff.Staff_ID INNER JOIN feedback ON class.Class_ID=feedback.Class_ID WHERE (Class.Class_ID = @classid)";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Module.Text = reader.GetString("Module_Code") + " (" + reader.GetString("Module_Name") + ")";
                        Type.Text = reader.GetString("Class_Type");
                        DateTime.Text = reader.GetString("DateTime");
                        Lecturer.Text = reader.GetString("Forename") + " " + reader.GetString("Surname");
                        NoFeedbacks.Text = reader.GetInt32("Count_Classes").ToString();
                    }

                    reader.Close();

                    conn.Close();


                }
                catch (Exception error)
                {
                    Response.Write("Could not connect to database");
                }
                /*try
                {
                    conn.Open();

                    queryStr = "SELECT Interesting_Comment FROM feedback WHERE (Class_ID = @classid) GROUP BY (feedback.Interesting_Comment) ORDER BY COUNT(feedback.Interesting_Comment) DESC LIMIT 1;";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Interesting_Comment.Text = reader.GetString("Interesting_Comment");
                    }

                    reader.Close();

                    conn.Close();

                }
                catch (Exception error)
                {
                    //Response.Write("Could not connect to database");
                    Response.Write(error.Message);
                }
                try
                {
                    conn.Open();

                    queryStr = "SELECT Informative_Comment FROM feedback WHERE (Class_ID = @classid) GROUP BY (feedback.Informative_Comment) ORDER BY COUNT(feedback.Informative_Comment) DESC LIMIT 1;";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Informative_Comment.Text = reader.GetString("Informative_Comment");
                    }

                    reader.Close();

                    conn.Close();

                }
                catch (Exception error)
                {
                    //Response.Write("Could not connect to database");
                    Response.Write(error.Message);
                }
                try
                {
                    conn.Open();

                    queryStr = "SELECT Interactive_Comment FROM feedback WHERE (Class_ID = @classid) GROUP BY (feedback.Interactive_Comment) ORDER BY COUNT(feedback.Interactive_Comment) DESC LIMIT 1;";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Interactive_Comment.Text = reader.GetString("Interactive_Comment");
                    }

                    reader.Close();

                    conn.Close();

                }
                catch (Exception error)
                {
                    //Response.Write("Could not connect to database");
                    Response.Write(error.Message);
                }
                try
                {
                    conn.Open();

                    queryStr = "SELECT Intelligible_Comment FROM feedback WHERE (Class_ID = @classid) GROUP BY (feedback.Intelligible_Comment) ORDER BY COUNT(feedback.Intelligible_Comment) DESC LIMIT 1;";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Intelligible_Comment.Text = reader.GetString("Intelligible_Comment");
                    }

                    reader.Close();

                    conn.Close();

                }
                catch (Exception error)
                {
                    //Response.Write("Could not connect to database");
                    Response.Write(error.Message);
                }
                try
                {
                    conn.Open();

                    queryStr = "SELECT Innovative_Comment FROM feedback WHERE (Class_ID = @classid) GROUP BY (feedback.Innovative_Comment) ORDER BY COUNT(feedback.Innovative_Comment) DESC LIMIT 1;";

                    cmd = new MySql.Data.MySqlClient.MySqlCommand(queryStr, conn);
                    cmd.Parameters.AddWithValue("@classid", classid);

                    reader = cmd.ExecuteReader();

                    while (reader.HasRows && reader.Read())
                    {
                        Innovative_Comment.Text = reader.GetString("Innovative_Comment");
                    }

                    reader.Close();

                    conn.Close();

                }
                catch (Exception error)
                {
                    //Response.Write("Could not connect to database");
                    Response.Write(error.Message);
                }*/
            }
        }
    }
}