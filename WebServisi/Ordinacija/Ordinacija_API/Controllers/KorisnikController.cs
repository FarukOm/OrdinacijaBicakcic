using Ordinacija_API.Helper;
using Ordinacija_API.Models;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Description;

namespace Ordinacija_API.Controllers
{
    public class KorisnikController : AuthToken
    {
        private OrdinacijaEntities db = new OrdinacijaEntities();


        //POST: api/Korisnici
        [HttpPost]
        [Route("api/Korisnici")]
        [ResponseType(typeof(Korisnici))]
        public IHttpActionResult PostKorisnici([FromBody] Pacijenti pacijent)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (db.Korisnici.Any(x => x.KorisnickoIme == pacijent.KorisnickoIme) || db.Pacijenti.Any(x => x.KorisnickoIme == pacijent.KorisnickoIme))
            {
                return BadRequest("Korisničko ime postoji");
            }

            db.Pacijenti.Add(pacijent);
            db.SaveChanges();
            return Ok();
        }

    }
}
