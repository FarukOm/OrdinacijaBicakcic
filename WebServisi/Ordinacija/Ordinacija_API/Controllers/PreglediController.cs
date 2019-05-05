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
    public class PreglediController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();

        [HttpGet]
        [Route("api/Pregledi/getPreglediByPacijent/{PacijentID}")]
        public IHttpActionResult getPreglediByPacijent(string PacijentID)
        {
            if (ProvjeriValidnostTokena() == false)
                return Unauthorized();

            int IDint = Convert.ToInt32(PacijentID);

            PreglediResultVM model = new PreglediResultVM
            {
                rows = db.Pregledi.Where(s => s.PacijentID == IDint).Select(s => new PreglediResultVM.Row
                {
                    PregledID=s.PregledID,
                    Datum=s.Datum,
                    Vrijeme=s.Vrijeme,
                    Opis=s.Opis,
                    PacijentID=s.PacijentID,
                    ZaposlenikID=s.ZaposlenikID,
                    Usluga=s.Usluga.Naziv

                }).ToList()
            };

            return Ok(model);
        }
        [HttpGet]
        [Route("api/Pregledi/getPregledByID/{PregledID}")]
        public IHttpActionResult getPregledByID(string PregledID)
        {
            if (ProvjeriValidnostTokena() == false)
                return Unauthorized();

            int IDint = Convert.ToInt32(PregledID);
            Pregledi pregled = db.Pregledi.Where(d => d.PregledID == IDint).FirstOrDefault();
            GetPregledResultVM model = new GetPregledResultVM
            {    
                    PregledID = pregled.PregledID,
                    Datum = pregled.Datum.ToShortDateString(),
                    Vrijeme = pregled.Vrijeme,
                    Opis = pregled.Opis,
                    PacijentID = pregled.PacijentID,
                    ZaposlenikID = pregled.ZaposlenikID,
                    Usluga=pregled.Usluga.Naziv
            };

            return Ok(model);
        }
        [ResponseType(typeof(Pregledi))]
        [Route("api/Pregledi/postPregled")]

        public IHttpActionResult PostPregled([FromBody] PregledPostVM pregled)
        {
            DateTime noviDatum = DateTime.Parse(pregled.Datum);
            
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Pregledi NoviPregled = new Pregledi
            {
                Datum=noviDatum,
                Vrijeme=pregled.Vrijeme,
                Opis=pregled.Opis,
                ZaposlenikID=pregled.ZaposlenikID,
                PacijentID=pregled.PacijentID,
                UslugaID=pregled.UslugaID
            };

            db.Pregledi.Add(NoviPregled);
            db.SaveChanges();

            return Ok();
        }
    }
}
