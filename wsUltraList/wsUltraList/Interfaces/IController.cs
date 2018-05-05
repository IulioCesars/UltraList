using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using wsUltraList.Utils;

namespace wsUltraList.Interfaces
{
    interface IController<T>
    {
        Result Agregar(T entidad);
        Result Editar(T entidad);
        Result Eliminar(int id);
        Result<List<T>> ObtenerLista();
        Result<List<T>> Buscar(T entidad);
    }
}
