using Ordinacija_API.Models;

namespace Ordinacija_API.ModelVM
{
    public class AutentifikacijaResultVM
    {

        public int KorisnikID { get; set; }
        public string KorisnickoIme { get; set; }
        public string LozinkaSalt { get; set; }
        public string Telefon { get; set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public string Email { get; set; }
        public string Token { get; set; }
        public Uloge Uloga { get; set; }

    }
}