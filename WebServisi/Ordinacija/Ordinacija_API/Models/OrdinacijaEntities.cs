
namespace Ordinacija_API.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.Data.Entity;
    using System.Linq;

    public class OrdinacijaEntities : DbContext
    {

        public OrdinacijaEntities()
            : base("name=OrdinacijaEntities")
        {
            //this.Configuration.ProxyCreationEnabled = false;
        }

        public virtual DbSet<Korisnici> Korisnici { get; set; }
        public virtual DbSet<Pregledi> Pregledi { get; set; }
        public virtual DbSet<Termini> Termini { get; set; }
        public virtual DbSet<Pacijenti> Pacijenti { get; set; }
        public virtual DbSet<AutorizacijskiToken> AutorizacijskiTokeni { get; set; }
        public virtual DbSet<Usluge> Usluge { get; set; }


    }
}