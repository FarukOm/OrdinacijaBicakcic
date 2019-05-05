using Ordinacija_API.Models;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Web;
using System.Web.Http;

namespace Ordinacija_API.Helper
{
    public class AuthToken:ApiController
    {
        private  OrdinacijaEntities db = new OrdinacijaEntities();

        protected string GetAuthToken()
        {
            string authToken = null;
            NameValueCollection headers = HttpContext.Current.Request.Headers;

            if (headers["AuthToken"] != null)
                authToken = headers["AuthToken"];

            return authToken;
        }

        protected bool ProvjeriValidnostTokena()
        {
            string token = GetAuthToken();

            AutorizacijskiToken TokenCheck = db.AutorizacijskiTokeni
                .Where(s => s.Vrijednost == token)
                .FirstOrDefault();

            if (TokenCheck != null)
            {
                if (TokenCheck.VrijemeEvidentiranja >= DateTime.Now.AddDays(-2)) // token moze biti star 2 dana
                {
                    return true;
                }
            }

            return false;
        }
        protected void IzbrisiToken()
        {
            string token = GetAuthToken();

            AutorizacijskiToken TokenCheck = db.AutorizacijskiTokeni
                .Where(s => s.Vrijednost == token)
                .FirstOrDefault();

            if (TokenCheck != null)
            {
                db.AutorizacijskiTokeni.Remove(TokenCheck);
                db.SaveChanges();
            }
        }
    }
}