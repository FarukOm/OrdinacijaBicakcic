using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class PreglediResultVM
    {
        public List<Row> rows { get; set; }

        public class Row
        {
            public int PregledID { get; set; }
            public DateTime Datum { get; set; }
            public string Vrijeme { get; set; }
            public string Opis { get; set; }
            public int ZaposlenikID { get; set; }
            public int PacijentID { get; set; }
            public string Usluga { get; set; }


        }
    }
}