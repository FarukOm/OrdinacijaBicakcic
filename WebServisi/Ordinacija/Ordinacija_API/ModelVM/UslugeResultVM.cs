using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ordinacija_API.ModelVM
{
    public class UslugeResultVM
    {
        public List<Row> rows { get; set; }

        public class Row
        {
            public int UslugaID { get; set; }
            public string Naziv { get; set; }
            public string Opis { get; set; }


        }
    }
}