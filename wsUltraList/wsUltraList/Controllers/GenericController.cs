using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using wsUltraList.Interfaces;
using wsUltraList.Modelo;
using wsUltraList.Utils;

namespace wsUltraList.Controllers
{
    public abstract class GenericController<T> : ApiController, IController<T>
        where T : Entidad<T>
    {
        protected abstract Func<T, bool> BuscarID(int id);
        protected abstract Func<T, bool> BuscarEntidad(T entidad);

        [HttpGet]
        public virtual String Prueba() { return "Hola Mundo"; }


        [HttpPost]
        public virtual Result Agregar(T entidad)
        {
            try
            {
                var resultado = Entidad<T>.Agregar(entidad);
                return new Result(resultado);
            }
            catch (Exception ex)
            { return new Result(ex); }
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
        public virtual Result Editar(T entidad)
        {
            try
            {
                var resultado = Entidad<T>.Editar(entidad);
                return new Result(resultado);
            }
            catch (Exception ex)
            { return new Result(ex); }
        }

        [HttpPost]
        public virtual Result Eliminar(int id)
        {
            try
            {
                T entidad = Entidad<T>.Buscar(BuscarID(id));
                bool resultado = Entidad<T>.Eliminar(entidad);
                return new Result(resultado);
            }
            catch (Exception ex)
            { return new Result(ex); }
        }

        [HttpPost]
        public virtual Result<List<T>> ObtenerLista()
        {
            try
            {
                var resultado = Entidad<T>.ObtenerLista();
                return new Result<List<T>>(true, resultado);
            }
            catch (Exception ex)
            { return new Result<List<T>>(ex); }
        }

    }
}