using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace wsUltraList.Utils
{
    public class Result<T>
    {
        public T valor { get; set; }
        public String mensaje { get; set; }
        public bool exito { get; set; }

        public Result(bool exito, T valor, string mensaje = "")
        {
            this.exito = exito;
            this.valor = valor;
            this.mensaje = mensaje;
        }

        public Result(Exception ex)
        {
            this.exito = false;
            this.valor = default(T);
            this.mensaje = ex.GetBaseException().Message;
        }
    }
}