using Ordinacija_API.Helper;
using Ordinacija_API.Models;
using Ordinacija_API.ModelVM;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace Ordinacija_API.Controllers
{
    public class PacijentiController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();

        [HttpGet]
        [Route("api/Pacijenti/getPacijenti")]
        public IHttpActionResult getPacijenti()
        {
           
        
            PacijentiResultVM model = new PacijentiResultVM
            {
                rows = db.Pacijenti.Select(s => new PacijentiResultVM.Row
                {
                    PacijentID = s.PacijentID,
                    Ime = s.Ime,
                    Prezime = s.Prezime,
                    Adresa = s.Adresa,
                    Telefon = s.Telefon,
                    DatumRodjenja = s.DatumRodjenja,
                    Email = s.Email
                }).ToList()
            };

            return Ok(model);
        }
        [HttpGet]
        [Route("api/Pacijenti/getPacijent/{pacijentID}")]
        public IHttpActionResult getPacijent(string pacijentID)
        {
            int IDint = Convert.ToInt32(pacijentID);
            Pacijenti pacijent = db.Pacijenti.Where(s => s.PacijentID == IDint).FirstOrDefault();

            GetPacijentResultVM model = new GetPacijentResultVM
            {
                PacijentID = pacijent.PacijentID,
                Ime = pacijent.Ime,
                Prezime = pacijent.Prezime,
                Adresa = pacijent.Adresa,
                Telefon = pacijent.Telefon,
                DatumRodjenja = pacijent.DatumRodjenja,
                Email = pacijent.Email
            };

            return Ok(model);
        }
        
    }
}
