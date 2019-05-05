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
    public class TerminiController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();

        [HttpGet]
        [Route("api/Termini/getTermini")]
        public IHttpActionResult getTermini()
        {
            if (ProvjeriValidnostTokena() == false)
                return Unauthorized();


            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime=s.Pacijent.Ime+" "+s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren,
                    IsEvidentiran=s.IsEvidentiran,
                    IsDeleted=s.IsDeleted,
                    Usluga=s.Usluga.Naziv
                }).ToList()
            };

            return Ok(model);
        }
        [HttpGet]
        [Route("api/Termini/GetTermin/{terminID}")]
        public IHttpActionResult getTermin(string terminID)
        {
            int IDint = Convert.ToInt32(terminID);
            Termini termin = db.Termini.Where(s => s.TerminID == IDint).FirstOrDefault();

            GetTerminResultVM model = new GetTerminResultVM
            {
                TerminID = termin.TerminID,
                Datum = termin.Datum,
                ImePrezime = termin.Pacijent.Ime + " " + termin.Pacijent.Prezime,
                Vrijeme = termin.Vrijeme,
                UslugaID = termin.UslugaID,
                Napomena=termin.Napomena,
                IsOdobren=termin.IsOdobren,
                PacijentID=termin.PacijentID,
                IsEvidentiran=termin.IsEvidentiran,
                IsDeleted=termin.IsDeleted
            };

            return Ok(model);
        }

        [HttpGet]
        [Route("api/Termini/GetTerminByPacijent/{pacijentId}")]
        public IHttpActionResult getTerminByKorisnik(string pacijentId)
        {
            int IDint = Convert.ToInt32(pacijentId);

            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Where(x=>x.PacijentID == IDint && !x.IsDeleted).Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime = s.Pacijent.Ime + " " + s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren,
                    IsEvidentiran = s.IsEvidentiran,
                    IsDeleted = s.IsDeleted,
                    Usluga = s.Usluga.Naziv
                }).ToList()
            };

            return Ok(model);
        }
        
        [HttpGet]
        [Route("api/Termini/getNoviTermini")]
        public IHttpActionResult getNoviTermini()
        {
            if (ProvjeriValidnostTokena() == false)
                return Unauthorized();


            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Where(d=>d.IsEvidentiran==false && (d.Datum > DateTime.Today || d.Datum == DateTime.Today)).Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime = s.Pacijent.Ime + " " + s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Usluga = s.Usluga.Naziv,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren,
                }).ToList()
            };

            return Ok(model);
        }

        [HttpGet]
        [Route("api/Termini/getEvidentiraniTermini")]
        public IHttpActionResult getEvidentiraniTermini()
        {
            if (ProvjeriValidnostTokena() == false)
              return Unauthorized();


            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Where(d => d.IsEvidentiran == true && (d.Datum > DateTime.Today || d.Datum == DateTime.Today)).Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime = s.Pacijent.Ime + " " + s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Usluga = s.Usluga.Naziv,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren,
                    IsDeleted = s.IsDeleted,
                }).ToList()
            };

            return Ok(model);
        }
        [HttpGet]
        [Route("api/Termini/getDanasnjeTermine")]
        public IHttpActionResult getDanasnjeTermine()
        {
            if (ProvjeriValidnostTokena() == false)
                return Unauthorized();
            DateTime datum = DateTime.Today.Date;
         
            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Where(d => d.Datum==datum && d.IsOdobren==true).Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime = s.Pacijent.Ime + " " + s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Usluga = s.Usluga.Naziv,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren
                }).ToList()
            };

            return Ok(model);
        }

        [HttpGet]
        [Route("api/Termini/getDanasnjiTerminByKorisnik/{pacijentId}")]
        public IHttpActionResult getDanasnjiTerminByKorisnik(int pacijentId)
        {
            //if (ProvjeriValidnostTokena() == false)
             //return Unauthorized();
            DateTime datum = DateTime.Today.Date;
          //  int IDint = Convert.ToInt32(pacijentId);

            TerminiResultVM model = new TerminiResultVM
            {
                rows = db.Termini.Where(d => d.PacijentID == pacijentId && d.IsOdobren == true && d.Datum==datum).Select(s => new TerminiResultVM.Row
                {
                    TerminID = s.TerminID,
                    ImePrezime = s.Pacijent.Ime + " " + s.Pacijent.Prezime,
                    PacijentID = s.PacijentID,
                    Datum = s.Datum,
                    Vrijeme = s.Vrijeme,
                    Usluga = s.Usluga.Naziv,
                    Napomena = s.Napomena,
                    IsOdobren = s.IsOdobren
                }).ToList()
            };

            return Ok(model);
        }

        [HttpPut]
        [ResponseType(typeof(Termini))]
        [Route("api/Termini/putOdobreno")]

        public IHttpActionResult putOdobreno([FromBody] TerminPostVM termin)
        {

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Termini edit = db.Termini.Where(s => s.TerminID == termin.TerminID).FirstOrDefault();
            edit.IsOdobren = true;
            edit.IsEvidentiran = true;
            db.Entry(edit);
            db.SaveChanges();

            return Ok();
        }
        [HttpPut]
        [ResponseType(typeof(Termini))]
        [Route("api/Termini/putOdbijeno")]

        public IHttpActionResult putOdbijeno([FromBody] TerminPostVM termin)
        {

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Termini edit = db.Termini.Where(s => s.TerminID == termin.TerminID).FirstOrDefault();
            edit.IsOdobren = false;
            edit.IsEvidentiran = true;
            db.Entry(edit);
            db.SaveChanges();

            return Ok();
        }

        [HttpPut]
        [ResponseType(typeof(Termini))]
        [Route("api/Termini/putOtkazano")]

        public IHttpActionResult putOtkazano([FromBody] TerminPostVM termin)
        {

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Termini edit = db.Termini.Where(s => s.TerminID == termin.TerminID).FirstOrDefault();
            edit.IsOdobren = false;
            edit.IsDeleted = true;
            db.Entry(edit);
            db.SaveChanges();

            return Ok();
        }

        [HttpPut]
        [ResponseType(typeof(Termini))]
        [Route("api/Termini/putOtkazanoByTerminId{terminId}")]

        public IHttpActionResult putOtkazanoByTerminId(int terminId)
        {

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            Termini edit = db.Termini.Where(s => s.TerminID == terminId).FirstOrDefault();
            edit.IsOdobren = false;
            edit.IsDeleted = true;
            db.Entry(edit);
            db.SaveChanges();

            return Ok();
        }

        [HttpPost]
        [ResponseType(typeof(TerminPostVM))]
        [Route("api/Termini/postTermin")]

        public IHttpActionResult PostTermin([FromBody] TerminPostVM termin)
        {
            DateTime noviDatum = DateTime.Parse(termin.Datum);

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            
            Termini noviTermin = new Termini
            {
                Datum = noviDatum,
                Vrijeme = termin.Vrijeme,
                Napomena = termin.Napomena,
                PacijentID = termin.PacijentID,
                UslugaID = int.Parse(termin.Usluga)
            };

            db.Termini.Add(noviTermin);
            db.SaveChanges();

            return Ok();
        }
    }
}
