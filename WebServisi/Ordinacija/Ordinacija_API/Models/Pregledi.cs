using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Ordinacija_API.Models
{
    [Table("Pregledi")]
    public class Pregledi
    {
        [Key]
        public int PregledID { get; set; }  
        public DateTime Datum { get; set; }
        public string Vrijeme { get; set; }
        public string Opis { get; set; }
        [ForeignKey(nameof(PacijentID))]
        public virtual Pacijenti Pacijent { get; set; }
        public int PacijentID { get; set; }

        [ForeignKey(nameof(ZaposlenikID))]
        public virtual Korisnici Zaposlenik { get; set; }
        public int ZaposlenikID { get; set; }

        [ForeignKey(nameof(UslugaID))]
        public virtual Usluge Usluga { get; set; }
        public int UslugaID { get; set; }
    }
}   