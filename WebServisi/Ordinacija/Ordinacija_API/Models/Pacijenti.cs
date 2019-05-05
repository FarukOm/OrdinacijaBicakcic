using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Ordinacija_API.Models
{
    [Table("Pacijenti")]
    public class Pacijenti
    {
        [Key]
        public int PacijentID { get; set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public DateTime DatumRodjenja { get; set; }
        public string Adresa { get; set; }
        public string Telefon { get; set; }
        public string Email { get; set; }
        public string LozinkaSalt { get; set; }
        public string KorisnickoIme { get; set; }
    }
}