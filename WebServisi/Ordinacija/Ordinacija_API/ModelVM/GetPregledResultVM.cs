using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class GetPregledResultVM
    {
        public int PregledID { get; set; }
        public string Datum { get; set; }
        public string Vrijeme { get; set; }
        public string Opis { get; set; }
        public int ZaposlenikID { get; set; }
        public int PacijentID { get; set; }
        public string Usluga { get; set; }

    }
}