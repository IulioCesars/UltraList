using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace wsUltraList.Modelo
{
    public interface IEntidad<T>
    {
        Func<T, bool> BuscarID(int id);
        Func<T, bool> BuscarEntidad(T entidad);
    }
}
