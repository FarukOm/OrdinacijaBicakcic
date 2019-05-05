using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class PregledPostVM
    {

        public string  Datum { get; set; }
        public string Vrijeme { get; set; }
        public string Opis { get; set; }
        public int PacijentID { get; set; }
        public int ZaposlenikID { get; set; }
        public int UslugaID { get; set; }


    }
}