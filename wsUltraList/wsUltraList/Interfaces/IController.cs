﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using wsUltraList.Utils;

namespace wsUltraList.Interfaces
{
    interface IController<T>
    {
        Result<bool> Agregar(T entidad);
        Result<bool> Editar(T entidad);
        Result<bool> Eliminar(int id);
        Result<List<T>> ObtenerLista();
        Result<List<T>> Buscar(T entidad);
    }
}
