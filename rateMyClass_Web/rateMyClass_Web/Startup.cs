using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(rateMyClass_Web.Startup))]
namespace rateMyClass_Web
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
