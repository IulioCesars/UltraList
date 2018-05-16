using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using wsUltraList.Modelo;
using wsUltraList.Utils;

namespace wsUltraList.Controllers
{
    public class OfertaController : GenericController<Oferta>
    {
        protected override Func<Oferta, bool> BuscarEntidad(Oferta entidad)
        {
            throw new NotImplementedException();
        }

        protected override Func<Oferta, bool> BuscarID(int id)
        {
            return (o) => o.id_oferta == id;
        }

    }
}
