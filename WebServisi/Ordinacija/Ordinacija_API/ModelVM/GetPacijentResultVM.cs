using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class GetPacijentResultVM
    {
        public int PacijentID { get; internal set; }
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public DateTime DatumRodjenja { get; set; }
        public string Telefon { get; set; }
        public string Adresa { get; set; }
        public string Email { get; set; }
    }
}