using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Ordinacija_API.Models
{

    [Table("Usluge")]
    public class Usluge
    {
        [Key]
        public int UslugaID { get; set; }
        public string Naziv { get; set; }
        public string Opis { get; set; }

            
    }
}