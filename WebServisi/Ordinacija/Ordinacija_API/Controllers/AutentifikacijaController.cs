using Ordinacija_API.Helper;
using Ordinacija_API.Models;
using Ordinacija_API.ModelVM;
using System;
using System.Linq;
using System.Web.Http;

namespace Ordinacija_API.Controllers
{
    public class AutentifikacijaController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();

        [HttpGet]
        [Route("api/Autentifikacija/LoginCheck/{username}/{pass}")]
        public IHttpActionResult LoginCheck(string username, string pass)
        {
            string token = Guid.NewGuid().ToString();

            var korisnik = db.Korisnici.Where(s => s.KorisnickoIme == username && s.LozinkaSalt == pass).FirstOrDefault();

            // unutar lozinkaSalt je smjesten string password
            if (korisnik != null)
            {
                AutentifikacijaResultVM a = new AutentifikacijaResultVM();
                a.KorisnikID = korisnik.KorisnikID;
                a.Ime = korisnik.Ime;
                a.Prezime = korisnik.Prezime;
                a.KorisnickoIme = korisnik.KorisnickoIme;
                a.LozinkaSalt = korisnik.LozinkaSalt;
                a.Telefon = korisnik.Telefon;
                a.Email = korisnik.Email;
                a.Token = token;
                a.Uloga = Uloge.Zaposlenik;

                db.AutorizacijskiTokeni.Add(new AutorizacijskiToken
                {
                    Vrijednost = a.Token,
                    KorisnikID = a.KorisnikID,
                    VrijemeEvidentiranja = DateTime.Now,
                    IpAdresa = "..."
                });

                db.SaveChanges();

                return Ok(a);
            }
            else
            {
                Pacijenti pacijent = db.Pacijenti.Where(s => s.KorisnickoIme == username && s.LozinkaSalt == pass).FirstOrDefault();
                if (pacijent != null)
                {
                    AutentifikacijaResultVM a = new AutentifikacijaResultVM();
                    a.KorisnikID = pacijent.PacijentID;
                    a.Ime = pacijent.Ime;
                    a.Prezime = pacijent.Prezime;
                    a.KorisnickoIme = pacijent.KorisnickoIme;
                    a.LozinkaSalt = pacijent.LozinkaSalt;
                    a.Telefon = pacijent.Telefon;
                    a.Email = pacijent.Email;
                    a.Token = token;
                    a.Uloga = Uloge.Korisnik;

                    return Ok(a);
                }
            }


            AutentifikacijaResultVM y = new AutentifikacijaResultVM();
            y.Ime = "PogresniPodaci";

            return Ok(y);
        }

        [HttpDelete]
        [Route("api/Autentifikacija/Logout")]
        public IHttpActionResult Logout()
        {
            IzbrisiToken();
            return Ok();
        }
    }
}
