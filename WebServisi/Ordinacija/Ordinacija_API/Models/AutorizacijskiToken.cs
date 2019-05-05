using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace Ordinacija_API.Models
{
    [Table("AutorizacijskiToken")]
    public class AutorizacijskiToken
    {
        [Key]
        public int AutorizacijskiTokenID { get; set; }
        public string Vrijednost { get; set; }
        public Nullable<System.DateTime> VrijemeEvidentiranja { get; set; }
        public string IpAdresa { get; set; }
        public string DeviceInfo { get; set; }
        public Nullable<int> KorisnikID { get; set; }
        [ForeignKey("KorisnikID")]
        public virtual Korisnici Korisnik { get; set; }
    }
}