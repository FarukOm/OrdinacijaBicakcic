using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Ordinacija_API.Models
{
    [Table("Termini")]
    public class Termini
    {
        [Key]
        public int TerminID { get; set; }
        public DateTime Datum { get; set; }
        public string Vrijeme { get; set; }
        public string Napomena { get; set; }
        public bool IsOdobren { get; set; }
        public bool IsEvidentiran { get; set; }
    
        public bool IsDeleted { get; set; }

        [ForeignKey(nameof(PacijentID))]
        public virtual Pacijenti Pacijent { get; set; }
        public int PacijentID { get; set; }

        [ForeignKey(nameof(UslugaID))]
        public virtual Usluge Usluga { get; set; }
        public int UslugaID { get; set; }


    }
}