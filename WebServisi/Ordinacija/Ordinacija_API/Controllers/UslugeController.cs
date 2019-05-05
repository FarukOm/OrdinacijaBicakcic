using Ordinacija_API.Helper;
using Ordinacija_API.Models;
using Ordinacija_API.ModelVM;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Ordinacija_API.Controllers
{
    public class UslugeController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();

        [HttpGet]
        [Route("api/Usluge/getUsluge")]
        public IHttpActionResult getUsluge()
        {
           // if (ProvjeriValidnostTokena() == false)
           // return Unauthorized();


            UslugeResultVM model = new UslugeResultVM
            {
                rows = db.Usluge.Select(s => new UslugeResultVM.Row
                {
                    UslugaID=s.UslugaID,
                    Naziv=s.Naziv,
                    Opis=s.Opis
                }).ToList()
            };

            return Ok(model);
        }
    }
}
