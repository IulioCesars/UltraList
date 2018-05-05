using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using wsUltraList.Modelo;

namespace wsUltraList.Controllers
{
    public class ListaController : GenericController<Lista>
    {
        protected override Func<Lista, bool> BuscarEntidad(Lista entidad)
        {
            throw new NotImplementedException();
        }

        protected override Func<Lista, bool> BuscarID(int id)
        { return (l) => l.id_lista == id; }


    }
}
