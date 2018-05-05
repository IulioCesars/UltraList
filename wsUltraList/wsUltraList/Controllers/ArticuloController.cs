using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using wsUltraList.Interfaces;
using wsUltraList.Modelo;
using wsUltraList.Utils;

namespace wsUltraList.Controllers
{
    public class ArticuloController : GenericController<Articulo>
    {
        protected override Func<Articulo, bool> BuscarEntidad(Articulo entidad)
        {
            throw new NotImplementedException();
        }

        protected override Func<Articulo, bool> BuscarID(int id)
        { return (a) => a.id_articulo == id; }

    }
}
