using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace wsUltraList.Modelo
{
    public partial class Oferta : Entidad<Oferta>
    {
        public Oferta() { }

        public Oferta(Articulo articulo)
        {
            id_articulo = articulo.id_articulo;
            nombre = articulo.nombre;
            descripcion = articulo.descripcion;
            categoria = articulo.categoria;
            precio = articulo.precio;
            usuario_agrego = articulo.usuario_agrego;
            fecha_agrego = articulo.fecha_agrego;
            usuario_modifico = articulo.usuario_modifico;
            fecha_modifico = articulo.fecha_modifico;
            estatus = articulo.estatus;
            image_path = articulo.image_path;
            lat = articulo.lat;
            lng = articulo.lng;
        }

    }
}