using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.Http;
using wsUltraList.Interfaces;
using wsUltraList.Modelo;
using wsUltraList.Utils;

namespace wsUltraList.Controllers
{
    public abstract class GenericController<T> : ApiController
        where T : Entidad<T>
    {
        protected abstract Func<T, bool> BuscarID(int id);
        protected abstract Func<T, bool> BuscarEntidad(T entidad);

        [HttpPost]
        public virtual Result<String> Prueba() { Thread.Sleep(1000); return new Result<string>(true,"Hola Mundo"); }


        [HttpPost]
        public virtual Result<bool> Agregar(T entidad)
        {
            try
            {
                var resultado = Entidad<T>.Agregar(entidad);
                return new Result<bool>(resultado, true);
            }
            catch (Exception ex)
            { return new Result<bool>(ex); }
        }

        [HttpPost]
        public virtual Result<List<T>> Buscar(T entidad)
        {
            try
            {
                var resultado = Entidad<T>.ObtenerLista(BuscarEntidad(entidad));
                return new Result<List<T>>(true, resultado);
            }
            catch (Exception ex)
            { return new Result<List<T>>(ex); }
        }

        [HttpPost]
        public virtual Result<bool> Editar(T entidad)
        {
            try
            {
                var resultado = Entidad<T>.Editar(entidad);
                return new Result<bool>(resultado, true);
            }
            catch (Exception ex)
            { return new Result<bool>(ex); }
        }

        [HttpPost]
        public virtual Result<bool> Eliminar(int id)
        {
            try
            {
                T entidad = Entidad<T>.Obtener(BuscarID(id));
                bool resultado = Entidad<T>.Eliminar(entidad);
                return new Result<bool>(resultado, true);
            }
            catch (Exception ex)
            { return new Result<bool>(ex); }
        }

        [HttpPost]
        public virtual List<T> ObtenerLista()
        {
            try
            {
                var resultado = Entidad<T>.ObtenerLista();
                return resultado;
            }
            catch (Exception ex)
            { return null; }
        }

    }
}