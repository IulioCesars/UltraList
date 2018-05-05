using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace wsUltraList.Modelo
{
    public class Entidad<T> where T : class
    {
        public static bool Agregar(T entidad)
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    ctx.Set<T>().Add(entidad);
                    return ctx.SaveChanges() > 0;
                }
            }
            catch (Exception)
            { throw; }
        }

        public static bool Editar(T entidad)
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    ctx.Entry<T>(entidad).State = EntityState.Modified;
                    return ctx.SaveChanges() > 0;
                }
            }
            catch (Exception)
            { throw; }
        }

        public static bool Eliminar(T entidad)
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    ctx.Entry<T>(entidad).State = EntityState.Deleted;
                    return ctx.SaveChanges() > 0;
                }
            }
            catch (Exception)
            { throw; }
        }

        public static List<T> ObtenerLista()
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    return ctx.Set<T>().ToList();
                }
            }
            catch (Exception)
            { throw; }
        }

        public static List<T> ObtenerLista(Func<T, bool> pred)
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    return ctx.Set<T>().Where(pred).ToList();
                }
            }
            catch (Exception)
            { throw; }
        }

        public static T Buscar(Func<T, bool> pred)
        {
            try
            {
                using (var ctx = Contexto.Crear())
                {
                    return ctx.Set<T>().Where(pred).FirstOrDefault();
                }
            }
            catch (Exception)
            { throw; }
        }
    }

    public static class Contexto
    {
        public static DbContext Crear()
        {
            var ctx = new Modelo.UltraList();

            ctx.Configuration.ProxyCreationEnabled = false;

            return ctx;
        }
    }
}