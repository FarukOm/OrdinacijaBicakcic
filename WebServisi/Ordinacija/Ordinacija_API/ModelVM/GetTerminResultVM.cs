using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class GetTerminResultVM
    {
        public int TerminID { get; set; }
        public DateTime Datum { get; set; }
        public string Vrijeme { get; set; }
        public string Usluga { get; set; }
        public string Napomena { get; set; }
        public bool IsOdobren { get; set; }
        public bool IsEvidentiran { get; set; }
        public bool IsDeleted { get; set; }
        public int UslugaID { get; set; }

        public string ImePrezime { get; set; }
        public int PacijentID { get; set; }
    }
}