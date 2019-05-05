using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Ordinacija_API.Models
{
    [Table("Korisnici")]
    public class Korisnici
    {
        [Key]
        public int KorisnikID { get; set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public string Telefon { get; set; }
        public string Email { get; set; }
        public string KorisnickoIme { get; set; }
        public string LozinkaSalt { get; set; }
        public string LozinkaHash { get; set; }
        public Uloge Uloga { get; set; }

    }
}