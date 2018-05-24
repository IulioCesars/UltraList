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


        [HttpGet]
        public Result<int> MeGusta(int idOferta, int idUsuario)
        {
            var o = Oferta.Obtener(it => it.id_oferta == idOferta);
            if (o != null)
            {
                if (Modelo.MeGusta.Existe(it => it.id_oferta == idOferta && it.id_usuario == idUsuario))
                    return new Result<int>(false, 0);
                o.me_gusta = (o.me_gusta ?? 0) + 1;
                Modelo.MeGusta.Agregar(new Modelo.MeGusta() { id_oferta = idOferta, id_usuario = idUsuario });
                Oferta.Editar(o);
            }

            return new Result<int>(true,o?.me_gusta ?? 0);
        }
    }
}
