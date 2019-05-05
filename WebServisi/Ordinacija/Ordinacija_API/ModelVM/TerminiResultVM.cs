using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class TerminiResultVM
    {
        public List<Row> rows { get; set; }

        public class Row
        {
            public int TerminID { get; internal set; }
            public DateTime Datum { get; set; }
            public string Vrijeme { get; set; }
            public string Napomena { get; set; }
            public bool? IsOdobren { get; set; }
            public bool IsEvidentiran { get; set; }
            public bool IsDeleted { get; set; }

            public string ImePrezime { get; set; }
            public int PacijentID { get; set; }
            public string Usluga { get; set; }


        }
    }
}